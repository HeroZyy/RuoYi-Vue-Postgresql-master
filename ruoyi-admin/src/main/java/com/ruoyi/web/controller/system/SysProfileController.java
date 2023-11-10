package com.ruoyi.web.controller.system;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.framework.web.service.SysRegisterService;
import com.ruoyi.system.domain.B2bChangeMsg;
import com.ruoyi.system.domain.B2bMember;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IB2bMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysRegisterService sysRegisterService;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private IB2bMemberService memberService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        String s = userService.selectUserRoleGroup(loginUser.getUsername());
        if(s.contains("认证会员")) s = "3";
        else s = "4";
        ajax.put("roleGroup", s);
        ajax.put("profileMsg",memberService.selectB2bMemberBySid(loginUser.getUserId()));
//        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 获取用户身份是普通会员还是认证会员
     */
    @GetMapping("/roleMsg")
    public AjaxResult roleMsg()
    {
        LoginUser loginUser = getLoginUser();
//        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
//        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 找回密码验证码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @GetMapping("/findPwdCode")
    public AjaxResult findPwdCode(String phonenumber)
    {
        AjaxResult ajax = AjaxResult.success();
        SysUser user = new SysUser();
        user.setPhonenumber(phonenumber);
        if(userService.checkPhoneUnique(user)){
            return error("手机号不存在");
        }
        String resCode = userService.resPhoneCode("修改密码提醒",phonenumber);
        ajax.put("CODE", resCode);
        return ajax;
    }

    /**
     * 手机号重置密码 外部的
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPhonePwd")
    public AjaxResult resetPhonePwd(String phonenumber,String code)
    {
        String res = userService.resPhoneCodeTrue(phonenumber,code);
        if(StringUtils.isNotEmpty(res)) return error(res);
        String newPassword = UserConstants.PASSWD;
        String username = phonenumber;
        //目前username == phonenumber , 以下方法返回int ， 表示update语句
        //此处没保存缓存，不知道后续是否会有问题
        if(userService.resetUserPwd(username,SecurityUtils.encryptPassword(newPassword)) > 0){
            return success("密码重置为默认密码");
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 手机验证码重置密码 内部的
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @GetMapping("/codeUpdatePwd")
    public AjaxResult codeUpdatePwd(String phonenumber,String code)
    {
//        LoginUser loginUser = getLoginUser();
        SysUser loginUser = userService.selectUserByUserName(phonenumber);
        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();
        String msg = sysRegisterService.vatifyPhoneCode(phonenumber,code);
        String newPassword = UserConstants.PASSWD;
        if (StringUtils.isNotEmpty(msg)){
            return error(msg);
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.setPassword(SecurityUtils.encryptPassword(newPassword));
//            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 修改用户 平台的
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateMsg")
    public AjaxResult updateMsg(@RequestBody B2bChangeMsg user)
    {
        LoginUser loginUser = getLoginUser();
        SysUser currentUser = loginUser.getUser();
//        currentUser.setNickName(user.getNickName());
        currentUser.setEmail(user.getEmail());
        B2bMember currentMember = memberService.selectB2bMemberBySid(currentUser.getUserId());
        currentMember.setCompany(user.getCompany());
//        currentMember.setAddr(user.getAddr());
        currentMember.setRealname(user.getRealname());
        currentMember.setEmail(user.getEmail());
        currentMember.setAvator(user.getAvatar());
//        currentUser.setUserName(user.getUserName());
//        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(currentUser))
//        {
//            return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
//        }
        if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(currentUser))
        {
            return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (userService.updateUserProfile(currentUser) > 0 && memberService.updateB2bMember(currentMember) > 0)
        {
            // 更新缓存用户信息
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 修改用户 若依的
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user)
    {
        LoginUser loginUser = getLoginUser();
        SysUser currentUser = loginUser.getUser();
        currentUser.setNickName(user.getNickName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(currentUser))
        {
            return error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(currentUser))
        {
            return error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (userService.updateUserProfile(currentUser) > 0)
        {
            // 更新缓存用户信息
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 修改密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @GetMapping("/updatePassword")
    public AjaxResult updatePassword(String oldPassword, String newPassword)
    {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
}

package com.ruoyi.framework.web.service;

import com.ruoyi.common.utils.SendMsg;
import com.ruoyi.system.domain.B2bMember;
import com.ruoyi.system.mapper.SysUserMapper;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ruoyi.system.service.IB2bMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import static com.ruoyi.common.utils.SendMsg.Send;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private IB2bMemberService memberService;


    /**
     * 手机号是否被注册
     */
    public Boolean phoneIfExist(String phonenumber){
        //通过手机号查找sysuser
        SysUser info = userMapper.checkPhoneUnique(phonenumber);
        if (StringUtils.isNotNull(info))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 注册手机
     * 返回参数：验证码
     */
    public String getRegisterPhoneCode(String phonenumber){
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        String result = String.format("%04d", randomInt);
        redisCache.setCacheObject(phonenumber,result,UserConstants.CODE_TIME_OUT, TimeUnit.MINUTES);
        Send("注册提醒",phonenumber,Integer.parseInt(result));
        //要形成电话和验证码的映射，后续对比验证码和电话
        return result;
    }

    /**
     * 注册手机判断验证码
     */
    public String vatifyPhoneCode(String phonenumber, String code){
        String codeCache = redisCache.getCacheObject(phonenumber);
        if(!redisCache.hasKey(phonenumber)){
            return "验证码失效，请重新获取验证码";
        }
        if(!code.equals(codeCache)) {
            return "验证码错误";
        }
        redisCache.deleteObject(phonenumber);
        return "";
    }

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getPhonenumber(), password = registerBody.getPassword();
        // 设置默认密码为Aa123456
        password = UserConstants.PASSWD;
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
//        sysUser.setAvatar(registerBody.getAvatar());
//        sysUser.setEmail(registerBody.getEmail());
        sysUser.setPhonenumber(registerBody.getPhonenumber());
//        sysUser.setSex(registerBody.getSex());
        sysUser.setDelFlag("0");
        //默认注册给予会员权限
        Long [] member = {4L};
        sysUser.setRoleIds(member);
        sysUser.setStatus("0");
        //设置为普通会员
        sysUser.setUserType("1");
        // 验证码开关
//        boolean captchaEnabled = configService.selectCaptchaEnabled();
//        if (captchaEnabled)
//        {
//            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
//        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else if (StringUtils.isNotEmpty(sysUser.getPhonenumber()) && !userService.checkPhoneUnique(sysUser))
        {
            msg = ("注册用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(sysUser.getEmail()) && !userService.checkEmailUnique(sysUser))
        {
            msg = ("注册用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
        }
        else
        {
            try {
                sysUser.setNickName(username);
                sysUser.setPassword(SecurityUtils.encryptPassword(password));
                boolean regFlag = userService.registerUser(sysUser);
                userService.updateUser(sysUser);
                // 插入会员表会员信息
                B2bMember b2bMember = new B2bMember();
                b2bMember.setSid(sysUser.getUserId());
                b2bMember.setPhone(sysUser.getPhonenumber());
                //默认设置为普通会员
                b2bMember.setMemberType(1L);
                b2bMember.setSex(1L);
                memberService.insertB2bMember(b2bMember);
                if (!regFlag)
                {
                    msg = "注册失败,请联系系统管理人员";
                }
                else
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));

                }
            }
            catch (Exception e){
                msg = "注册失败,请联系系统管理人员";
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}

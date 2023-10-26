package com.ruoyi.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysRegisterService;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/sys/user/register/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
//        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
//        {
//            return error("当前系统没有开启注册功能！");
//        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @GetMapping("/sys/user/register/getCode")
    public AjaxResult getPhoneCode(String phonenumber){
        AjaxResult ajax = AjaxResult.success();
        Boolean res = registerService.phoneIfExist(phonenumber);
        if(!res) return error("该手机号已注册");
        String registerPhoneCode = registerService.getRegisterPhoneCode(phonenumber);
        //暂时用这个，不用返回验证码
        ajax.put("code", registerPhoneCode);
        return ajax;
    }

    @GetMapping("/sys/user/register/submitPhoneAndCode")
    public AjaxResult submitPhoneAndCode(String phonenumber,String code){
        String msg = registerService.vatifyPhoneCode(phonenumber, code);
        if(StringUtils.isNotEmpty(msg)) return error(msg);
        RegisterBody user = new RegisterBody();
        user.setPhonenumber(phonenumber);
        String resMsg = registerService.register(user);
        if(StringUtils.isNotEmpty(resMsg)) return error(resMsg);
        return success("注册成功");
    }


}

package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.B2bLogin;
import com.ruoyi.system.service.IB2bLoginService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登录信息Controller
 * 
 * @author ruoyi
 * @date 2023-10-23
 */
@RestController
@RequestMapping("/system/login")
public class B2bLoginController extends BaseController
{
    @Autowired
    private IB2bLoginService b2bLoginService;

    /**
     * 查询登录信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:login:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bLogin b2bLogin)
    {
        startPage();
        List<B2bLogin> list = b2bLoginService.selectB2bLoginList(b2bLogin);
        return getDataTable(list);
    }

    /**
     * 导出登录信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:login:export')")
    @Log(title = "登录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bLogin b2bLogin)
    {
        List<B2bLogin> list = b2bLoginService.selectB2bLoginList(b2bLogin);
        ExcelUtil<B2bLogin> util = new ExcelUtil<B2bLogin>(B2bLogin.class);
        util.exportExcel(response, list, "登录信息数据");
    }

    /**
     * 获取登录信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:login:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bLoginService.selectB2bLoginBySid(sid));
    }

    /**
     * 新增登录信息
     */
    @PreAuthorize("@ss.hasPermi('system:login:add')")
    @Log(title = "登录信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bLogin b2bLogin)
    {
        return toAjax(b2bLoginService.insertB2bLogin(b2bLogin));
    }

    /**
     * 修改登录信息
     */
    @PreAuthorize("@ss.hasPermi('system:login:edit')")
    @Log(title = "登录信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bLogin b2bLogin)
    {
        return toAjax(b2bLoginService.updateB2bLogin(b2bLogin));
    }

    /**
     * 删除登录信息
     */
    @PreAuthorize("@ss.hasPermi('system:login:remove')")
    @Log(title = "登录信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bLoginService.deleteB2bLoginBySids(sids));
    }
}

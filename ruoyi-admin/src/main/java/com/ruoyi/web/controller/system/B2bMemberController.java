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
import com.ruoyi.system.domain.B2bMember;
import com.ruoyi.system.service.IB2bMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/system/member")
public class B2bMemberController extends BaseController
{
    @Autowired
    private IB2bMemberService b2bMemberService;

    /**
     * 查询会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bMember b2bMember)
    {
        startPage();
        List<B2bMember> list = b2bMemberService.selectB2bMemberList(b2bMember);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:export')")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bMember b2bMember)
    {
        List<B2bMember> list = b2bMemberService.selectB2bMemberList(b2bMember);
        ExcelUtil<B2bMember> util = new ExcelUtil<B2bMember>(B2bMember.class);
        util.exportExcel(response, list, "会员数据");
    }

    /**
     * 获取会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:member:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bMemberService.selectB2bMemberBySid(sid));
    }

    /**
     * 新增会员
     */
    @PreAuthorize("@ss.hasPermi('system:member:add')")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bMember b2bMember)
    {
        return toAjax(b2bMemberService.insertB2bMember(b2bMember));
    }

    /**
     * 修改会员
     */
    @PreAuthorize("@ss.hasPermi('system:member:edit')")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bMember b2bMember)
    {
        return toAjax(b2bMemberService.updateB2bMember(b2bMember));
    }

    /**
     * 删除会员
     */
    @PreAuthorize("@ss.hasPermi('system:member:remove')")
    @Log(title = "会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bMemberService.deleteB2bMemberBySids(sids));
    }
}

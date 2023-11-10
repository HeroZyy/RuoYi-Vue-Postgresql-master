package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.B2bAiListConfig;
import com.ruoyi.system.domain.B2bDevNeedNum;
import com.ruoyi.system.domain.B2bProductConfigList;
import com.ruoyi.system.service.IB2bProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.B2bDevDetailedlist;
import com.ruoyi.system.service.IB2bDevDetailedlistService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 智能报价清单Controller
 *
 * @author ruoyi
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/system/detailedlist")
public class B2bDevDetailedlistController extends BaseController
{
    @Autowired
    private IB2bDevDetailedlistService b2bDevDetailedlistService;

    @Autowired
    private IB2bProductService productService;
    /**
     * 查询智能清单分项配置
     */
    @GetMapping("/getConfig")
    public AjaxResult getConfig(Long productId){
        List<B2bProductConfigList> configByPIdAndTypeId = b2bDevDetailedlistService.getConfigByPId(productId);
        return success(configByPIdAndTypeId);
    }

    /**
     * 下载智能报价清单
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:export')")
    @Log(title = "智能报价清单", businessType = BusinessType.EXPORT)
    @PostMapping("/exportList")
    public void exportList(HttpServletResponse response,@RequestBody HashMap<Long , Long> map)
    {
        List<B2bDevDetailedlist> l = new ArrayList<>();
        for (Long x : map.keySet()) {
            B2bDevDetailedlist devlist = new B2bDevDetailedlist();
            devlist.setDevId(x);
            List<B2bDevDetailedlist> b2bDevDetailedlists = b2bDevDetailedlistService.selectB2bDevDetailedlistList(devlist);
            for (B2bDevDetailedlist d : b2bDevDetailedlists) {
                d.setDevNum(String.valueOf(map.get(x)));
            }
            l.addAll(b2bDevDetailedlists);
        }
        //原方案
//        for (B2bAiListConfig AiList : list) {
//            B2bDevDetailedlist devlist = new B2bDevDetailedlist();
//            List<B2bDevNeedNum> devNeedNum = AiList.getDevNeedNum();
//            // 分项的数量
//            for (B2bDevNeedNum needNum : devNeedNum) {
//                devlist.setDevId(needNum.getDevId());
//                //查找的是唯一分项，这个列表只有一个，设置它的数量
//                List<B2bDevDetailedlist> b2bDevDetailedlists = b2bDevDetailedlistService.selectB2bDevDetailedlistList(devlist);
//                for (B2bDevDetailedlist d : b2bDevDetailedlists) {
//                    d.setDevNum(String.valueOf(needNum.getDevNum()));
//                }
//                l.addAll(b2bDevDetailedlists);
//            }
//        }

        List<B2bDevDetailedlist> res = new ArrayList<>();
        Double totalPrice = (double) 0;
        for (B2bDevDetailedlist row : l) {
                //算出总价
                Double sum = Double.parseDouble(row.getDevTotalprice()) +(Double.parseDouble(row.getDevNum()) * Double.parseDouble(row.getDevPerprice()));
                String resTotal = String.valueOf(sum);
                row.setDevTotalprice(resTotal);
                res.add(row);
                totalPrice+=sum;
        }
        B2bDevDetailedlist last = new B2bDevDetailedlist();
        last.setDevTypename("总计");
        last.setDevTotalprice(String.valueOf(totalPrice));
        res.add(last);
        ExcelUtil<B2bDevDetailedlist> util = new ExcelUtil<B2bDevDetailedlist>(B2bDevDetailedlist.class);
        util.exportExcel(response, res, "智能报价清单数据");
        String filePath = util.getPath(res,"智能报价清单数据");//调用了通用下载方法进行文件下载
        System.out.println("xlsx文件绝对路径 : " + util.getAbsoluteFile(filePath));
    }

    /**
     * 导出智能报价清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:export')")
    @Log(title = "智能报价清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bDevDetailedlist b2bDevDetailedlist)
    {
        List<B2bDevDetailedlist> list = b2bDevDetailedlistService.selectB2bDevDetailedlistList(b2bDevDetailedlist);
        ExcelUtil<B2bDevDetailedlist> util = new ExcelUtil<B2bDevDetailedlist>(B2bDevDetailedlist.class);
        util.exportExcel(response, list, "智能报价清单数据");
        String filePath = util.getPath(list,"智能报价清单数据");//调用了通用下载方法进行文件下载
        System.out.println("xlsx文件绝对路径 : " + util.getAbsoluteFile(filePath));
    }

    /**
     * 查询智能报价清单列表
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bDevDetailedlist b2bDevDetailedlist)
    {
        startPage();
        List<B2bDevDetailedlist> list = b2bDevDetailedlistService.selectB2bDevDetailedlistList(b2bDevDetailedlist);
        return getDataTable(list);
    }




    /**
     * 获取智能报价清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bDevDetailedlistService.selectB2bDevDetailedlistBySid(sid));
    }

    /**
     * 新增智能报价清单
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:add')")
    @Log(title = "智能报价清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bDevDetailedlist b2bDevDetailedlist)
    {
        return toAjax(b2bDevDetailedlistService.insertB2bDevDetailedlist(b2bDevDetailedlist));
    }

    /**
     * 修改智能报价清单
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:edit')")
    @Log(title = "智能报价清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bDevDetailedlist b2bDevDetailedlist)
    {
        return toAjax(b2bDevDetailedlistService.updateB2bDevDetailedlist(b2bDevDetailedlist));
    }

    /**
     * 删除智能报价清单
     */
    @PreAuthorize("@ss.hasPermi('system:detailedlist:remove')")
    @Log(title = "智能报价清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bDevDetailedlistService.deleteB2bDevDetailedlistBySids(sids));
    }
}

package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.system.domain.B2bProductConfigList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bDevDetailedlistMapper;
import com.ruoyi.system.domain.B2bDevDetailedlist;
import com.ruoyi.system.service.IB2bDevDetailedlistService;

import static com.ruoyi.common.utils.UniqueUtil.removeDuplicates;

/**
 * 智能报价清单Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class B2bDevDetailedlistServiceImpl implements IB2bDevDetailedlistService
{
    @Autowired
    private B2bDevDetailedlistMapper b2bDevDetailedlistMapper;

    @Autowired
    private IB2bDevDetailedlistService devDetailedlistService;

    @Override
    public List<B2bProductConfigList> getConfigByPId(Long product_id) {
        List<B2bDevDetailedlist> configByPId = getDetailedListByPId(product_id);
        //typeId 映射设备类型分类的设备分项
        HashMap<Long,B2bProductConfigList> typeHash = new HashMap<>();
        for (B2bDevDetailedlist x : configByPId) {
            if(!typeHash.containsKey(x.getDevType()) ){
                B2bProductConfigList configList = new B2bProductConfigList();
                configList.setDevList(new ArrayList<B2bDevDetailedlist>());
                configList.getDevList().add(x);
                configList.setProductId(product_id);
                configList.setDevType(x.getDevType());
                configList.setDevTypename(getDetailedListByPIdAndTypeId(product_id,x.getDevType()).getDevTypename());
                typeHash.put(x.getDevType() , configList);
            }
            else{
                typeHash.get(x.getDevType()).getDevList().add(x);
            }
        }
        List<B2bProductConfigList> resConfigList = new ArrayList<>();
        for (Long l : typeHash.keySet()) {
            resConfigList.add(typeHash.get(l));
        }
        return resConfigList;
    }

    @Override
    public B2bDevDetailedlist getDetailedListByPIdAndTypeId(Long product_id, Long dev_type) {
        B2bDevDetailedlist ser = new B2bDevDetailedlist();
        ser.setProductId(product_id);
        ser.setDevType(dev_type);
        List<B2bDevDetailedlist> b2bDevDetailedlists = b2bDevDetailedlistMapper.selectB2bDevDetailedlistList(ser);
        B2bDevDetailedlist res = new B2bDevDetailedlist();
        for (B2bDevDetailedlist b2bDevDetailedlist : b2bDevDetailedlists) {
            res = b2bDevDetailedlist;
            break;
        }
        return res;
    }

    @Override
    public List<B2bDevDetailedlist> getDetailedListByPId(Long product_id) {
        B2bDevDetailedlist ser = new B2bDevDetailedlist();
        ser.setProductId(product_id);
        List<B2bDevDetailedlist> b2bDevDetailedlists = b2bDevDetailedlistMapper.selectB2bDevDetailedlistList(ser);
        return b2bDevDetailedlists;
    }



    /**
     * 查询智能报价清单
     *
     * @param sid 智能报价清单主键
     * @return 智能报价清单
     */
    @Override
    public B2bDevDetailedlist selectB2bDevDetailedlistBySid(Long sid)
    {
        return b2bDevDetailedlistMapper.selectB2bDevDetailedlistBySid(sid);
    }

    /**
     * 查询智能报价清单列表
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 智能报价清单
     */
    @Override
    public List<B2bDevDetailedlist> selectB2bDevDetailedlistList(B2bDevDetailedlist b2bDevDetailedlist)
    {
        return b2bDevDetailedlistMapper.selectB2bDevDetailedlistList(b2bDevDetailedlist);
    }

    /**
     * 新增智能报价清单
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 结果
     */
    @Override
    public int insertB2bDevDetailedlist(B2bDevDetailedlist b2bDevDetailedlist)
    {
        return b2bDevDetailedlistMapper.insertB2bDevDetailedlist(b2bDevDetailedlist);
    }

    /**
     * 修改智能报价清单
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 结果
     */
    @Override
    public int updateB2bDevDetailedlist(B2bDevDetailedlist b2bDevDetailedlist)
    {
        return b2bDevDetailedlistMapper.updateB2bDevDetailedlist(b2bDevDetailedlist);
    }

    /**
     * 批量删除智能报价清单
     *
     * @param sids 需要删除的智能报价清单主键
     * @return 结果
     */
    @Override
    public int deleteB2bDevDetailedlistBySids(Long[] sids)
    {
        return b2bDevDetailedlistMapper.deleteB2bDevDetailedlistBySids(sids);
    }

    /**
     * 删除智能报价清单信息
     *
     * @param sid 智能报价清单主键
     * @return 结果
     */
    @Override
    public int deleteB2bDevDetailedlistBySid(Long sid)
    {
        return b2bDevDetailedlistMapper.deleteB2bDevDetailedlistBySid(sid);
    }
}

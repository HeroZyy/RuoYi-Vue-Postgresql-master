package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bDevDetailedlist;
import com.ruoyi.system.domain.B2bProductConfigList;

/**
 * 智能报价清单Service接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface IB2bDevDetailedlistService
{

    /**
     * 查询分项配置
     */
    public List<B2bProductConfigList> getConfigByPId(Long product_id );

    /**
     * 查询产品和设备类型id对应一行数据
     */
    public B2bDevDetailedlist getDetailedListByPIdAndTypeId(Long product_id , Long dev_type );

    /**
     * 查询产品对应分项配置
     */
    public List<B2bDevDetailedlist> getDetailedListByPId(Long product_id);

    /**
     * 查询智能报价清单
     *
     * @param sid 智能报价清单主键
     * @return 智能报价清单
     */
    public B2bDevDetailedlist selectB2bDevDetailedlistBySid(Long sid);

    /**
     * 查询智能报价清单列表
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 智能报价清单集合
     */
    public List<B2bDevDetailedlist> selectB2bDevDetailedlistList(B2bDevDetailedlist b2bDevDetailedlist);

    /**
     * 新增智能报价清单
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 结果
     */
    public int insertB2bDevDetailedlist(B2bDevDetailedlist b2bDevDetailedlist);

    /**
     * 修改智能报价清单
     *
     * @param b2bDevDetailedlist 智能报价清单
     * @return 结果
     */
    public int updateB2bDevDetailedlist(B2bDevDetailedlist b2bDevDetailedlist);

    /**
     * 批量删除智能报价清单
     *
     * @param sids 需要删除的智能报价清单主键集合
     * @return 结果
     */
    public int deleteB2bDevDetailedlistBySids(Long[] sids);

    /**
     * 删除智能报价清单信息
     *
     * @param sid 智能报价清单主键
     * @return 结果
     */
    public int deleteB2bDevDetailedlistBySid(Long sid);
}

package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bDevDetailedlist;

/**
 * 智能报价清单Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface B2bDevDetailedlistMapper
{
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
     * 删除智能报价清单
     *
     * @param sid 智能报价清单主键
     * @return 结果
     */
    public int deleteB2bDevDetailedlistBySid(Long sid);

    /**
     * 批量删除智能报价清单
     *
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bDevDetailedlistBySids(Long[] sids);
}

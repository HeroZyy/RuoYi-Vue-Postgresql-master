package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bProductPfiles;

/**
 * 产品文件Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface B2bProductPfilesMapper 
{
    /**
     * 查询产品文件
     * 
     * @param sid 产品文件主键
     * @return 产品文件
     */
    public B2bProductPfiles selectB2bProductPfilesBySid(Long sid);

    /**
     * 查询产品文件列表
     * 
     * @param b2bProductPfiles 产品文件
     * @return 产品文件集合
     */
    public List<B2bProductPfiles> selectB2bProductPfilesList(B2bProductPfiles b2bProductPfiles);

    /**
     * 新增产品文件
     * 
     * @param b2bProductPfiles 产品文件
     * @return 结果
     */
    public int insertB2bProductPfiles(B2bProductPfiles b2bProductPfiles);

    /**
     * 修改产品文件
     * 
     * @param b2bProductPfiles 产品文件
     * @return 结果
     */
    public int updateB2bProductPfiles(B2bProductPfiles b2bProductPfiles);

    /**
     * 删除产品文件
     * 
     * @param sid 产品文件主键
     * @return 结果
     */
    public int deleteB2bProductPfilesBySid(Long sid);

    /**
     * 批量删除产品文件
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bProductPfilesBySids(Long[] sids);
}

package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bProductPfiles;

/**
 * 产品文件Service接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface IB2bProductPfilesService 
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
     * 批量删除产品文件
     * 
     * @param sids 需要删除的产品文件主键集合
     * @return 结果
     */
    public int deleteB2bProductPfilesBySids(Long[] sids);

    /**
     * 删除产品文件信息
     * 
     * @param sid 产品文件主键
     * @return 结果
     */
    public int deleteB2bProductPfilesBySid(Long sid);
}

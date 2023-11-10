package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductPfilesMapper;
import com.ruoyi.system.domain.B2bProductPfiles;
import com.ruoyi.system.service.IB2bProductPfilesService;

/**
 * 产品文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bProductPfilesServiceImpl implements IB2bProductPfilesService 
{
    @Autowired
    private B2bProductPfilesMapper b2bProductPfilesMapper;

    /**
     * 查询产品文件
     * 
     * @param sid 产品文件主键
     * @return 产品文件
     */
    @Override
    public B2bProductPfiles selectB2bProductPfilesBySid(Long sid)
    {
        return b2bProductPfilesMapper.selectB2bProductPfilesBySid(sid);
    }

    /**
     * 查询产品文件列表
     * 
     * @param b2bProductPfiles 产品文件
     * @return 产品文件
     */
    @Override
    public List<B2bProductPfiles> selectB2bProductPfilesList(B2bProductPfiles b2bProductPfiles)
    {
        return b2bProductPfilesMapper.selectB2bProductPfilesList(b2bProductPfiles);
    }

    /**
     * 新增产品文件
     * 
     * @param b2bProductPfiles 产品文件
     * @return 结果
     */
    @Override
    public int insertB2bProductPfiles(B2bProductPfiles b2bProductPfiles)
    {
        return b2bProductPfilesMapper.insertB2bProductPfiles(b2bProductPfiles);
    }

    /**
     * 修改产品文件
     * 
     * @param b2bProductPfiles 产品文件
     * @return 结果
     */
    @Override
    public int updateB2bProductPfiles(B2bProductPfiles b2bProductPfiles)
    {
        return b2bProductPfilesMapper.updateB2bProductPfiles(b2bProductPfiles);
    }

    /**
     * 批量删除产品文件
     * 
     * @param sids 需要删除的产品文件主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductPfilesBySids(Long[] sids)
    {
        return b2bProductPfilesMapper.deleteB2bProductPfilesBySids(sids);
    }

    /**
     * 删除产品文件信息
     * 
     * @param sid 产品文件主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductPfilesBySid(Long sid)
    {
        return b2bProductPfilesMapper.deleteB2bProductPfilesBySid(sid);
    }
}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductDownloadMapper;
import com.ruoyi.system.domain.B2bProductDownload;
import com.ruoyi.system.service.IB2bProductDownloadService;

/**
 * 产品资料下载信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class B2bProductDownloadServiceImpl implements IB2bProductDownloadService 
{
    @Autowired
    private B2bProductDownloadMapper b2bProductDownloadMapper;

    /**
     * 查询产品资料下载信息
     * 
     * @param sid 产品资料下载信息主键
     * @return 产品资料下载信息
     */
    @Override
    public B2bProductDownload selectB2bProductDownloadBySid(Long sid)
    {
        return b2bProductDownloadMapper.selectB2bProductDownloadBySid(sid);
    }

    /**
     * 查询产品资料下载信息列表
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 产品资料下载信息
     */
    @Override
    public List<B2bProductDownload> selectB2bProductDownloadList(B2bProductDownload b2bProductDownload)
    {
        return b2bProductDownloadMapper.selectB2bProductDownloadList(b2bProductDownload);
    }

    /**
     * 新增产品资料下载信息
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 结果
     */
    @Override
    public int insertB2bProductDownload(B2bProductDownload b2bProductDownload)
    {
        return b2bProductDownloadMapper.insertB2bProductDownload(b2bProductDownload);
    }

    /**
     * 修改产品资料下载信息
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 结果
     */
    @Override
    public int updateB2bProductDownload(B2bProductDownload b2bProductDownload)
    {
        return b2bProductDownloadMapper.updateB2bProductDownload(b2bProductDownload);
    }

    /**
     * 批量删除产品资料下载信息
     * 
     * @param sids 需要删除的产品资料下载信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductDownloadBySids(Long[] sids)
    {
        return b2bProductDownloadMapper.deleteB2bProductDownloadBySids(sids);
    }

    /**
     * 删除产品资料下载信息信息
     * 
     * @param sid 产品资料下载信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductDownloadBySid(Long sid)
    {
        return b2bProductDownloadMapper.deleteB2bProductDownloadBySid(sid);
    }
}

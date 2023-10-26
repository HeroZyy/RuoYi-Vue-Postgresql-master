package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bProductDownload;

/**
 * 产品资料下载信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface B2bProductDownloadMapper 
{
    /**
     * 查询产品资料下载信息
     * 
     * @param sid 产品资料下载信息主键
     * @return 产品资料下载信息
     */
    public B2bProductDownload selectB2bProductDownloadBySid(Long sid);

    /**
     * 查询产品资料下载信息列表
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 产品资料下载信息集合
     */
    public List<B2bProductDownload> selectB2bProductDownloadList(B2bProductDownload b2bProductDownload);

    /**
     * 新增产品资料下载信息
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 结果
     */
    public int insertB2bProductDownload(B2bProductDownload b2bProductDownload);

    /**
     * 修改产品资料下载信息
     * 
     * @param b2bProductDownload 产品资料下载信息
     * @return 结果
     */
    public int updateB2bProductDownload(B2bProductDownload b2bProductDownload);

    /**
     * 删除产品资料下载信息
     * 
     * @param sid 产品资料下载信息主键
     * @return 结果
     */
    public int deleteB2bProductDownloadBySid(Long sid);

    /**
     * 批量删除产品资料下载信息
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bProductDownloadBySids(Long[] sids);
}

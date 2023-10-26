package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductFavoriteMapper;
import com.ruoyi.system.domain.B2bProductFavorite;
import com.ruoyi.system.service.IB2bProductFavoriteService;

/**
 * 产品收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class B2bProductFavoriteServiceImpl implements IB2bProductFavoriteService 
{
    @Autowired
    private B2bProductFavoriteMapper b2bProductFavoriteMapper;

    /**
     * 查询产品收藏
     * 
     * @param sid 产品收藏主键
     * @return 产品收藏
     */
    @Override
    public B2bProductFavorite selectB2bProductFavoriteBySid(Long sid)
    {
        return b2bProductFavoriteMapper.selectB2bProductFavoriteBySid(sid);
    }

    /**
     * 查询产品收藏列表
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 产品收藏
     */
    @Override
    public List<B2bProductFavorite> selectB2bProductFavoriteList(B2bProductFavorite b2bProductFavorite)
    {
        return b2bProductFavoriteMapper.selectB2bProductFavoriteList(b2bProductFavorite);
    }

    /**
     * 新增产品收藏
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 结果
     */
    @Override
    public int insertB2bProductFavorite(B2bProductFavorite b2bProductFavorite)
    {
        return b2bProductFavoriteMapper.insertB2bProductFavorite(b2bProductFavorite);
    }

    /**
     * 修改产品收藏
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 结果
     */
    @Override
    public int updateB2bProductFavorite(B2bProductFavorite b2bProductFavorite)
    {
        return b2bProductFavoriteMapper.updateB2bProductFavorite(b2bProductFavorite);
    }

    /**
     * 批量删除产品收藏
     * 
     * @param sids 需要删除的产品收藏主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductFavoriteBySids(Long[] sids)
    {
        return b2bProductFavoriteMapper.deleteB2bProductFavoriteBySids(sids);
    }

    /**
     * 删除产品收藏信息
     * 
     * @param sid 产品收藏主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductFavoriteBySid(Long sid)
    {
        return b2bProductFavoriteMapper.deleteB2bProductFavoriteBySid(sid);
    }
}

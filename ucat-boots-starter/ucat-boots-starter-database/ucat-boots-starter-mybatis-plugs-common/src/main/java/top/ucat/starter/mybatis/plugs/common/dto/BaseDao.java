package top.ucat.starter.mybatis.plugs.common.dto;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.ucat.starter.mybatis.plugs.common.beans.PageVo;

public interface BaseDao<T> extends IService<T> {
    /**
     * 获取Wrapper
     *
     * @return
     */
    public abstract QueryWrapper<T> getWrapper();

    /**
     * 分页
     *
     * @param page
     * @param rows
     * @param wrapper
     * @return
     */
    public abstract IPage<T> selectPageByWrapper(int page, int rows, Wrapper<T> wrapper);


    /**
     * 根据条件获取layui table 返回参数
     *
     * @param page
     * @param rows
     * @param keyWord
     * @return
     */
    public abstract PageVo selectListPage(Integer page, Integer rows, String keyWord);


}
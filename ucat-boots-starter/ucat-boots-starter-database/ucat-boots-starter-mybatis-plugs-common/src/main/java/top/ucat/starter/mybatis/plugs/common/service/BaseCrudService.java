package top.ucat.starter.mybatis.plugs.common.service;


import top.ucat.starter.mybatis.plugs.common.beans.PageVo;

public interface BaseCrudService<T> {

    /**
     * 分页查询(用于List table)
     *
     * @param page    页数
     * @param rows    行数
     * @param keyWord 关键字(key-word)
     * @return
     * @throws Exception
     */
    public PageVo queryListPage(Integer page, Integer rows, String keyWord);

    /**
     * 新建
     *
     * @param t
     */
    public T add(T t);

    /**
     * 修改
     *
     * @param t
     */
    public T update(T t);


    /**
     * 根据id删除
     *
     * @param ids
     */
    public boolean delete(String ids);


}
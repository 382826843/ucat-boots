package top.ucat.starter.mybatis.plugs.common.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.ucat.starter.mybatis.plugs.common.beans.PageVo;
import top.ucat.starter.mybatis.plugs.common.dto.AbstractBaseEntity;
import top.ucat.starter.mybatis.plugs.common.dto.BaseDao;

import java.util.Arrays;
import java.util.List;

public class BaseCrudServiceImpl<T extends AbstractBaseEntity> implements BaseCrudService<T> {

    @Autowired
    protected BaseDao<T> baseDbService;


    @Override
    public PageVo queryListPage(Integer page, Integer rows, String keyWord) {
        return this.baseDbService.selectListPage(page, rows, keyWord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T add(T t) {
        t.setId(null);
        boolean isSave = this.baseDbService.save(t);
        return isSave ? t : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T update(T t) {
        boolean isUpdate = this.baseDbService.updateById(t);
        return isUpdate ? t : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            List<String> deleteIds = Arrays.asList(ids.split(","));
            boolean isDelete = this.baseDbService.removeByIds(deleteIds);
            return isDelete;
        }
        return false;
    }
}
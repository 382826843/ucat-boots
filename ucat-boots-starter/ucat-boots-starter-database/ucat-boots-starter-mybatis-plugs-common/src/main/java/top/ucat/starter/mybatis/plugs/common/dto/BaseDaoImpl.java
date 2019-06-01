package top.ucat.starter.mybatis.plugs.common.dto;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.ucat.starter.mybatis.plugs.common.beans.PageVo;

public class BaseDaoImpl<T extends AbstractBaseEntity> extends ServiceImpl<BaseMapper<T>, T> implements BaseDao<T> {

    @Autowired
    private BaseMapper<T> mapper;

    @Override
    public QueryWrapper<T> getWrapper() {
        return new QueryWrapper<T>();
    }

    @Override
    public IPage<T> selectPageByWrapper(int page, int rows, Wrapper<T> wrapper) {
        IPage<T> tPage = super.page(new Page<T>(page, rows), wrapper);
        return tPage;
    }


    @Override
    public PageVo selectListPage(Integer page, Integer rows, String keyWord) {
        QueryWrapper<T> wrapper = this.getWrapper();
        if (StringUtils.isNotEmpty(keyWord)) {
            String[] keywordArr = keyWord.split(",");
            for (String keyword : keywordArr) {
                String[] arr = keyword.split("\\|");
                if ("=".equals(arr[1])) {
                    wrapper.eq(arr[0], arr[2]);
                }
            }

        }
        IPage<T> tiPage = selectPageByWrapper(page, rows, wrapper);
        return new PageVo(tiPage.getTotal(), tiPage.getRecords());
    }
}
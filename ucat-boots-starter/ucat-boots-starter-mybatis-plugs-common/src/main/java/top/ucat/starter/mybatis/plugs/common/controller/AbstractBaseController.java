package top.ucat.starter.mybatis.plugs.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.common.result.SystemResult;
import top.ucat.starter.mybatis.plugs.common.beans.PageVo;
import top.ucat.starter.mybatis.plugs.common.dto.AbstractBaseEntity;
import top.ucat.starter.mybatis.plugs.common.service.BaseCrudService;

public abstract class AbstractBaseController<T extends AbstractBaseEntity, S extends BaseCrudService> {

    @Autowired
    protected S baseCurdService;


    @GetMapping("list")
    public Result getPageList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer rows, String keyword) {
        PageVo vo = baseCurdService.queryListPage(page, rows, keyword);
        return OK(vo);
    }

    @PostMapping
    public Result add(T t) {
        T returnObj = (T) baseCurdService.add(t);
        if (returnObj != null) {
            return CREATED(returnObj);
        }
        return ERROR("新建失败", null);
    }

    @PutMapping
    public Result update(T t) {
        T returnObj = (T) baseCurdService.update(t);
        if (returnObj != null) {
            return OK(returnObj);
        }
        return ERROR("修改失败", null);
    }

    @DeleteMapping
    public Result delete(String ids) {
        boolean delete = baseCurdService.delete(ids);
        return delete ? OK("删除成功") : ERROR("删除失败", null);
    }

    public Result OK() {
        return SystemResult.OK.getResult();
    }

    public Result OK(Object data) {
        return SystemResult.OK.getResult(data);
    }

    public Result OK(String msg, Object data) {
        return SystemResult.OK.getResult(msg, null, data);
    }

    public Result ERROR() {
        return SystemResult.ERROR.getResult();
    }

    public Result ERROR(Object data) {
        return SystemResult.ERROR.getResult(data);
    }

    public Result ERROR(String msg, Object data) {
        return SystemResult.ERROR.getResult(msg, null, data);
    }

    public Result CREATED() {
        return SystemResult.CREATED.getResult();
    }

    public Result CREATED(Object data) {
        return SystemResult.CREATED.getResult(data);
    }

    public Result CREATED(String msg, Object data) {
        return SystemResult.CREATED.getResult(msg, null, data);
    }

}
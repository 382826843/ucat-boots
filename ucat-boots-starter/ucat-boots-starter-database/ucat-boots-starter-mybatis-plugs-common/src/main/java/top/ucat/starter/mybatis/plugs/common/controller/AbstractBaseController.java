package top.ucat.starter.mybatis.plugs.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.common.result.SystemResult;
import top.ucat.starter.mybatis.plugs.common.beans.BaseControllerPageListBo;
import top.ucat.starter.mybatis.plugs.common.beans.PageVo;
import top.ucat.starter.mybatis.plugs.common.dto.AbstractBaseEntity;
import top.ucat.starter.mybatis.plugs.common.service.BaseCrudService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractBaseController<T extends AbstractBaseEntity, S extends BaseCrudService> {

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @ModelAttribute
    public void initReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Autowired
    protected S baseCurdService;


    @GetMapping("list")
//    public Result getPageList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer rows, String keyword) {
    public Result getPageList(BaseControllerPageListBo bo) {
        PageVo vo = baseCurdService.queryListPage(bo);
        return OK(vo);
    }

    @PostMapping
    public ResponseEntity add(T t) {
        T returnObj = (T) baseCurdService.add(t);
        if (returnObj != null) {
            return ResponseEntity.ok(OK("保存成功", returnObj));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR("新建失败", null));
    }

    @PutMapping
    public ResponseEntity update(T t) {
        T returnObj = (T) baseCurdService.update(t);
        if (returnObj != null) {
            return ResponseEntity.ok(OK("修改成功", returnObj));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR("修改失败", null));
    }

    @DeleteMapping
    public ResponseEntity delete(String ids) {
        boolean delete = baseCurdService.delete(ids);
        Result result = delete ? OK("删除成功", null) : ERROR("删除失败", null);
        return ResponseEntity.status(result.getStatus()).body(result);
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
package top.ucat.boots.example.db.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ucat.boots.example.db.entity.SysTest;
import top.ucat.boots.example.db.service.crud.TestService;
import top.ucat.starter.mybatis.plugs.common.controller.AbstractBaseController;

@RestController
@RequestMapping("test")
public class TestCrudController extends AbstractBaseController<SysTest, TestService> {

}

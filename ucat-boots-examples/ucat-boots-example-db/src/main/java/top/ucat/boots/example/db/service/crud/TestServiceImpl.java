package top.ucat.boots.example.db.service.crud;

import org.springframework.stereotype.Service;
import top.ucat.boots.example.db.entity.SysTest;
import top.ucat.starter.mybatis.plugs.common.service.BaseCrudServiceImpl;

@Service
public class TestServiceImpl extends BaseCrudServiceImpl<SysTest> implements TestService {
}

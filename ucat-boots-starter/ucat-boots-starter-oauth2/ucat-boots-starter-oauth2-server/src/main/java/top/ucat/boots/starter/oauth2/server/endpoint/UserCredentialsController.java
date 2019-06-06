package top.ucat.boots.starter.oauth2.server.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ucat.boots.common.result.Result;
import top.ucat.boots.common.result.SystemResult;
import top.ucat.boots.starter.oauth2.client.service.api.UserCredentialsService;

/**
 * @Auther: Jun
 * @Date: 2019/6/5 16:41
 * @Description:
 */
@RestController
@RequestMapping("user/credentials")
public class UserCredentialsController {

    @Autowired
    private UserCredentialsService userCredentialsService;

    /**
     * 创建用户凭证
     *
     * @param userId
     * @param userCode
     * @param userCodeType
     * @param systemType
     * @return
     */
    @PostMapping
    public Result saveUserCredentials(String userId, String userCode, String userCodeType, String systemType) {
        return SystemResult.OK.getResult(userCredentialsService.saveUserCredentials(userId, userCode, userCodeType, systemType));
    }
}

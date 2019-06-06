package top.ucat.boots.starter.oauth2.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.ucat.boots.common.utils.UuidUtil;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class OauthUserCredentialsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OauthUserCredentials getOauthUserCredential(String userCode, String userCodeType, String systemType) {
        String sqlStr = "SELECT * FROM oauth_user_credentials where user_code=? and user_code_type=? and system_type=?";

//        List<OauthUserCredentials> list = jdbcTemplate.queryForList(
//                sqlStr,
//                new Object[]{userCode, userCodeType, systemType},
//                new BeanPropertyRowMapper<OauthUserCredentials>(OauthUserCredentials.class));

        List<OauthUserCredentials> list = jdbcTemplate.query(sqlStr, new Object[]{userCode, userCodeType, systemType},
                new BeanPropertyRowMapper<OauthUserCredentials>(OauthUserCredentials.class));

        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }


    public int saveOauthUserCredential(OauthUserCredentials userCredentials) {

        userCredentials.setId(UuidUtil.getUuid());
        userCredentials.setCreatetime(new Date());
        String sql = "insert into oauth_user_credentials (id,user_id,user_code,user_code_type,system_type,createtime,updatetime)values(?,?,?,?,?,?,?)";

        Object args[] = {UuidUtil.getUuid(), userCredentials.getId(), userCredentials.getUserCode(), userCredentials.getUserCodeType(),
                userCredentials.getSystemType(), userCredentials.getCreatetime(), userCredentials.getCreatetime()
        };
        int num = jdbcTemplate.update(sql, args);
        return num;
    }
}

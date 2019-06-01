package top.ucat.boots.starter.oauth2.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;
import top.ucat.boots.starter.oauth2.client.entity.OauthUserCredentials;

@Repository
public class OauthUserCredentialsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OauthUserCredentials getOauthUserCredential(String userCode, String userCodeType, String systemType) {
        String sqlStr = "SELECT * FROM oauth_user_credentials where user_code=? and user_code_type=? and system_type=?";

        OauthUserCredentials userCredentials = jdbcTemplate.queryForObject(
                sqlStr,
                new Object[]{userCode, userCodeType, systemType},
                new BeanPropertyRowMapper<OauthUserCredentials>(OauthUserCredentials.class));

        return userCredentials;
    }

}

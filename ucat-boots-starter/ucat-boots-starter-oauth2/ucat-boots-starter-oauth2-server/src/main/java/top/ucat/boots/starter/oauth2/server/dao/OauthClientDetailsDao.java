package top.ucat.boots.starter.oauth2.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.ucat.boots.starter.oauth2.client.entity.OauthClientDetails;

@Repository
public class OauthClientDetailsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OauthClientDetails getOauthClientDetailByClientId(String clientId) {
        String sqlStr = String.format("SELECT * FROM oauth_client_details where client_id=\"%s\"", clientId);
        OauthClientDetails oauthClientDetails = jdbcTemplate.queryForObject(sqlStr, OauthClientDetails.class);
        return oauthClientDetails;
    }
}

package top.ucat.boots.starter.oauth2.server.utils;

public class KeysUtil {

    /**
     * 获取用户组合redis key
     *
     * @param userCode
     * @param userCodeType
     * @param systemType
     * @return
     */
    public static String getUserSystemKey(String userCode, String userCodeType, String systemType) {
        StringBuilder builder = new StringBuilder("uc_");
        builder.append(userCode).append(":uct_").append(userCodeType).append(":st_").append(systemType);
        return builder.toString();
    }
}

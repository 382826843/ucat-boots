package top.ucat.boots.common.utils;

import java.util.UUID;

/**
 * @Auther: Jun
 * @Date: 2019/6/5 18:11
 * @Description:
 */
public class UuidUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

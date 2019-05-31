package top.ucat.boots.starter.oauth2.client.beans.oauth;


public class UserThreadLocal {

    public static final ThreadLocal<SystemUser> LOCAL = new ThreadLocal<SystemUser>();

    public static void set(SystemUser user) {
        LOCAL.set(user);
    }

    public static SystemUser get() {
        return LOCAL.get();
    }

}

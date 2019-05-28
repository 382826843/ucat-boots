package top.ucat.boots.starter.db.beans;

/**
 * 使用ThreadLocal技术来记录当前线程中的数据源的key
 *
 * @author Zj
 */
public class DynamicDataSourceHolder {


    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 设置数据源key
     *
     * @param key
     */
    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     *
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }

    /**
     * 系统库
     */
    public static void defaultDataSource() {
        putDataSourceKey(DataSourceEnum.DEFAULT.getKey());
    }
}

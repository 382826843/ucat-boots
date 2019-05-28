package top.ucat.boots.starter.db.beans;

/**
 * @author Zj
 */
public enum DataSourceEnum {
    DEFAULT("default");

    DataSourceEnum(String key) {
        this.key = key;
    }

    DataSourceEnum() {
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
package cn.doanything.dalgen;

import java.util.ResourceBundle;

/**
 * @author wxj
 * 2023/12/15
 */
public class Config {

    private final ResourceBundle resource;

    private static final String SPLIT_STR = ",";

    public Config(String file) {
        this.resource = ResourceBundle.getBundle(file);
    }

    public String get(String key) {
        return resource.getString(key);
    }

    public String[] getArray(String key) {
        return resource.getString(key).split(SPLIT_STR);
    }

}

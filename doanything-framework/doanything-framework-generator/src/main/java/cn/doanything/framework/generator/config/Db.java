package cn.doanything.framework.generator.config;

import lombok.Data;

/**
 * @author wxj
 * 2024/1/12
 */
@Data
public class Db {

    private DataSource dataSource;

    private Table table;
}

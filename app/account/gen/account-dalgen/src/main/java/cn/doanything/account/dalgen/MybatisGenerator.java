package cn.doanything.account.dalgen;

import cn.doanything.framework.dal.mybatis.ext.ExtBaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.File;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis自动生成代码
 */
public class MybatisGenerator {

    private static final String CONFIG_FILE = "config";

    public static void main(String[] args) throws Exception {
        File f = new File(MybatisGenerator.class.getResource("/").getPath());
        String projectPath = f.getParentFile().getParentFile().getParentFile().getParentFile().getPath();
        gen(new Config(CONFIG_FILE), projectPath);
    }

    public static void gen(Config config, String projectPath) {
        String classDir = projectPath + "/app/account-infrastructure/src/main/java";
        String xmlDir = projectPath + "/app/account-infrastructure/src/main/resources/mapper";
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, xmlDir);
        pathInfo.put(OutputFile.controller, "");
        pathInfo.put(OutputFile.service, "");
        pathInfo.put(OutputFile.serviceImpl, "");

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/" + config.get("db.name") + "?useUnicode=true&characterEncoding=utf8", config.get("db.user"), config.get("db.password"))
                .globalConfig(builder -> {
                    builder.author(config.get("project.author")) // 设置作者
                            .outputDir(classDir); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("cn.doanything.account.infrastructure.persistence") // 设置父包名
                            .pathInfo(pathInfo).entity("dataobject"); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(config.getArray("db.table.list"))
                            .addTablePrefix(config.getArray("db.table.prefix"))
                            .mapperBuilder().enableFileOverride()
                            .enableBaseResultMap().enableBaseColumnList()
                            .superClass(ExtBaseMapper.class)
                            .entityBuilder().formatFileName("%sDO").enableFileOverride().addIgnoreColumns("auto_id");
                })

                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}

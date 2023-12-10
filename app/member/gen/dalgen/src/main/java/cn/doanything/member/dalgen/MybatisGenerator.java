package cn.doanything.member.dalgen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxj
 * 2023/12/10
 */
public class MybatisGenerator {

    public static void main(String[] args) {
        gen();
    }

    public static void gen() {
        String classDir = System.getProperty("user.dir") + "/app/member/app/member-infrastructure/src/main/java";
        String xmlDir = System.getProperty("user.dir") + "/app/member/app/member-infrastructure/src/main/resources/mapper";
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, xmlDir);
        pathInfo.put(OutputFile.controller, "");
        pathInfo.put(OutputFile.service, "");
        pathInfo.put(OutputFile.serviceImpl, "");

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/member?useUnicode=true&characterEncoding=utf8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("wxj") // 设置作者
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
                    builder.parent("cn.doanything.infrastructure.persistence") // 设置父包名
                            .pathInfo(pathInfo).entity("dataobject"); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tp_personal_member")
                            .addTablePrefix("tp_")
                            .mapperBuilder().enableFileOverride()//.enableBaseResultMap().enableBaseColumnList()
                            .entityBuilder().formatFileName("%sDO").entityBuilder();
                })

                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}

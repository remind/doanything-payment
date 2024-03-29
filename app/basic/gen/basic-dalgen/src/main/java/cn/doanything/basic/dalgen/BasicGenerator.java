package cn.doanything.basic.dalgen;

import cn.doanything.framework.generator.DalGenerator;
import cn.doanything.framework.generator.config.GenConfig;
import cn.hutool.setting.yaml.YamlUtil;

import java.io.File;

/**
 * @author wxj
 * 2024/1/12
 */
public class BasicGenerator {

    public static void main(String[] args) {
        GenConfig genConfig =  YamlUtil.loadByPath("config.yaml", GenConfig.class);
        DalGenerator dalGenerator = new DalGenerator(genConfig);
        File f = new File(BasicGenerator.class.getResource("/").getPath());
        String projectPath = f.getParentFile().getParentFile().getParentFile().getParentFile().getPath();
        dalGenerator.execute(projectPath, "md");
    }
}

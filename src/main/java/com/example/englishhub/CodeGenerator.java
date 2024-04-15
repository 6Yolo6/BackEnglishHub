package com.example.englishhub;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.sql.Types;

/**
 * @Author: hahaha
 * @Date: 2024/4/14 21:06
 */

public class CodeGenerator {

    public static void main(String[] args) {
        // 数据库配置信息
        String url = "jdbc:mysql://localhost:3306/english_hub?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "Root@123";

        // 当前项目路径
        String currentPath = new File("").getAbsolutePath();
        String codeOutputDir = currentPath + "/src/main/java";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("hahaha")
                            // 指定输出目录
                            .outputDir(codeOutputDir);
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.example")
                            // 设置父包模块名
                            .moduleName("englishhub")
                            // pojo 实体类包名
                            .entity("entity")
                            // Service 包名
                            .service("service")
                            // ***ServiceImpl 包名
                            .serviceImpl("service.impl")
                            // Mapper 包名
                            .mapper("mapper")
                            // Controller 包名
                            .controller("controller");
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("e_book_series")
                            // 设置过滤表前缀
                            .addTablePrefix("t_")
                            // Entity 配置
                            .entityBuilder()
                            // 开启 lombok 模型
                            .enableLombok()
                            // 覆盖已生成文件
                            .enableFileOverride()
                            // Controller 配置
                            .controllerBuilder()
                            // 开启生成 @RestController 控制器
                            .enableRestStyle()
                            // 覆盖已生成文件
                            .enableFileOverride()
                            // Service 配置
                            .serviceBuilder()
                            // 覆盖已生成文件
                            .enableFileOverride()
                            // Mapper 配置
                            .mapperBuilder()
                            // 覆盖已生成文件
                            .enableFileOverride();
                })
                // 默认的是 Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

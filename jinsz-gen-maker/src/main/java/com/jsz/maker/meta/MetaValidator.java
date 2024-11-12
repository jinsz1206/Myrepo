package com.jsz.maker.meta;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.jsz.maker.meta.enums.FileGenerateTypeEnum;
import com.jsz.maker.meta.enums.FileTypeEnum;
import com.jsz.maker.meta.enums.ModelTypeEnum;


import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Title: MetaValidator
 * @Author jsz
 * @Package com.jsz.maker.meta
 * @Date 2024/11/11 15:28
 * @description: 校验
 */
public class MetaValidator {
    public static void dovaliAndFill(Meta meta){
        // 基础信息和默认

        validAndFillMetaRoot(meta);

        //fileConfig
        
        validAndFillFileConfig(meta);


        //modelConfig
        validAndFillModelConfig(meta);


    }

    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfigDTO modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfigDTO.ModelsDTO> modelInfoList = modelConfig.getModels();
        if (CollectionUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfigDTO.ModelsDTO modelInfo : modelInfoList) {
            //输出路径默认值
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)){
                throw new MetaException("未填写fieldName");
            }

            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)){
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }


    }

    private static void validAndFillFileConfig(Meta meta) {
        Meta.FileConfigDTO fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写sourceRootPath");
        }


        String inputRootPath = fileConfig.getInputRootPath();
        if (StrUtil.isEmpty(inputRootPath)) {
            String defaultInputRootPath = ".source/"  +
                    FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
            fileConfig.setInputRootPath(defaultInputRootPath);
        }


        String outputRootPath = fileConfig.getOutputRootPath();
        if (StrUtil.isEmpty(outputRootPath)) {
            String defaultOutputRootPath = "generated";
            fileConfig.setOutputRootPath(defaultOutputRootPath);

        }


        String Configtype = fileConfig.getType();
        if (StrUtil.isEmpty(Configtype)) {
            String defaultConfigtype = FileTypeEnum.DIR.getValue();
            fileConfig.setType(defaultConfigtype);
        }


        List<Meta.FileConfigDTO.FilesDTO> files = fileConfig.getFiles();
        if (CollectionUtil.isEmpty(files)) {
            return;
        }
        for (Meta.FileConfigDTO.FilesDTO file : files) {
            String Type = file.getType();
            if(FileTypeEnum.GROUP.getValue().equals(Type)){
                continue;
            }
            //inputPath 必须填
            String inputPath = file.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写inputPath");
            }

            String outputPath = file.getOutputPath();
            if (StrUtil.isBlank(outputPath)) {
                String defaultOutputPath = inputPath;
                file.setOutputPath(defaultOutputPath);
            }

            String type = file.getType();
            if (StrUtil.isBlank(type)) {
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    file.setType(FileTypeEnum.DIR.getValue());
                }
                else {
                    file.setType(FileTypeEnum.FILE.getValue());
                }
            }

            String generatorType = file.getGenerateType();
            if (StrUtil.isBlank(generatorType)) {
                if (inputPath.endsWith(".ftl")){
                    file.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                }else {
                    file.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }

        }

    }

    private static void validAndFillMetaRoot(Meta meta) {
        // 校验并填充默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "代码生成器模板");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "jsz");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.jsz");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);

    }
}

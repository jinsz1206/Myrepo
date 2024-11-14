package com.jsz.maker.template;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jsz.maker.meta.Meta;
import com.jsz.maker.meta.enums.FileGenerateTypeEnum;
import com.jsz.maker.meta.enums.FileTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: TemplateMaker
 * @Author jsz
 * @Package com.jsz.maker.template
 */
public class TemplateMaker {

    public static void main(String[] args) {
        // 指定原始项目路径
        String projectPath = System.getProperty("user.dir");
        String originProjectPath = new File(projectPath).getParent() + File.separator + "jinsz-demo/acm-template";
        String fileInputPath = "src/com/jsz/acm/MainTemplate.java";

        Meta.ModelConfigDTO.ModelsDTO modelInfo = new Meta.ModelConfigDTO.ModelsDTO();
        modelInfo.setFieldName("outputText");
        modelInfo.setType("String");
        modelInfo.setDefaultValue("sum = ");

        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");
//        第一次挖坑
        String str = "Sum: ";

        //第二次测试


        makeTemplate(meta,1856620056754012160L, originProjectPath, fileInputPath, modelInfo,str);

    }

    /**
     *
     * @param newMeta
     * @param id
     * @param originProjectPath
     * @param fileInputPath
     * @param modelInfo
     * @param searchStr
     * @return
     */
    private static long makeTemplate(Meta newMeta ,Long id, String originProjectPath, String fileInputPath,Meta.ModelConfigDTO.ModelsDTO modelInfo,String searchStr){

        // 没有 id 则生成
        if (id == null) {
            id = IdUtil.getSnowflakeNextId();
        }



        // 复制目录
        String projectPath = System.getProperty("user.dir");
        String tempDirPath = projectPath + File.separator + ".temp";
        String templatePath = tempDirPath + File.separator + id;
        if (!FileUtil.exist(templatePath)) {
            FileUtil.mkdir(templatePath);
            FileUtil.copy(originProjectPath, templatePath, true);
        }
        // 一、输入信息


        // 2. 输入文件信息
        String sourceRootPath = templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();
        // 注意 win 系统需要对路径进行转义
        sourceRootPath = sourceRootPath.replaceAll("\\\\", "/");
        System.out.println(sourceRootPath);


        String fileOutputPath = fileInputPath + ".ftl";
        System.out.println(fileOutputPath);

        // 3. 输入模型参数信息


        // 二、使用字符串替换，生成模板文件
        String fileInputAbsolutePath = sourceRootPath + File.separator + fileInputPath;
        String fileOutputAbsolutePath = sourceRootPath + File.separator + fileOutputPath;

        String fileContent = null;
        // 如果已有模板文件，说明不是第一次制作，则在模板基础上再次挖坑
        if (FileUtil.exist(fileOutputAbsolutePath)) {
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        } else {
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }
        String replacement = String.format("${%s}", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, searchStr, replacement);

        // 输出模板文件
        FileUtil.writeUtf8String(newFileContent, fileOutputAbsolutePath);


        // 三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";


        // Meta.FileConfigDTO.FilesDTO配置
        Meta.FileConfigDTO.FilesDTO fileInfo = new Meta.FileConfigDTO.FilesDTO();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());

        //已有 meta 文件 ，不是第一次制作，则在原有的基础上进行修改
        if (FileUtil.exist(metaOutputPath)) {
            Meta oldMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            //1. 追加配置参数
            List<Meta.FileConfigDTO.FilesDTO> fileInfoList = oldMeta.getFileConfig().getFiles();
            fileInfoList.add(fileInfo);
            List<Meta.ModelConfigDTO.ModelsDTO> modelsInfoList = oldMeta.getModelConfig().getModels();
            modelsInfoList.add(modelInfo);

            //配置去重
            oldMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            oldMeta.getModelConfig().setModels(distinctModels(modelsInfoList));

            //2. 输出元信息文件
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(oldMeta),metaOutputPath);
        }else {


            // 1. 构造配置参数


            Meta.FileConfigDTO fileConfig = new Meta.FileConfigDTO();
            newMeta.setFileConfig(fileConfig);
            fileConfig.setSourceRootPath(sourceRootPath);
            List<Meta.FileConfigDTO.FilesDTO> fileInfoList = new ArrayList<>();
            fileConfig.setFiles(fileInfoList);
            fileInfoList.add(fileInfo);

            Meta.ModelConfigDTO modelConfig = new Meta.ModelConfigDTO();
            newMeta.setModelConfig(modelConfig);
            List<Meta.ModelConfigDTO.ModelsDTO> modelInfoList = new ArrayList<>();
            modelConfig.setModels(modelInfoList);
            modelInfoList.add(modelInfo);

            //2.输出元信息文件
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(newMeta), metaOutputPath);
        }




        return id;

    }



    /**
     * 文件去重
     * @param fileInfoList
     * @return
     */
    private static List<Meta.FileConfigDTO.FilesDTO> distinctFiles(List<Meta.FileConfigDTO.FilesDTO> fileInfoList) {

        ArrayList<Meta.FileConfigDTO.FilesDTO> newFileInfoList = new ArrayList<>(fileInfoList.stream()
                .collect(
                        Collectors.toMap(Meta.FileConfigDTO.FilesDTO::getInputPath, o -> o, (e, r) -> r)
                ).values());
        return newFileInfoList;

    }

    /**
     * 模型去重
     * @param ModelInfoList
     * @return
     */
    private static List<Meta.ModelConfigDTO.ModelsDTO> distinctModels(List<Meta.ModelConfigDTO.ModelsDTO> ModelInfoList) {

        ArrayList<Meta.ModelConfigDTO.ModelsDTO> newModelInfoList = new ArrayList<>(ModelInfoList.stream()
                .collect(
                        Collectors.toMap(Meta.ModelConfigDTO.ModelsDTO::getFieldName, o -> o, (e, r) -> r)
                ).values());
        return newModelInfoList;

    }


}

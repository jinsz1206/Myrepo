# ${name}

> ${description}
>
> 作者：${author}
>

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：

示例命令：
```
generator <命令> <选项参数>
```

```
generator generate <#list modelConfig.models as modelInfo>-<#if modelInfo.abbr??>${modelInfo.abbr} </#if></#list>
```




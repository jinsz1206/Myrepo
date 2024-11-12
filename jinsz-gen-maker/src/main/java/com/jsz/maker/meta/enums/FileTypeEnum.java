package com.jsz.maker.meta.enums;

/**
 * @Title: FileTypeEnum
 * @Author jsz
 * @Package com.jsz.maker.meta.enums
 * @Date 2024/11/11 17:39
 * @description: 枚举类
 */


public enum FileTypeEnum {

    DIR("目录", "dir"),
    FILE("文件", "file"),
    GROUP("文件组", "group");

    private final String text;

    private final String value;

    FileTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}

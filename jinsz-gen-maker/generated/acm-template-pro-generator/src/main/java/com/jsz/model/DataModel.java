package com.jsz.model;


import lombok.Data;



@Data
public class DataModel {


    /**
    *   是否生成循环
    */
    private boolean loop = false  ;


    /**
    *   作者注释
    */
    private String author = "jinsz"  ;


    /**
    *   输出信息
    */
    private String outputText = "sum = "  ;

}

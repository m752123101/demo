package com.example.demo.batch;

/**
 * @title ConstantEnum
 * @Description  使用的常量枚举
 * @Date 2019/9/8 9:29
 * @Created by hp
 */
public enum ConstantEnum {
    less("less",1),
    many("many",1000);

    ConstantEnum(String desc, Integer chunkValue) {
        this.desc = desc;
        this.chunkValue = chunkValue;
    }

    private String desc;
    private Integer chunkValue;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getChunkValue() {
        return chunkValue;
    }

    public void setChunkValue(Integer chunkValue) {
        this.chunkValue = chunkValue;
    }
}

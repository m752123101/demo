package com.example.demo.batch.helloworld.listenner;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;

/**
 * chunk基本的监听器
 *
 * @author hanwen.dong
 * @date 2019/8/29 18:55
 * @Description auto
 */

public class ChunkListener {
    @AfterChunk
    public void afterChunktest(){
        System.out.println("每条处理一条数据chunkListener会进行一次操作 after");
    }
    @BeforeChunk
    public void beforeChunk(){
        System.out.println("每条处理一条数据chunkListener会进行一次操作 before");
    }
}


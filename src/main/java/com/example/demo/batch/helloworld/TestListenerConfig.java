package com.example.demo.batch.helloworld;

import com.example.demo.batch.helloworld.listenner.ChunkListener;
import com.example.demo.batch.helloworld.listenner.JobListenner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 测试监听器的job
 *
 * @author hanwen.dong
 * @date 2019/8/29 18:53
 * @Description auto
 */
@Configuration
public class TestListenerConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job testListenerJob() {
        return jobBuilderFactory.get("testListenerJob1").listener(new JobListenner()).start(testListenerStep()).build();
    }

    @Bean
    public Step testListenerStep() {
        return stepBuilderFactory.get("testListenerStep").<String, String>chunk(1)
                .faultTolerant().listener(new ChunkListener())
                .reader(testListenerReader()).writer(testListenerWriter()).build();
    }

    @Bean
    public ItemReader<String> testListenerReader() {
        return new ListItemReader<String>(Arrays.asList("hello1", "hello2", "hello3"));
    }

    @Bean
    public ItemWriter<String> testListenerWriter() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> items) throws Exception {
                items.stream().forEach(t ->
                        System.out.println(t));
            }
        };
    }
}

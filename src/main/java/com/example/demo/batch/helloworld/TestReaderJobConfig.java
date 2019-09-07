package com.example.demo.batch.helloworld;

import com.example.demo.batch.helloworld.reader.TestReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 自己实现接口调用itemreader
 *
 * @author hanwen.dong
 * @date 2019/8/29 19:35
 * @Description auto
 */
@Configuration
public class TestReaderJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Bean
    public Job testReaderJob(){
        return jobBuilderFactory.get("testReaderJob")
                .start(testReaderStep()).build();
    }
    @Bean
    public Step testReaderStep(){
        return stepBuilderFactory.get("testReaderStep")
                .<String,String>chunk(3)
                .reader(testMyReader())
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        System.out.println(items);
                    }
                }).build();

    }
    @Bean
    public ItemReader<String> testMyReader(){
        return new TestReader(Arrays.asList("1","2","3","4","5","6"));
    }
}

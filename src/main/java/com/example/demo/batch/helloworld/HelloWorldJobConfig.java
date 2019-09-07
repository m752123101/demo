package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanwen.dong
 * @date 2019/8/17 08:57
 * @Description auto
 */
@Configuration
public class HelloWorldJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWroldJob() {
        return jobBuilderFactory.get("helloWorldJob").start(helloStep()).build();
    }

    private Step helloStep() {
        return stepBuilderFactory.get("helloStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("===========================");
                System.out.println("hello world");
                System.out.println("===========================");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}

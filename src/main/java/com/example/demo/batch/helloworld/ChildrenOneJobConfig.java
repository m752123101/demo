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
 * TODO
 *
 * @author hanwen.dong
 * @date 2019/8/17 19:03
 * @Description auto
 */
@Configuration
public class ChildrenOneJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job childrenOneJob(){
        return jobBuilderFactory.get("childrenOneJob")
                .start(childrenOneStep()).build();
    }
    @Bean
    public Step childrenOneStep() {
        return stepBuilderFactory.get("childrenOneStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("childrenOneStep");
                System.out.println("=====================");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }


}

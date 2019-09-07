package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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
 * @date 2019/8/17 09:30
 * @Description auto
 */
@Configuration
@EnableBatchProcessing
public class MoreStepJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Bean
    public Job moreStepJob(){
           return jobBuilderFactory.get("moreStepJob1").start(step1())
                   .on("COMPLETED").to(step2())
                   .from(step2()).on("COMPLETED").to(step3())
                  .end()
                   .build();
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========================");
                System.out.println("step3");
                System.out.println("=========================");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========================");
                System.out.println("step2");
                System.out.println("=========================");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=========================");
                System.out.println("step1");
                System.out.println("=========================");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}

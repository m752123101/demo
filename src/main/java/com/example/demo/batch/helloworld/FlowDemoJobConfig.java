package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanwen.dong
 * @date 2019/8/17 17:38
 * @Description auto
 */
@Configuration
@EnableBatchProcessing
public class FlowDemoJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowDemoJob() {
        return jobBuilderFactory.get("flowDemoJob")
                .start(demoFlow()).next(flowDemoStep3()).end().build();
    }

    @Bean
    public Step flowDemoStep1() {
        return stepBuilderFactory.get("flowDemoStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("flowStep1");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step flowDemoStep2() {
        return stepBuilderFactory.get("flowDemoStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("flowStep2");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step flowDemoStep3() {
        return stepBuilderFactory.get("flowDemoStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("step3");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Flow demoFlow() {
        return new FlowBuilder<Flow>("demoFlow").start(flowDemoStep1()).next(flowDemoStep2()).end();
    }

}

package com.example.demo.batch.helloworld.filetoout;

import com.example.demo.batch.helloworld.listenner.JobListenner;
import com.example.demo.mapping.Citydao;
import com.example.demo.mapping.bean.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import java.util.List;

/**
 * 读文件，写数据库
 *
 * @author hanwen.dong
 * @date 2019/9/7 17:20
 * @Description auto
 */

@Configuration
public class FileToOutJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Citydao citydao;

    @Bean
    public Job fileToOutjob3(){
        return jobBuilderFactory.get("dbToOutJob3").listener(new JobListenner())
                .start(fileToOutStep())
                .build();

    }
    @Bean
    public Step fileToOutStep(){
        return stepBuilderFactory.get("fileToOutStep").<City,City>chunk(1000)
                .reader(fileToOutReader()).processor(new ItemProcessor<City, City>() {
                    @Override
                    public City process(City item) throws Exception {
                        System.out.println("=============processor=============");
                        item.setId(null);
                        System.out.println(item.toString());
                        System.out.println("=============processor=============s");
                        return item;
                    }
                }).writer(fileToOutWriter()).build();
    }
    @Bean
    @StepScope
    public ItemWriter<? super City> fileToOutWriter() {
        return new ItemWriter<City>() {
            @Override
            public void write(List<? extends City> items) throws Exception {
                items.forEach(t ->{
                    System.out.println("wrting ....");
                    citydao.insert(t);
                });
            }
        };
    }

@Bean
@StepScope
    public FlatFileItemReader<City> fileToOutReader() {
        FlatFileItemReader<City> flatFileItemReader=new FlatFileItemReader<>();
        //设置文件
        flatFileItemReader.setResource(new ClassPathResource("city.txt"));
        //跳过第一行
        flatFileItemReader.setLinesToSkip(1);
        //解析文件
        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
        //设置对应字段
        delimitedLineTokenizer.setNames(new String[]{"id","name","countryCode","district","population"});
        //设置映射
        DefaultLineMapper<City> defaultLineMapper=new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(new FieldSetMapper<City>() {
            @Override
            public City mapFieldSet(FieldSet fieldSet) throws BindException {
                City city=new City();
                city.setId(fieldSet.readInt("id"));
                city.setName(fieldSet.readString("name"));
                city.setCountryCode(fieldSet.readString("countryCode"));
                city.setDistrict(fieldSet.readString("district"));
                city.setPopulation(fieldSet.readInt("population"));
                return city;
            }
        });
    flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }
}

package com.example.demo.batch.helloworld.filetoout.readermanyfile;

import com.example.demo.batch.ConstantEnum;
import com.example.demo.batch.helloworld.listenner.CommonListenner;
import com.example.demo.mapping.bean.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import java.util.List;

/**
 * 读取多个文件
 *
 * @author hanwen.dong
 * @date 2019/9/8 09:25
 * @Description auto
 */
@Configuration
public class ReaderManyFileConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Value("classpath:/city*.txt")
    private Resource[] resources;

    @Bean
    public Job readerManyFileJob1() {
        return jobBuilderFactory.get("readerManyFileJob1")
                .listener(new CommonListenner())
                .start(readerManyFileStep())
                .build();
    }

    @Bean
    public Step readerManyFileStep() {
        return stepBuilderFactory.get("readerManyFileStep")
                .<City, City>chunk(ConstantEnum.less.getChunkValue())
                .reader(readerManyFileReader())
                .writer(new ItemWriter<City>() {
                    @Override
                    public void write(List<? extends City> items) throws Exception {
                        items.stream().forEach(item -> System.out.println(((City) item).toString()));
                    }
                })
                .build();
    }


    @Bean
    public MultiResourceItemReader<City> readerManyFileReader() {
        MultiResourceItemReader<City> multiResourceItemReader = new MultiResourceItemReader<>();
        multiResourceItemReader.setDelegate(singleFileReader());
        multiResourceItemReader.setResources(resources);
        return multiResourceItemReader;
    }

    /**
     * 普通的读取文件
     *
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemReader<City> singleFileReader() {
        FlatFileItemReader<City> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new ClassPathResource("city.txt"));
        //解析数据
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id", "name", "countryCode", "district", "population"});
        //把解析出的一行映射为city对象
        DefaultLineMapper<City> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(tokenizer);
        defaultLineMapper.setFieldSetMapper(new FieldSetMapper<City>() {
                                                @Override
                                                public City mapFieldSet(FieldSet fieldSet) throws BindException {
                                                    City city = new City();
                                                    city.setId(fieldSet.readInt("id"));
                                                    city.setName(fieldSet.readString("name"));
                                                    city.setCountryCode(fieldSet.readString("countryCode"));
                                                    city.setDistrict(fieldSet.readString("district"));
                                                    city.setPopulation(fieldSet.readInt("population"));
                                                    return city;
                                                }
                                            }
        );
        flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }

}

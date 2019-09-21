package com.example.demo.batch.helloworld.xml2out;

import com.example.demo.batch.ConstantEnum;
import com.example.demo.batch.helloworld.listenner.CommonListenner;
import com.example.demo.mapping.Citydao;
import com.example.demo.mapping.bean.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取xml文件写库
 *
 * @author hanwen.dong
 * @date 2019/9/8 08:02
 * @Description auto
 */

@Configuration
public class Xml2OutJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Citydao citydao;

    @Bean
    public Job xml2OutJob2() {
      return   jobBuilderFactory.get("" +
              "")
                .start(xml2OutStep())
                .listener(new CommonListenner())
                .build();
    }

    @Bean
    public Step xml2OutStep() {
        return stepBuilderFactory.get("xml2OutStep")
                .<City, City>chunk(ConstantEnum.less.getChunkValue())
                .reader(xml2outReader())
                .processor(new ItemProcessor<City, City>() {
                    @Override
                    public City process(City item) throws Exception {
                        System.out.println("process executing");
                        item.setId(null);
                        System.out.println("city"+item.toString());
                        return item;
                    }

                }).writer(new ItemWriter<City>() {
                    @Override
                    public void write(List<? extends City> items) throws Exception {
                        items.stream().forEach(item ->
                        {
                            System.out.println("execute writing ...");
                            citydao.insert(item);

                        });
                    }
                }).build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<City> xml2outReader() {
        StaxEventItemReader<City> reader = new StaxEventItemReader<>();
        //设置数据源（xml）
        reader.setResource(new ClassPathResource("city.xml"));
        //设置解析器
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        Map<String, Class> map = new HashMap<>(1);
        //设置对应映射
        map.put("city", City.class);
        xStreamMarshaller.setAliases(map);
        reader.setUnmarshaller(xStreamMarshaller);
        reader.setFragmentRootElementName("city");
        return reader;
    }
}

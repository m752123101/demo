package com.example.demo.batch.helloworld.db2out;

import com.example.demo.mapping.bean.City;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author hanwen.dong
 * @date 2019/9/3 19:06
 * @Description auto
 */
@Configuration

public class DbToOutConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;

    @Bean
    public Job dbToOutJob(){
       return jobBuilderFactory.get("dbToOutJob6").listener(new Db2OutListener())
                .start(dbToOutStep()).build();
    }
    @Bean
    public Step dbToOutStep(){
        return stepBuilderFactory.get("dbToOutStep")
                .<City,City>chunk(2)
                .reader(dbToOutReader())
                .writer(dbToOutWriter())
                .build();
    }
    @Bean
    @StepScope
    public JdbcPagingItemReader<City> dbToOutReader(){
        JdbcPagingItemReader<City> jdbcReader=new JdbcPagingItemReader<>();
        jdbcReader.setDataSource(dataSource);
        jdbcReader.setFetchSize(2);
        jdbcReader.setRowMapper(new RowMapper<City>() {
            @Override
            public City mapRow(ResultSet resultSet, int i) throws SQLException {
                City city=new City();
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));
                city.setCountryCode(resultSet.getString(3));
                city.setDistrict(resultSet.getString(4));
                city.setPopulation(resultSet.getInt(5));

                return city;
            }
        });
        MySqlPagingQueryProvider provider=new MySqlPagingQueryProvider();
        provider.setFromClause("from city");
        provider.setSelectClause("id,name,countryCode,district,population");
        Map<String, Order> sort= new HashMap<>();
        sort.put("id",Order.DESCENDING);
        //必须要设定排序
        provider.setSortKeys(sort);
        jdbcReader.setQueryProvider(provider);
        return jdbcReader;
    }
    @Bean
    @StepScope
    public ItemWriter<City> dbToOutWriter(){
        return new ItemWriter<City>() {
            @Override
            public void write(List<? extends City> items) throws Exception {
                items.stream().forEach( dto ->
                        System.out.println(((City) dto).toString()));
                }
        };
    }

}

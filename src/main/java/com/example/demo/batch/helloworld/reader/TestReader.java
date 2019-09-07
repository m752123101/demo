package com.example.demo.batch.helloworld.reader;

import org.springframework.batch.item.*;

import java.util.Iterator;
import java.util.List;

/**
 * 自定义reader
 *
 * @author hanwen.dong
 * @date 2019/8/29 19:38
 * @Description auto
 */

public class TestReader implements ItemReader<String> {
    private Iterator<String> iterator;

    public TestReader(List<String> cx) {
        iterator = cx.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }
}

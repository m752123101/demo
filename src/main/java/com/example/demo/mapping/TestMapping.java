package com.example.demo.mapping;

import com.example.demo.mapping.bean.Test;
import org.springframework.stereotype.Repository;

/**
 * @author hanwen.dong
 * @date 2019/8/3 09:30
 * @Description auto
 */
@Repository
public interface TestMapping {

   public Test selectTest();
}

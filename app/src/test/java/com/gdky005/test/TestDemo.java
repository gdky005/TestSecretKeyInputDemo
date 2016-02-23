package com.gdky005.test;

import org.junit.Test;

/**
 * Created by WangQing on 16/2/23.
 */
public class TestDemo {

    @Test
    public void addition_isCorrect() throws Exception {

        String userKey = "4";

        boolean isMatch = userKey.matches("^[A-Za-z0-9]+$");


        System.out.println("是否匹配成功：" + isMatch);
    }
}

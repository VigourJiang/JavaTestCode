package com.ume.guavatest;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.List;

public class Reflect {
    @Test
    public void test() {
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {};
        System.out.println(stringListTok.getType());        
    }
}

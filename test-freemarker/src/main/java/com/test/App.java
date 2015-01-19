package com.test;

import com.google.common.collect.Lists;
import freemarker.ext.beans.SimpleMapModel;
import freemarker.template.*;

import java.util.*;
import java.io.*;

public class App {

    public static void main(String[] args) throws Exception {

        /* --
        --------------------------------------------------------------------- */
        /* You should do this ONLY ONCE in the whole application life-cycle:       */    
    
        /* Create and adjust the configuration */
        Configuration cfg = new Configuration();

        cfg.setDirectoryForTemplateLoading(new File("./"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));

        /* ----------------------------------------------------------------------- */    
        /* You usually do these for many times in the application life-cycle:      */    

        /* Create a data-model */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        root.put("aaa", "d");

        root.put("list", Lists.newArrayList("a", "ab", "abc"));

        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        root.put("custom", new CustomMethod());
        /* Get the template */
        Template temp = cfg.getTemplate("test.ftl");

        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);

    }
}

class CustomModel implements TemplateHashModel{

    @Override
    public TemplateModel get(String key) throws TemplateModelException {
       return new SimpleScalar("hello nokia");
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return false;
    }
}

class CustomMethod implements TemplateMethodModelEx{

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        System.out.println(arguments.size());
        return ((SimpleNumber)arguments.get(1)).getAsNumber().intValue() * 20;
    }
}
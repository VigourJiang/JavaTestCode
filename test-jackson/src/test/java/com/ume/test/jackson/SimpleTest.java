package com.ume.test.jackson;

import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleTest
{
    @Test
    public void testReadWrite() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MyValue value =
            mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        System.out.print(value);
    }

    @Test
    public void testWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MyValue mv = new MyValue();
        mv.age = 32;
        mv.name = "JFQ";

        // save to file 
        File f = new File("result.json");
        mapper.writeValue(f, mv);

        // write as byte array
        byte[] jsonBytes = mapper.writeValueAsBytes(mv);
        String s =
            Charset.defaultCharset().decode(ByteBuffer.wrap(jsonBytes))
                .toString();
        System.out.println(s);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // write as string
        String jsonString = mapper.writeValueAsString(mv);
        System.out.println(jsonString);
    }

    @Test
    public void testWriteArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MyValue mv = new MyValue();
        mv.age = 32;
        mv.name = "JFQ";
        MyValue[] myvalues = new MyValue[3];
        for (int i = 0; i < myvalues.length; i++) {
            myvalues[i] = mv;
        }
        System.out.println(mapper.writeValueAsString(myvalues));
    }

    @Test
    public void testWriteList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MyValue mv = new MyValue();
        mv.age = 32;
        mv.name = "JFQ";
        List<MyValue> myvalues = new LinkedList<MyValue>();
        for (int i = 0; i < 4; i++) {
            myvalues.add(mv);
        }
        System.out.println(mapper.writeValueAsString(myvalues));
    }

    @Test
    public void testWriteMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MyValue mv = new MyValue();
        mv.age = 32;
        mv.name = "JFQ";
        Map<String, MyValue> myvalues = new HashMap<String, MyValue>();
        for (int i = 0; i < 4; i++) {
            myvalues.put(Integer.toString(i), mv);
        }
        System.out.println(mapper.writeValueAsString(myvalues));
    }

}

package com.ume.test.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.IOException;

public class TreeModelTest
{
    @Test
    public void testWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode root = (ObjectNode) mapper.readTree("{}");
        root.put("name", "Jhon");
        ObjectNode other = (ObjectNode) root.with("other");
        other.put("type", "student");

        ArrayNode children = (ArrayNode) root.withArray("children");
        children.add(324);
        children.add("DKKK");
        children.addObject().put("SubChild", "I am sub Child");

        String json = mapper.writeValueAsString(root);
        System.out.println(json);
    }

}

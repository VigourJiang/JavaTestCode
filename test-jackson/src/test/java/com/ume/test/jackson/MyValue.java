package com.ume.test.jackson;


// Note: can use getters/setters as well; here we just use public fields directly:
public class MyValue {
    public String name;
    public int age;
    @Override
    public String toString(){
        return "MyValue: " + name + " " + age;
    }
    // NOTE: if using getters/setters, can keep fields `protected` or `private`
}

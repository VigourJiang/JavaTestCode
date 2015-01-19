package com.ume.guavatest;

import com.google.common.base.Charsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.Arrays;

public class StringTest {
    @Test
    public void testJoiner() {
        Joiner joiner = Joiner.on(':').skipNulls();
        assertEquals(joiner.join("abc", "def", null, "123"), "abc:def:123");

        Joiner joiner2 = Joiner.on(':').useForNull("Null");
        assertEquals(joiner2.join("abc", "def", null, "123"), "abc:def:Null:123");

        String str2 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        assertEquals(str2, "1,5,7");
    }

    @Test
    public void testSplitter() {
        assertArrayEquals(
            ",a,,b,".split(","),
            new String[] { "", "a", "", "b" });
    }

    @Test
    public void testGuavaSplitter() {
        Iterable<String> strs = Splitter.on(',')
            .trimResults()
            .omitEmptyStrings()
            .split("foo,bar,,   qux");
        assertArrayEquals(
            Iterables.toArray(strs, String.class),
            new String[] { "foo", "bar", "qux" });
    }

    @Test
    public void testGuavaSplitter2() {
        Iterable<String> strs = Splitter.on(',')
            .omitEmptyStrings()
            .split("foo,bar,,   qux");
        assertArrayEquals(
            Iterables.toArray(strs, String.class),
            new String[] { "foo", "bar", "   qux" });
    }

    @Test
    public void testCharMatcher() {
        String string = "";

        // remove control characters
        string = "abc def   123";
        String noControl = CharMatcher.WHITESPACE.removeFrom(string);
        assertEquals(noControl, "abcdef123");

        // only the digits
        string = "abc def   123";
        String theDigits = CharMatcher.DIGIT.retainFrom(string);
        assertEquals(theDigits, "123");

        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        string = "  abc def   123  ";
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        assertEquals(spaced, "abc def 123");

        // star out all digits
        string = "  abc def   123  ";
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*");
        assertEquals(noDigits, "  abc def   ***  ");
        
        // eliminate all characters that aren't digits or lowercase
        string = "  ABC def   123  ";
        String lowerAndDigit = CharMatcher.JAVA_DIGIT
            .or(CharMatcher.JAVA_LOWER_CASE)
            .retainFrom(string);
        assertEquals(lowerAndDigit, "def123");
    }

}

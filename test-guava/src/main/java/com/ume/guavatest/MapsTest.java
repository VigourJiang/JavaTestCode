package com.ume.guavatest;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapsTest {
    @Test
    public void test() {
        assertEquals(DigestUtils.md5Hex("1234567890"), "e807f1fcf82d132f9bb018ca6738a19f");

        String str = "to generate two byte strings with the same hash. " +
            "Since there are a finite number of MD5 outputs (2128), but an" +
            " infinite number of possible inputs, it has long been known that " +
            "such collisions must exist, but it had been previously believed to " +
            "be impractically difficult to find one. The result is that the MD5" +
            " hash of some information no longer uniquely identifies it. If I " +
            "present you with information such as a public key, its MD5 hash " +
            "might not uniquely identify it; I may have a second public key with " +
            "the same MD5 hash. However, the present attacks require the ability " +
            "to choose both messages of the collision. They do not make it easy " +
            "to perform a preimage attack, finding a message with a specified MD5 " +
            "hash, or a second preimage attack, finding a message with the same " +
            "MD5 hash as a given message. Thus, old MD5 hashes, made before these " +
            "attacks were known, are safe for now. In particular, old digital " +
            "signatures can still be considered reliable.";

        assertEquals(DigestUtils.md5Hex(str), "93e9d534652e69518a692c949c42ad51");
    }
}

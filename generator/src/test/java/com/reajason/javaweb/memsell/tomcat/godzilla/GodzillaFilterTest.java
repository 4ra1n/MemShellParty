package com.reajason.javaweb.memsell.tomcat.godzilla;

import com.reajason.javaweb.memsell.GodzillaGenerator;
import com.reajason.javaweb.util.ClassUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ReaJason
 * @since 2024/11/24
 */
class GodzillaFilterTest {

    GodzillaGenerator godzillaGenerator = new GodzillaGenerator();
    String pass = "pass";
    String key = "key";
    String headerName = "User-Agent";
    String headerValue = "test";

    @Test
    void generate() {
        String className = "org.apache.utils.CommonFilter";
        byte[] bytes = godzillaGenerator.generate(GodzillaFilter.class, className, pass, key, headerName, headerValue);
        Object obj = ClassUtils.newInstance(bytes);
        assertEquals(className, obj.getClass().getName());
        assertEquals(pass, ClassUtils.getFieldValue(obj, "pass"));
        assertEquals("3c6e0b8a9c15224a", ClassUtils.getFieldValue(obj, "key"));
        assertEquals(headerName, ClassUtils.getFieldValue(obj, "headerName"));
        assertEquals(headerValue, ClassUtils.getFieldValue(obj, "headerValue"));
        System.out.println(Base64.encodeBase64String(bytes));
    }
}
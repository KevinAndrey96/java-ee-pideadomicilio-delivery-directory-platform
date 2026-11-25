package com.infinitec.pideadomicilio.tools;

import org.junit.Assert;
import org.junit.Test;

public class URLParamEncoderTest {

    @Test
    public void testSafeAlphanumericEncoding() {
        String input = "ComercioElCorral123";
        String encoded = URLParamEncoder.encode(input);
        Assert.assertEquals("Alphanumeric string without unsafe chars should be identical", input, encoded);
    }

    @Test
    public void testSpaceEncoding() {
        String input = "Pide a domicilio";
        String encoded = URLParamEncoder.encode(input);
        Assert.assertEquals("Space must be encoded as %20", "Pide%20a%20domicilio", encoded);
    }

    @Test
    public void testSpecialSymbolsEncoding() {
        String input = "pizza+pasta&sushi#1";
        String encoded = URLParamEncoder.encode(input);
        Assert.assertTrue("Plus symbol must be hex encoded", encoded.contains("%2B"));
        Assert.assertTrue("Ampersand symbol must be hex encoded", encoded.contains("%26"));
        Assert.assertTrue("Hash symbol must be hex encoded", encoded.contains("%23"));
    }

    @Test
    public void testAccentedCharactersEncoding() {
        String input = "Bogotá";
        String encoded = URLParamEncoder.encode(input);
        Assert.assertFalse("Accented character must not remain as literal raw unicode", encoded.contains("á"));
        Assert.assertTrue("Encoded string must contain percent escapes", encoded.contains("%"));
    }
}

package com.infinitec.pideadomicilio.tools;

import org.junit.Assert;
import org.junit.Test;

public class EncryptorTest {

    private static final String DEFAULT_KEY = "Inf12345Inf12345";
    private static final String DEFAULT_IV = "RandomInftVector";

    @Test
    public void testEncryptAndDecryptRoundTrip() {
        String original = "DeliverySecret2026";
        String encrypted = Encryptor.encrypt(DEFAULT_KEY, DEFAULT_IV, original);
        Assert.assertNotNull("Encrypted string should not be null", encrypted);
        Assert.assertNotEquals("Encrypted string should differ from plaintext", original, encrypted);

        String decrypted = Encryptor.decrypt(DEFAULT_KEY, DEFAULT_IV, encrypted);
        Assert.assertEquals("Decrypted string must match original", original, decrypted);
    }

    @Test
    public void testKnownAdminCredentialVector() {
        String plaintext = "admin123";
        String expectedBase64 = "mmPyKBphNnsKMxyT3y1R6w==";

        String encrypted = Encryptor.encrypt(DEFAULT_KEY, DEFAULT_IV, plaintext);
        Assert.assertEquals("Encrypted credential must match deterministic AES vector", expectedBase64, encrypted);

        String decrypted = Encryptor.decrypt(DEFAULT_KEY, DEFAULT_IV, expectedBase64);
        Assert.assertEquals("Decrypted credential must yield original password", plaintext, decrypted);
    }

    @Test
    public void testNullSafety() {
        Assert.assertNull("Null value should return null", Encryptor.encrypt(DEFAULT_KEY, DEFAULT_IV, null));
        Assert.assertNull("Null key should return null", Encryptor.encrypt(null, DEFAULT_IV, "test"));
        Assert.assertNull("Null IV should return null", Encryptor.encrypt(DEFAULT_KEY, null, "test"));

        Assert.assertNull("Null cipher text should return null", Encryptor.decrypt(DEFAULT_KEY, DEFAULT_IV, null));
        Assert.assertNull("Null key on decrypt should return null", Encryptor.decrypt(null, DEFAULT_IV, "test"));
        Assert.assertNull("Null IV on decrypt should return null", Encryptor.decrypt(DEFAULT_KEY, null, "test"));
    }

    @Test
    public void testDifferentPlaintextsProduceDifferentCiphertexts() {
        String c1 = Encryptor.encrypt(DEFAULT_KEY, DEFAULT_IV, "PasswordAlpha");
        String c2 = Encryptor.encrypt(DEFAULT_KEY, DEFAULT_IV, "PasswordBeta");

        Assert.assertNotNull(c1);
        Assert.assertNotNull(c2);
        Assert.assertNotEquals("Distinct plaintexts must produce distinct ciphertexts", c1, c2);
    }
}

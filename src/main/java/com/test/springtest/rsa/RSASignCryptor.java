package com.test.springtest.rsa;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.UrlBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

public class RSASignCryptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RSASignCryptor.class);
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    public static final String ENCODING = "utf-8";
    public static final String X509 = "X.509";
//    private static final int MAX_ENCRYPT_BLOCK = 117;   //RSA最大加密明文大小
//    private static final int MAX_DECRYPT_BLOCK = 128;   //RSA最大解密密文大小


    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(ENCODING));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key.getBytes(ENCODING));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
        return pubKey;
    }

    public String getSignData(Map<String, String> params, String sep) {
        LOGGER.debug("data:{}, sep:{}", params, sep);
        if (params instanceof TreeMap) {
            StringBuilder builder = new StringBuilder();
            boolean isFirst = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (StringUtils.isEmpty(entry.getKey()) || StringUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                if (!isFirst) {
                    builder.append(sep);
                }
                builder.append(entry.getKey()).append("=").append(entry.getValue());
                isFirst = false;
            }
            return builder.toString();
        } else {
            Map<String, String> sortedMap = new TreeMap<String, String>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            return getSignData(sortedMap, sep);
        }
    }

    public static String sign(String signData, String signKey) {
        LOGGER.debug("signData:{}, signKey:{}", signData, signKey);
        String ret = null;
        try {
            PrivateKey priKey = getPrivateKey(signKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(signData.getBytes(ENCODING));
            byte[] signed = signature.sign();
            ret = new String(UrlBase64.encode(signed), ENCODING);
        } catch (Exception e) {
            LOGGER.error("sign error, data: {}, key: {}, Exp: {}", signData, signKey, e);
        }
        LOGGER.debug("sign:{}", ret);
        return ret;
    }

    public static boolean verifySign(String signData, String signKey, String sign) {
        LOGGER.debug("signData:{}, signKey:{}, sign:{}", signData, signKey, sign);
        try {
            PublicKey pubKey = getPublicKey(signKey);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(signData.getBytes(ENCODING));
            return signature.verify(UrlBase64.decode(sign.getBytes(ENCODING)));
        } catch (Exception e) {
            LOGGER.error("verify error, data: {}, key: {}, sign: {}, Exp: {}", signData, signKey, sign, e);
        }
        return false;
    }

    public static String encrypt(String encryptKey, String plainText) {
        LOGGER.debug("encryptKey: {}, plainText: {}", encryptKey, plainText);
        try {
            RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(encryptKey);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] plainData = plainText.getBytes(ENCODING);
            LOGGER.debug("plainHex:" + Hex.encodeHexString(plainData));

            int MAX_ENCRYPT_BLOCK = publicKey.getModulus().bitLength() / 8 - 11;
            LOGGER.debug("MAX_ENCRYPT_BLOCK: " + MAX_ENCRYPT_BLOCK);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] cache;
            for (int i = 0, offset = 0, len = plainData.length; offset < len;) {
                if (len - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(plainData, offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(plainData, offset, len - offset);
                }
//                LOGGER.debug("cahe:" + Hex.encodeHexString(cache));
                out.write(cache, 0, cache.length);
                offset = (++i) * MAX_ENCRYPT_BLOCK;
            }
            byte[] enBytes = out.toByteArray();
            out.close();
            LOGGER.debug(Hex.encodeHexString(enBytes));
            return new String(Base64.encodeBase64(enBytes), ENCODING);
        } catch (Exception e) {
            LOGGER.error("rsa encrypt exception={}", e);
        }
        return null;
    }

    public  static String decrypt(String decryptKey, String encryptText) {
        //LOGGER.debug("decryptKey: {}, encryptText: {}", decryptKey, encryptText);
        try {
            RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(decryptKey);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] enData = Base64.decodeBase64(encryptText.getBytes(ENCODING));
            //LOGGER.debug(Hex.encodeHexString(enData));

            int MAX_DECRYPT_BLOCK = privateKey.getModulus().bitLength() / 8;
            //LOGGER.debug("MAX_DECRYPT_BLOCK: " + MAX_DECRYPT_BLOCK);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] cache;
            for (int i = 0, offset = 0, len = enData.length; offset < len;) {
                if (len - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(enData, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(enData, offset, len - offset);
                }
//                LOGGER.debug("cahe:" + Hex.encodeHexString(cache));
                out.write(cache, 0, cache.length);
                offset = (++i) * MAX_DECRYPT_BLOCK;
            }
            byte[] deBytes = out.toByteArray();
            out.close();
            //LOGGER.debug("decodeHex:" + Hex.encodeHexString(deBytes));
            return new String(deBytes, ENCODING);
        } catch (Exception e) {
            LOGGER.error("rsa decrypt exception={}", e);
        }
        return null;
    }
}
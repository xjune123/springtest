package com.test.springtest.rsa;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/6/3 下午2:19
 */
public class RsaTestMain {
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW4tqK9A9fXIzYj4BfprYvwzr69mVh+Cj373j8pYXBWEJ7SpshKEWCfIwJ/UpeHAQ/kOSxmQiR9clxNG79HqRghvZPba9RrQXzS0UH+N6eCiy/VXf/VD7568pgX+VreygZlJmrHJP3dKfsYtxpthTpPg5URmsvYcPoMXVHThdcQQIDAQAB";
    private static final String source = "{\"token\":\"3d99793e429c4358b57f77dbbfce92a5\",\"cusId\":\"A060000030\",\"tradeCode\":\"04001504\",\"reqNo\":\"93592cc94d6541828f9632cd1ffa146f\",\"refNo\":\"b387d0166e1e42088a2166d0a7982117\",\"sign\":\"NjHcWscdE3HxC43YFD8lGpYj89g-yniz3uSNPM3ET1rx_PZQNPk8qPLF-uItw8Q5ltPlQBCMNHluPdQVgNsaHTUHgwdaAFCqEnfdpzZYOqKe96qJJdvgmVDjuGF8dKX8jrtSjrSjBkADhPcAkF-PycpORgqgHbF9fdWVQumbHjo.\",\"data\":\"V5u3+pYDwAacVIxB9rgn1ruEPi03fRGTUlWUUHLp9jZojHeTw78E1SmX6t2hpPe4Z5SFdh3yxNychYKYH0qAZmizvVlPyXR0lscI+mQSgZmd85G9B2rkTtr9GsDC7QFAtkMAOtVnb07dUnBEc2gjrcf5hC/obIEscbbNOg3+soBmXrQrsfnqzTUjd89sjHcsYvvXrOCSUYX9XDoT5lOvmbTsXFvgZR4AfuxraAHEiBNRm8klq8erePFCqqADo887DVkSY/LJlDxY8meD45A0PYz97f/jq6ZRWy7L9gh3pgokrjEWJ1Qr6XIHdrreDL4XjKsWgEnExTTB3hPBZR9QNFNsJtXocP2wqQZshNDSIKk2qrNxrOwSaEKzsGfOGesJJCz8ehVy2Bbw1r/c5RqLgKs7m2D03jrQ0dYSsD2c+PRUF6zyu0x8EMWRGBih8Ql/EaLia3OhJvZHEwkjbW/MQcT4rbMW79lFNg+qi3tlwvVUeKguplsH2EhkqS1fGp9Rf4+bjkPrZUFIIxyQe8LeCP0uaAWgSUZT31iLSbVWabPOEpDVEb36xAlxCyL7zGlm4wbq4bFQEOjaG74kILif8n5+zbkXYZZOvUDTIKX5FPKeENUyC1eDJ6wwYEhROdJXZnNwFlfrjFes6hkcCahltbqmzE3n0JIRMADWmUgYI/RfmhXv7on5qj3IktRpLspQ5R3SAkM6Eia8AF8HHDXiJ6Zq8a83U06ra/lqzJEXlp3VWGvAvWZ8ca94T9hQARcrQL6IEnuLf+Iq3uH3+V4eoJXjUGODGnEBQWKx7noo1qhK2ANIxXRdseTg3+itlHgd1jfRbBQHhItOm+4KntJGjQeENf7LsX/qda9FoePMVTedKInj7RKOopHr56OmaAfzlpPdK3s1zAEQWRBIKkeCHHsRr+mzyO1k0zSZuCHqK4xfmt/rFrbJ4PlT/YQq0vy1xzhDAvoc05cwdfQVGW9v1/xuvhzXCTI6YQq6r1xjIQbQV2DfxHCCXOPVP9Uvu6xi\",\"reqUrl\":\"http://47.96.246.94:9999/ecp/bindCard\"}";

    public static void main(String[] args) {
        String decodeData = RSASignCryptor.decrypt(publicKey, source);
        System.out.println(decodeData);
    }
}

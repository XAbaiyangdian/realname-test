package com.xiongan.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

//对称加密  密码必须是128bit 16字节
public class SM4Utils {
    private static final String name = "SM4";                               //算法名字
    private static final String transformation = "SM4/CBC/PKCS5Padding";    //加密模式以及短快填充方式
    private static final String Default_iv = "0123456789abcdef";            //加密使用的初始向量

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encryptToBase64(String str, String key) throws Exception {
        byte[] strbytes = SM3Utils.getBytesUtf8(str);
        byte[] keybytes = SM3Utils.getBytesUtf8(key);
        byte[] outputbytes = encrypt(strbytes, keybytes);
        return Base64.getEncoder().encodeToString(outputbytes);

    }
    public static String decryptFromBase64(String str, String key) throws Exception {
        byte[] strbytes = Base64.getDecoder().decode(str);
        byte[] keybytes = SM3Utils.getBytesUtf8(key);
        byte[] outputbytes = decrypt(strbytes, keybytes);
        return new String(outputbytes);
    }
    public static byte[] encrypt(byte[] inputBytes, byte[] key) throws Exception {
        // 获取加密实例
        Cipher c = Cipher.getInstance(transformation);
        // 根据密钥的字节数组创建 SecretKeySpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, name);
        // 创建 IvParameterSpec 对象，使用默认向量和字符集
        IvParameterSpec ivParameterSpec = new IvParameterSpec(SM3Utils.getBytesUtf8(Default_iv));
        // 初始化加密实例
        c.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        // 返回加密后的字节数组
        return c.doFinal(inputBytes);
    }

    public static byte[] decrypt(byte[] inputBytes, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, name);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(SM3Utils.getBytesUtf8(Default_iv));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(inputBytes);
    }
}

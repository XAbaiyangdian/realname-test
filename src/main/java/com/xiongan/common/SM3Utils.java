package com.xiongan.common;

import org.bouncycastle.crypto.digests.SM3Digest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

//散列函数 获取摘要
public class SM3Utils {
    public static String hashToBase64(String str) {
        byte[] bytes = getBytesUtf8(str);
        byte[] hash = hash(bytes);
        return Base64.getEncoder().encodeToString(hash);
    }
    public static byte[] hash(byte[] bytes) {
        //创建 SM3Digest 对象
        SM3Digest sm3 = new SM3Digest();
        //将输入字符串转为字节数组，并使用该字节数组更新摘要对象的内部状态，以便进行计算
        sm3.update(bytes, 0, bytes.length);
        //创建一个输出字节数组，调用 doFinal 方法完成哈希计算，并将结果存入输出数组
        byte[] output = new byte[sm3.getDigestSize()];
        sm3.doFinal(output, 0);
        //返回摘要字节数组
        return output;
    }
    public static byte[] getBytesUtf8(String str){
        byte[] bytes = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return bytes;
    }


}

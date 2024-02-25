package com.xiongan.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {

    public static String sign(Map map, String secretKey) {
        String linkString = createLinkString(map);
        linkString += "&secretKey=" + secretKey;
        return SM3Utils.hashToBase64(linkString).toUpperCase();
    }

    public static Boolean checkSign(Map map, String signature, String secretKey) {
        String linkString = createLinkString(map);
        linkString += "&secretKey=" + secretKey;
        if (SM3Utils.hashToBase64(linkString).toUpperCase().equals(signature)) {
            return true;
        }
        return false;
    }

    public static String createLinkString(Map<String, Object> params) {
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        StringBuffer buffer = new StringBuffer();
        for (String key : keys) {
            Object value = params.get(key);
            if (!keys.get(0).equals(key)) {
                buffer.append("&");
            }
            buffer.append(key + "=" + value);
        }
        return buffer.toString();
    }
}

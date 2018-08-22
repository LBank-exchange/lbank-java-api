package com.lbank.java.api.sdk.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.ndktools.javamd5.Mademd5;

/**
 * @author chen.li
 */
public class LBankJavaApiSdkUtil {
    /**
     * 根据参数加密
     *
     * @param str
     * @param apiKey
     * @param secretKey
     * @return
     */
    public static String getSign(String str, String apiKey,String secretKey) {
        StringBuffer buffer = new StringBuffer();
        String result = null;
        if (StringUtils.isNoneBlank(str)) {
            str = str + "&api_key=" + apiKey;
            String[] split = StringUtils.split(str, "&");
            Arrays.sort(split);
            for (int i = 0; i < split.length; i++) {
                buffer.append(split[i]).append("&");
            }
            result = buffer.toString();
            result = result.substring(0,result.length()-1);
        } else {
        	result = buffer.append("api_key=").append(apiKey).toString();
        }
        Mademd5 md = new Mademd5();
        StringBuilder sign = new StringBuilder(md.toMd5(result));
        return  RSAUtil.sign(sign.toString(),secretKey);
    }
}

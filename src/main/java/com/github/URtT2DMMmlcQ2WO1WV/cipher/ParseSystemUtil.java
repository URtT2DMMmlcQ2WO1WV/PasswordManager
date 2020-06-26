

package com.github.URtT2DMMmlcQ2WO1WV.cipher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ParseSystemUtil {
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将base64字符串转换为普通字符串
     * @param base64Str
     * @return
     */
    public static String parseBase64Str2NormalStr(String base64Str) throws IOException {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(base64Str) , "utf-8");
    }

    /**
     * 将普通字符串转换成base64字符串
     * @param buf
     * @return
     */
    public static String parseNormalStr2Base64Str(String buf) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(buf.getBytes("utf-8"));
    }

}

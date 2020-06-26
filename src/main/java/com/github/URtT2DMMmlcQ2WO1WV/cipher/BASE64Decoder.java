package com.github.URtT2DMMmlcQ2WO1WV.cipher;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BASE64Decoder {
    public byte[] decodeBuffer(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        try{
            return decoder.decode(str.getBytes("utf-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}

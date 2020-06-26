package com.github.URtT2DMMmlcQ2WO1WV.cipher;
import	java.io.IOException;

import java.util.Base64;

public class BASE64Encoder {
    public String encode(byte[] byt){
        Base64.Encoder encoder = Base64.getEncoder();
        try{
            return encoder.encodeToString(byt);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

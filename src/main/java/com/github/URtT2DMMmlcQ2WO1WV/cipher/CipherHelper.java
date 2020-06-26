

package com.github.URtT2DMMmlcQ2WO1WV.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class CipherHelper {
    private SecretKeys keys;

    public SecretKeys getKeys() {
        return keys;
    }

    public void setKeys(SecretKeys keys) {
        this.keys = keys;
    }

    public String RSA_encrypt_public(String str) throws Exception {
        return ParseSystemUtil.parseByte2HexStr(RSAUtil.encryptByPublicKey(str.getBytes("utf-8") , keys.getRSA_Public_Key()));
    }

    public String RSA_decrypt_private(String str) throws Exception {
        return new String(RSAUtil.decryptByPrivateKey(ParseSystemUtil.parseHexStr2Byte(str) , keys.getRSA_Private_Key()));
    }

    public String RSA_encrypt_private(String str) throws Exception {
        return ParseSystemUtil.parseByte2HexStr(RSAUtil.encryptByPrivateKey(str.getBytes("utf-8") , keys.getRSA_Private_Key()));
    }

    public String RSA_decrypt_public(String str) throws Exception {
        return new String(RSAUtil.decryptByPublicKey(ParseSystemUtil.parseHexStr2Byte(str) , keys.getRSA_Public_Key()));
    }

    public String AES_encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return ParseSystemUtil.parseByte2HexStr(AESUtil.encryptAES(str.getBytes("utf-8") , AESUtil.strKey2SecretKey(keys.getAES_Key())));
    }

    public String AES_decrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return new String(AESUtil.decryptAES(ParseSystemUtil.parseHexStr2Byte(str) , AESUtil.strKey2SecretKey(keys.getAES_Key())));
    }

    public void genNewKey(String RSASeed , String AESSeed) throws Exception {
        this.keys.setAES_Key(AESUtil.getStrKeyAES(AESSeed));
        Map<String , Object> pair = RSAUtil.genKeyPair(RSASeed);
        this.keys.setRSA_Public_Key(RSAUtil.getPublicKey(pair));
        this.keys.setRSA_Private_Key(RSAUtil.getPrivateKey(pair));
    }

    public CipherHelper(SecretKeys keys) {
        this.keys = keys;
    }

    public CipherHelper(String RSASeed , String AESSeed) throws Exception {
        this.keys = new SecretKeys("" , "" , "");
        this.genNewKey(RSASeed, AESSeed);
    }
}

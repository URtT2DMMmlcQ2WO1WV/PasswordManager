package com.github.URtT2DMMmlcQ2WO1WV.passwordmanager;

import com.github.URtT2DMMmlcQ2WO1WV.cipher.AESUtil;
import com.github.URtT2DMMmlcQ2WO1WV.cipher.ParseSystemUtil;
import com.github.URtT2DMMmlcQ2WO1WV.gen.PasswordGen;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DatabaseInitializer {
    public void initializeDatabase(Database data , String name , String mainPassword , String assistPassword , String entropyFilePath , String keyFilePath , String entropy) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        KeyFile keyFile = new KeyFile();
        keyFile.number = String.valueOf(new SecureRandom().nextLong()) + new SecureRandom().nextLong() + new SecureRandom().nextLong();
        PasswordGen passgen = new PasswordGen();
        String seed = passgen.getNewPassword(name , String.valueOf(keyFile.number) , new File(entropyFilePath) , entropy);
        String AESKey = AESUtil.getStrKeyAES(seed);
        String encryptKey = AESUtil.getStrKeyAESWithSeedOnly(keyFile.number + mainPassword);
        String encryptedAESKey = ParseSystemUtil.parseByte2HexStr(AESUtil.encryptAES(AESKey.getBytes("utf-8") , AESUtil.strKey2SecretKey(encryptKey)));

    }
}

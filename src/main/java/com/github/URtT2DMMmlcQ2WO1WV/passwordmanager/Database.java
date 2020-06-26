package com.github.URtT2DMMmlcQ2WO1WV.passwordmanager;



import com.github.URtT2DMMmlcQ2WO1WV.cipher.CipherHelper;
import com.github.URtT2DMMmlcQ2WO1WV.cipher.SecretKeys;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
    protected String name;
    protected String mainAESKey;
    protected String assistPassword;
    protected CipherHelper RSAKeys;
    protected HashMap<String , String> passwords;
    protected String keyFilePath;
}

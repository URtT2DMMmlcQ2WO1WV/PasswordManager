

package com.github.URtT2DMMmlcQ2WO1WV.cipher;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SecretKeys implements Serializable {
    private String RSA_Public_Key;
    private String RSA_Private_Key;
    private String AES_Key;
    private String hash;

    public SecretKeys(String RSA_Public_Key, String RSA_Private_Key, String AES_Key) {
        this.RSA_Public_Key = RSA_Public_Key;
        this.RSA_Private_Key = RSA_Private_Key;
        this.AES_Key = AES_Key;
        hash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
    }

    public SecretKeys(String RSA_Public_Key, String AES_Key) {
        this.RSA_Public_Key = RSA_Public_Key;
        this.AES_Key = AES_Key;
        hash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
    }

    public String getRSA_Public_Key() {
        return RSA_Public_Key;
    }

    public void setRSA_Public_Key(String RSA_Public_Key) {
        this.RSA_Public_Key = RSA_Public_Key;
        hash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
    }

    public String getRSA_Private_Key() {
        return RSA_Private_Key;
    }

    public void setRSA_Private_Key(String RSA_Private_Key) {
        this.RSA_Private_Key = RSA_Private_Key;
        hash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
    }

    public String getAES_Key() {
        return AES_Key;
    }

    public void setAES_Key(String AES_Key) {
        this.AES_Key = AES_Key;
        hash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
    }

    public String getHash() {
        return hash;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException , CipherException{
        AES_Key = (String) ois.readObject();

        RSA_Private_Key = (String) ois.readObject();

        RSA_Public_Key = (String) ois.readObject();

        hash = (String) ois.readObject();
        String vhash = Tool.md5Password(this.RSA_Public_Key + this.RSA_Private_Key + this.AES_Key);
        if(hash.equals(vhash) == false){
            throw new CipherException("Secret key verify failed");
        }
    }
}

package com.github.URtT2DMMmlcQ2WO1WV.gen;
import java.io.UnsupportedEncodingException;
import	java.util.Random;
import	java.util.ArrayList;

import java.io.File;
import java.security.SecureRandom;
import java.util.*;
import com.github.URtT2DMMmlcQ2WO1WV.md.*;
import com.github.URtT2DMMmlcQ2WO1WV.cipher.BASE64Encoder;
import com.github.URtT2DMMmlcQ2WO1WV.cipher.Tool;

public class PasswordGen {

    public String getNewPassword(String userName , String webSite , File keyFile , String entropy) throws UnsupportedEncodingException {

        String pass1 = gen(userName , webSite , keyFile , entropy);
        String pass2 = gen(userName , webSite , keyFile , entropy);
        String pass3 = gen(userName , webSite , keyFile , entropy);

        ArrayList<String> list = new ArrayList<String>();
        list.add(pass1);
        list.add(pass2);
        list.add(pass3);

        String mainPass = maxStr(new String[]{pass1 , pass2 , pass3});

        list.remove(mainPass);

        Random r = new Random();

        String start = null;
        String end = null;

        if(r.nextInt(100) >= 50){
            start = list.get(0);
            end = list.get(1);
        }else{
            start = list.get(1);
            end = list.get(0);
        }

        start = start.substring(0 , r.nextInt(5) + 10);
        end = end.substring(end.length() - (r.nextInt(5) + 10));

        String result = start + mainPass + end;

        return result;

    }

    private String gen(String userName , String webSite , File keyFile , String entropy) throws UnsupportedEncodingException {
        ArrayList<String> entropyList = breakUpEntropy(entropy);
        String id = UUID.randomUUID().toString();
        SecureRandom r = new SecureRandom();
        String salt = String.valueOf(r.nextLong());
        String tmp = entropyList.get(0);
        String fileSalt = Tool.readFile(keyFile.getPath());
        MessageDigestEncrypt md5 = MessageDigestEncrypt.MD5();
        MessageDigestEncrypt sha1 = MessageDigestEncrypt.SHA1();
        MessageDigestEncrypt sha256 = MessageDigestEncrypt.SHA256();
        MessageDigestEncrypt sha512 = MessageDigestEncrypt.SHA512();
        for(int i = 1 ; i <= (r.nextInt(50) + 50) ; i++){
            tmp = md5.encrypt(md5.encrypt(userName + webSite + id) + salt + i + entropyList.get(1));
            tmp = sha1.encrypt(sha1.encrypt(tmp) + salt + i + entropyList.get(1));
            tmp = sha256.encrypt(sha256.encrypt(tmp) + salt + i + entropyList.get(1));
            salt = String.valueOf(r.nextLong());
        }
        salt = String.valueOf(r.nextLong());
        tmp = sha256.encrypt(sha256.encrypt(tmp) + salt + entropyList.get(2));
        salt = String.valueOf(r.nextLong());
        tmp = sha256.encrypt(sha256.encrypt(tmp) + salt + entropyList.get(3));
        salt = String.valueOf(r.nextLong());
        tmp = sha512.encrypt(sha512.encrypt(tmp) + salt + entropyList.get(4));
        salt = String.valueOf(r.nextLong());
        tmp = sha512.encrypt(sha512.encrypt(tmp) + salt + entropyList.get(5));
        salt = String.valueOf(r.nextLong());
        tmp = sha512.encrypt(sha512.encrypt(tmp) + salt + fileSalt + entropyList.get(6));
        BASE64Encoder base64 = new BASE64Encoder();
        tmp = base64.encode(tmp.getBytes("utf-8"));
        tmp = tmp + "12345678901234567890*****-----_____/////%%%%%" + base64.encode((userName + webSite).getBytes("utf-8")) + UUID.randomUUID().toString();
        String result = shuffleString(tmp);
        result = shuffleString(result);
        result = shuffleString(result);
        return result;
    }

    private String shuffleString(String str){
        char[] tmp = str.toCharArray();
        Character[] tmp2 = new Character[str.length()];
        for(int i = 1 ; i <= str.length() ; i++){
            tmp2[i - 1] = tmp[i - 1];
        }
        List<Character> charList = Arrays.asList(tmp2);
        Collections.shuffle(charList);
        for(int i = 1 ; i <= charList.size() ; i++){
            tmp[i - 1] = charList.get(i - 1);
        }
        return new String(tmp);
    }

    private ArrayList<String> breakUpEntropy(String entropy){
        double tmp = entropy.length() / 7;
        int numberOfCopies = (int) Math.floor(tmp);
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1 ; i <= 7 ; i++){
            list.add(entropy.substring((i - 1) * numberOfCopies , i * numberOfCopies));
        }
        return list;
    }

    private String maxStr(String str[]) {
        int index=0;
        for(int i=1;i<=str.length-1;i++) {
            if(str[i].length()>str[index].length()) {
                index=i;
            }
        }
        return str[index];
    }


}

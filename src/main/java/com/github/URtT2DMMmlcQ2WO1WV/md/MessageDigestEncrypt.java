package com.github.URtT2DMMmlcQ2WO1WV.md;
import	java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestEncrypt {
    private String algorithm;

    public MessageDigestEncrypt(String algorithm){
        this.algorithm = algorithm;
    }

    public static MessageDigestEncrypt MD5(){
        return new MessageDigestEncrypt("MD5");
    }

    public static MessageDigestEncrypt SHA256(){
        return new MessageDigestEncrypt("SHA-256");
    }

    public static MessageDigestEncrypt SHA512(){
        return new MessageDigestEncrypt("SHA-512");
    }

    public static MessageDigestEncrypt SHA1(){
        return new MessageDigestEncrypt("SHA-1");
    }

    public String encrypt(String strText){

        String strResult = null;

        if (strText != null && strText.length() > 0)
        {
            try
            {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

                messageDigest.update(strText.getBytes());

                byte byteBuffer[] = messageDigest.digest();


                StringBuffer strHexString = new StringBuffer();

                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }

                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }

        return strResult;

    }
}

package bestjavaapps;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class cryptomain{
    
    private final KeySpec myKeySpec;
    private final SecretKeyFactory mySecretKeyFactory;
    private final Cipher cipher;
    byte keyasbytes[];
    private final String myEncryptionKey;
    SecretKey key;
    String text;
    
public cryptomain(String cryptokey) throws Exception               //constructor
{
    myEncryptionKey = "myencryptionkey";                //this is encryption key string
    keyasbytes = myEncryptionKey.getBytes();       //converting string to byte array
    myKeySpec = new DESKeySpec(keyasbytes);
    mySecretKeyFactory = SecretKeyFactory.getInstance("DES");
    cipher = Cipher.getInstance("DES");
    key = mySecretKeyFactory.generateSecret(myKeySpec);
}

/*Encrypt Module */
public String encrypt(String unencryptedstring){  //encrypt function.parameter string

    String encryptedstring = null;
try
{    
cipher.init(Cipher.ENCRYPT_MODE,key);
byte[] plainText = unencryptedstring.getBytes();        //getting the byte form of plaintext
byte[] cipherText = cipher.doFinal(plainText);         //generating the cipher from plaintext4
BASE64Encoder encoder = new BASE64Encoder();           //ciphertext is in byte form we need to get string
encryptedstring = encoder.encode(cipherText);          //here cipherText is converted from byte form to string and stored in encryptedstring
}
catch(InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
System.out.println("Exception!");
}

return encryptedstring;
}

/*Decrypt module */
public String decrypt(String encryptedstring){
    
    String decryptedstring = null;
    
   try
   {
   cipher.init(Cipher.DECRYPT_MODE, key);
   BASE64Decoder decoder = new BASE64Decoder();
   
   byte encryptedtext[] = decoder.decodeBuffer(encryptedstring); 
   byte plaintext[] = cipher.doFinal(encryptedtext);          //cipher to plain text
   decryptedstring = bytes2String(plaintext);
   }
   catch(InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e){
       System.out.println("Exception!");
   }
   
   return decryptedstring;
}

/*returns a string from an array of bytes*/

private static String bytes2String(byte[] bytes){
    StringBuilder stringbuffer = new StringBuilder();
    
    for(int i=0;i<bytes.length;i++)
        stringbuffer.append((char)bytes[i]);

return stringbuffer.toString();
}

}

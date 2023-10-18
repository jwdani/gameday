package com.gameday.security;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gameday.constants.GamedayConstants;


public class GamedayPasswordEncoder implements PasswordEncoder {
	
	private static final Logger LOGGER = LogManager.getLogger(GamedayPasswordEncoder.class);
    
    Cipher ecipher;
    Cipher decipher;

    byte[] salt = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };

    int iterationCount = 19;	
  
	public GamedayPasswordEncoder() {
		
		try 
        {  
			
            KeySpec keySpec = new PBEKeySpec(GamedayConstants.CIPHER_PASSPHRASE.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            
            ecipher = Cipher.getInstance(key.getAlgorithm());
            decipher = Cipher.getInstance(key.getAlgorithm());
            
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            decipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } 
        catch (Exception e) {
        	
        	LOGGER.error(e.toString());
        }   		
	}

	@Override
	public String encode(CharSequence rawPassword) {
		
		try {
			
            byte[] utf8 = rawPassword.toString().getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            
            return java.util.Base64.getMimeEncoder().encodeToString(enc);
        } 
        catch (Exception e) {
        	
             LOGGER.error(e.toString());
        }         
        
        return null;
	}
	
	public String decode(CharSequence rawPassword) {
		
		try { 
			
            byte[] dec = java.util.Base64.getMimeDecoder().decode(rawPassword.toString());
            byte[] utf8 = decipher.doFinal(dec);

            return new String(utf8, "UTF8");
        } 
        catch (Exception e) {
        	
        	LOGGER.error(e.toString());
        }
        
        return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		if(encode(rawPassword.toString()).equals(encodedPassword))
			return true;
		
		return false;
	}
}

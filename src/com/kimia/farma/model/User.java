package com.kimia.farma.model;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class User extends Identitas {

	private String kd_user;
	private String password;
	private Integer level;

	public User() {
		kd_user = "";
		level=1;
	}
	
	public User(String kd_user, String password, Integer level) {
		super();
		this.kd_user = kd_user;
		if(password.length()>=20){
			password=decrypt(password);
		}
		this.password = password;
		this.level = level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getLevel() {
		return level;
	}
    private static byte[] key = {
            0x2d, 0x2a, 0x2d, 0x42, 0x55, 0x49, 0x4c, 0x44, 0x41, 0x43, 0x4f, 0x44, 0x45, 0x2d, 0x2a, 0x2d
        };
     
        public static String encrypt(String plainText) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
                String encryptedString = new String(Base64.getEncoder().encode(cipherText),"UTF-8");
                return encryptedString;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public static String decrypt(String encryptedText) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] cipherText = Base64.getDecoder().decode(encryptedText.getBytes("UTF8"));
                String decryptedString = new String(cipher.doFinal(cipherText),"UTF-8");
                return decryptedString;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

	public User(String kd_user) {
		this.kd_user = kd_user;
	}
	public void setPassword(String password) {
		if(password.length()>=20){
			password=decrypt(password);
		}
		this.password = encrypt(password);
	}
	public String getPassword() {
		return password;
	}

	public void setKd_user(String kd_user) {
		this.kd_user = kd_user;
	}

	public String getKd_user() {
		return kd_user;
	}

}

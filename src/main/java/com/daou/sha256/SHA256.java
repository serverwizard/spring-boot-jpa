package com.daou.sha256;

import java.security.MessageDigest;

public class SHA256 {

	public static String SHA256generator(String password) {
		
		String base = password;
		
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for(int i=0; i<hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}

package com.emergentideas.mediaarchive.services;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;


public class CryptoService {

	public static String createDigest(File file) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		FileInputStream fis = new FileInputStream(file);
		byte[] dataBytes = new byte[1024];

		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1) {
			digest.update(dataBytes, 0, nread);
		}
		byte[] mdbytes = digest.digest();

		// convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

}

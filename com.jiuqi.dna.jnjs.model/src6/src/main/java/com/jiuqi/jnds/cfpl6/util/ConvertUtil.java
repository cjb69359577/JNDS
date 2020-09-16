package com.jiuqi.jnds.cfpl6.util;

import java.util.UUID;

public class ConvertUtil {

	public static String toHexString(byte[] byteArray) {
		if (byteArray == null || byteArray.length < 1) {
			return null;
		}

		final StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] & 0xff) < 0x10) {
				hexString.append("0");
			}
			hexString.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return hexString.toString().toUpperCase();
	}
	
	public static UUID hexStringToUUID(String str) {
		StringBuffer buffer = new StringBuffer(str.toLowerCase());
		buffer.insert(8, "-");
		buffer.insert(13, "-");
		buffer.insert(18, "-");
		buffer.insert(23, "-");
		return UUID.fromString(buffer.toString());
	}
	
}

package com.gorgaz.gazmyas.utils;

import java.util.UUID;

public class ChatUtil {

	public static boolean isValidUUID (String str) {
		try {
			UUID.fromString(str);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

}

package com.kintiger.platform.framework.util;


/**
 * url参数加密解密
 * @author xxping
 *
 */
public class ParamsEncryptUtil {

	public static String Encrypt(String str) {

		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			chs[i] = (char) (chs[i] ^ '0');
		}
		return new String(chs);
	}

	public static void main(String[] srgs) {
		String ss = ParamsEncryptUtil.Encrypt("pingwang@kintiger.com");
		System.out.println(ss);
		String sss = ParamsEncryptUtil.Encrypt(ss);
		System.out.println(sss);

	}

}

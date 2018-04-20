package Util;

public class StringUtil {
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if(s == null || "".equals(s.trim())) {
			return true;
		}else {
			return false;
		}
	}
}

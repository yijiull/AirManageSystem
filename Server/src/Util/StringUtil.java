package Util;

public class StringUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊ��
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

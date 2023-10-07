package library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringLb {
	public static String md5(String str) {
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());
			
			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		//thay đ thành d:
		slug = slug.replaceAll("đ", "d");
		//xóa các ký tự đặt biệt:
		slug = slug.replaceAll("([^0-9a-z\\s])", "");
		//thay space thành dấu gạch ngang:
		slug = slug.replaceAll("\\s", "-");
		//xóa các ký tự gạch ngang ở đầu và cuối:
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}
}

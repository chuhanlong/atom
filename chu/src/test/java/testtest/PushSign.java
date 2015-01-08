package testtest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.lashou.v.mysql.utils.Md5;
/**
 * 百度推送sign算法
 * http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api#.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95
 * 
 * @author chu
 *
 */
public class PushSign {

	public static String getSign(String secretKey, String method, String url, Map<String, String> var) {
		String response = null;
		StringBuilder sb = new StringBuilder();
		sb.append(method).append(url);
		for (String key: var.keySet()) {
			sb.append(key).append("=").append(var.get(key));
		}
		sb.append(secretKey);
		try {
			response = URLEncoder.encode(sb.toString(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response = Md5.getEncryptedPwd(response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) {
		String secretKey = "PaoLtXMsIQYB994m4I6COXz9cpRAv4sO";
		String method = "POST";
		String url = "http://channel.api.duapp.com/rest/2.0/channel/676853380806665687";
		Map<String, String> var = new HashMap<String, String>();
		var.put("method", "query_bindlist");
		var.put("apikey", "qUCmVV3QDzCBgTg9e8xSeZ54");
		var.put("user_id", "676853380806665687");
		String timestamp = String.valueOf(System.currentTimeMillis());
		var.put("timestamp", timestamp);
		String sign = getSign(secretKey, method, url, var);
		
		var.put("sign", sign);
		System.out.println(System.currentTimeMillis());
		System.out.println("sign : " + sign );
	}
}

package javasource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordhash {


	/* メッセージダイジェストアルゴリズム */
	public MessageDigest md = null;


	/*
	 * テストコード
	 */
	public static void main(String[] args) {
		final String algorithmName = "SHA-256";
		final String password = "p12345";

		passwordhash e = new passwordhash();
		e.getpassword(password);
		e.passwordsha(algorithmName);

		byte[] bytes = e.toHashValue(password);
		String result = e.toEncryptedString(bytes);
		System.out.println(result);
		// 出力→ f63e7ba71de88a877e320318d0112356c381276dc6b6a58381726bef4bbc1481


//		TuserService tuserService = new TuserService();
//		List<Tuser> user = tuserService.findAllOrderById();
//
//		for(int i=0 ; i <user.size(); i++){
//			System.out.println(user.get(i).passWord);
//		}

	}



	public String getpassword(String pass){
		final String algorithmName = "SHA-256";
		final String password = pass ;

		passwordhash e = new passwordhash();
		e.passwordsha(algorithmName);
		byte[] bytes = e.toHashValue(password);
		String result = e.toEncryptedString(bytes);

		return result;
	}

	/*
	 * 引数でメッセージダイジェストアルゴリズムを指定する。
	 *  MD2, MD5, SHA, SHA-256, SHA-384, SHA-512が利用可能。
	 */
	public void passwordsha(String algorithmName) {
		try {
			md = MessageDigest.getInstance(algorithmName);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/*
	 * メッセージダイジェストアルゴリズムを使い、文字列をハッシュ値へ変換する。
	 */
	public byte[] toHashValue(String password) {
		md.update(password.getBytes());
		return md.digest();
	}

	/*
	 * バイト配列を16進数の文字列に変換し、連結して返す。
	 */
	public String toEncryptedString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			String hex = String.format("%02x", b);
			sb.append(hex);
		}
		return sb.toString();
	}


}

package javasource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeHashList {

	private String[] hashArray;

	public static void main(String[] args) {
		MakeHashList makehashlist = new MakeHashList();
		makehashlist.makehash("わたしはきゅうりですよふふ　#kyuri #test #hash");
	}

	public String[] makehash(String mur){
		List<String> hashList = new ArrayList<String>();

		String hash ="[　|\\s](#)([a-zA-Zあ-んア-ン_]+)";
		Pattern p = Pattern.compile(hash);
		Matcher matcher = p.matcher(mur);

			while (matcher.find()) {

				for (int i = 0; i <= matcher.groupCount(); i++) {
					System.out.println(matcher.group(i));
				}
				hashList.add(matcher.group(2));
			}

			hashArray = new String[hashList.size()];

		if (hashList.size() != 0) {
			for (int i = 0; i < hashList.size(); i++) {
				hashArray[i] = hashList.get(i);
			}
		}else{
			return null;
		}

		return hashArray;
	}

}

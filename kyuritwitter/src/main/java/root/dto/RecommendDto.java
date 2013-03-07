package root.dto;

import java.util.List;

public class RecommendDto {

	public int userid;

	public String usernick;

	public String username;

	public List<String> followUserList;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFollowUserList() {
		return followUserList;
	}

	public void setFollowUserList(List<String> followUserList) {
		this.followUserList = followUserList;
	}
}

package root.dto;

public class SearchDto {

	private int id;

	private String twit;

	private String[] hash;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTwit() {
		return twit;
	}

	public void setTwit(String twit) {
		this.twit = twit;
	}

	public String[] getHash() {
		return hash;
	}

	public void setHash(String[] hash) {
		this.hash = hash;
	}

}

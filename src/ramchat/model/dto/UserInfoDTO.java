package ramchat.model.dto;

public class UserInfoDTO {
	private String id;
	private String pw;
	private String name;
	private int gender;
	private int birth;
	private String email;
	private int heart;
	private int totalHeart;
	
	
	public UserInfoDTO() {}

	public UserInfoDTO(String id, int heart) {
		this.id=id;
		this.heart=heart;
	}
	
	public UserInfoDTO(String pw, String email, String id) {
		this.id=id;
		this.pw=pw;
		this.email=email;
	}
	
	public UserInfoDTO(String id, String pw, String name, int gender, int birth, String email, int heart,int totalHeart) {
		this(id,heart);
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.email = email;
		this.totalHeart= totalHeart;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalHeart() {
		return totalHeart;
	}

	public void setTotalHeart(int totalHeart) {
		this.totalHeart = totalHeart;
	}

	public String toString() {
		return id+pw+name+gender+birth+email+heart;
	}
}

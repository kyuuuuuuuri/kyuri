package root.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 * Tuserエンティティクラス
 *
 */
@Entity
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" }, date = "2013/02/28 3:52:02")
public class Tuser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** useridプロパティ */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(precision = 10, nullable = false, unique = true)
	public Integer userid;

	/** usernameプロパティ */
	@Column(length = 50, nullable = false, unique = false)
	public String username;

	/** usernickプロパティ */
	@Column(length = 50, nullable = false, unique = false)
	public String usernick;

	/** passWordプロパティ */
	@Column(length = 100, nullable = false, unique = false)
	public String passWord;

	/** mailadプロパティ */
	@Column(length = 100, nullable = false, unique = false)
	public String mailad;

	/** skeyプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	public Integer skey;

	/** postNumプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	public Integer postNum;

	/** followプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	public Integer follow;

	/** followedプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	public Integer followed;

	/** newMurプロパティ */
	@Column(length = 140, nullable = true, unique = false)
	public String newMur;

	/** newMurDプロパティ */
	@Column(nullable = false, unique = false)
	public Timestamp newMurD;

	/** profileimgプロパティ */
	@Lob
	@Column(length = 16777215, nullable = true, unique = false)
	public byte[] profileimg;

	/** secretquestionプロパティ */
	@Column(length = 64, nullable = true, unique = false)
	public String secretquestion;

	/** secretanswerプロパティ */
	@Column(length = 128, nullable = true, unique = false)
	public String secretanswer;

	/** followList関連プロパティ */
	@OneToMany(mappedBy = "tuser")
	public List<Follow> followList;

	/** inListUserList関連プロパティ */
	@OneToMany(mappedBy = "list")
	public List<InListUser> inListUserList;

	/** listFollowList関連プロパティ */
	@OneToMany(mappedBy = "followThis")
	public List<ListFollow> listFollowList;

	/** murmurList関連プロパティ */
	@OneToMany(mappedBy = "tuser")
	public List<Murmur> murmurList;

	/** tlistList関連プロパティ */
	@OneToMany(mappedBy = "tuser")
	public List<Tlist> tlistList;

	/** followList関連プロパティ */
	@OneToMany(mappedBy = "ftuser")
	public List<Follow> ffollowList;

	/** --------- 結合 --------- */
	@OneToMany(mappedBy = "retuser")
	public List<Murmur> reMurmurList;

}
package root.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;

import root.entity.Follow;

/**
 * {@link FollowService}のテストクラスです。
 *
 */
@RunWith(Seasar2.class)
public class FollowServiceTest {

	private FollowService followService;
	protected Connection con;

	@Before
	public void setUp() throws Exception {
		setDb();
	}

	/**
	 * 直接DB接続
	 * @throws Exception
	 */
	private void setDb() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test";
		con = DriverManager.getConnection(url, "root", "");
	}

	/**
	 * 直接DBより取得
	 * @param id
	 * @return
	 */
	private Follow getFollowFromDb(int id) {
		Follow follow = null;//復帰値
		String sql = "SELECT * FROM Follow WHERE followID = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				follow = new Follow();
				follow.userid = rs.getInt("userID");
				follow.followid = rs.getInt("followID");
				follow.fuserid = rs.getInt("FUserID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return follow;
	}

	/**
	 * {@link #followService}が利用可能であることをテストします。
	 *
	 * @throws Exception
	 */
	public void testAvailable() throws Exception {
		assertNotNull(followService);
	}

	/**
	 * FindByメソッドUnitテスト
	 */
	public void testFindById() {
		Follow result1 = followService.findById(0);
		assertNull("NULLじゃないよ。", result1);

		Follow result = followService.findById(2);
		assertNotNull("NULLである。", result);

		Follow tempFollow = getFollowFromDb(2);
		assertNotNull("NULLである", tempFollow);
		assertEquals("userid不一致", tempFollow.userid, result.userid);

		assertEquals("fuserid不一致", tempFollow.fuserid, result.fuserid);

		//SqlReader reader = new SqlReader(getDataSource());

	}

	/**
	 * followCount Class Test
	 */
	public void followCountTest() {
		long followNum = followService.followCount(1);
		long followedNum = followService.beFollowedCount(1);
		assertEquals("follow数が違う", followNum, 3);
		assertEquals("followed数が違う", followedNum, 4);
	}

	/**
	 * delFollow Class Test
	 */
	public void delFollowTest() {
		Follow delfollowName = followService.delFollow(3, 1);
		int delCode = delfollowName.followid;
		assertEquals("削除するはずのフォロワーの名前がちがう", delCode, 2);
	}

	/**
	 * follow
	 */
	public void findFollowUserTest() {
		List<Follow> followlist = followService.findFollowUser(1, 1);
		for (Follow f : followlist) {
			System.out.println(f.followid + " " + f.ftuser.username);
		}

	}
}
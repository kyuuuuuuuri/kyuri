package root.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.unit.Seasar2;

import root.dto.MurmurDto;
import root.entity.Murmur;

/**
 * {@link MurmurService}のテストクラスです。
 *
 */
@RunWith(Seasar2.class)
public class MurmurServiceTest extends S2TestCase {

	private MurmurService murmurService;
	protected Connection con;

	@Before
	public void setUp() throws Exception {
		setDb();
	}

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
	private Murmur getMurFromDb(int id) {
		Murmur twit = null;//復帰値
		String sql = "SELECT * FROM Murmur WHERE murmurID = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				twit = new Murmur();
				twit.userid = rs.getInt("userID");
				twit.murmur = rs.getString("murmur");
				twit.murmurid = rs.getInt("murmurID");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return twit;
	}

	/**
	 * {@link #murmurService}が利用可能であることをテストします。
	 *
	 * @throws Exception
	 */
	public void testAvailable() throws Exception {
		assertNotNull(murmurService);
	}

	/**
	 * FindByメソッドUnitテスト
	 */
	public void testFindById() {

		Murmur result = murmurService.findById(5);
		assertNotNull("NULLである。", result);

		Murmur tempTwit = getMurFromDb(5);
		assertNotNull("NULLである", tempTwit);
		assertEquals("id不一致", tempTwit.userid, result.userid);
		assertEquals("つぶやき不一致", tempTwit.murmur, result.murmur);

	}

	/**
	 * mainListPager Class Test
	 */
	public void mainListPagerTest() {
		List<Murmur> twitList = new ArrayList<Murmur>();
		List<Integer> useridList = new ArrayList<Integer>();

		useridList.add(1);
		useridList.add(2);

		twitList = murmurService.mainListPager(5, 0, useridList, 0);
		int twitID = twitList.get(0).murmurid;
		int twitListLong = twitList.size();
		assertEquals("つぶやきの結果が違う", twitID, 131);
		assertEquals("取得したつぶやきの数が違う", twitListLong, 5);

	}

	public void mainListTest(){
		List<Murmur> twitList = new ArrayList<Murmur>();
		List<Integer> useridList = new ArrayList<Integer>();

		useridList.add(1);
		useridList.add(11);

		twitList = murmurService.mainListPager(10, 0, useridList, 1);
		for(Murmur m : twitList){
			System.out.println(m.murmur + " " +  m.retweets.size() + " " + m.favolite.size());
			if(m.retweets.size() != 0){
				System.out.println(m.retweets.get(0).murmurid);
			}
		}

	}


	/**
	 * listPager Class Test
	 */
//	public void listPagerTest() {
//		List<Murmur> twitList = new ArrayList<Murmur>();
//
//		twitList = murmurService.listPager(5, 0, 1);
//		int twitID = twitList.get(0).murmurid;
//		int twitListLong = twitList.size();
//		assertEquals("つぶやきの結果が違う", twitID, 131);
//		assertEquals("取得したつぶやきの数が違う", twitListLong, 5);
//	}

	/**
	 * another_user_twitList Class Test
	 */
	public void another_user_twitListTest() {
		List<MurmurDto> twitList = new ArrayList<MurmurDto>();
		twitList = murmurService.another_user_twitList(2);

		String twitUsername = twitList.get(0).username;
		int twitid = twitList.get(0).murmurid;
		assertEquals("userNameが違う", twitUsername, "にゅるん");
		assertEquals("twitIdが違う", twitid, 49);
	}
//
//	public void zibunJoinmainTest() {
//		List<Murmur> murmurlist = murmurService.zibunJoinBeforeList(250);
//
//	}

	public void zibunJoinBeforeList(Murmur mur) {

		if (mur.murmurid != 250) {
			System.out.println(mur.beforeid + mur.murmur + mur.murmurid);
		}
		if (mur.beforeParent != null) {
			zibunJoinBeforeList(mur.beforeParent);

		}
	}

//	public void zibunJoinmainArterTest() {
//		List<Murmur> murmurlist = murmurService.zibunJoinAfterList(249);
//
//				zibunJoinAfterTest(murmurlist);
//
//	}

	public void zibunJoinAfterTest(List<Murmur> mur) {
		for (Murmur m : mur) {
			System.out.println(m.murmur + m.murmurid + " ");
			if (!m.murmurList.isEmpty()) {
				zibunJoinAfterTest(m.murmurList);
			}
		}
	}



}
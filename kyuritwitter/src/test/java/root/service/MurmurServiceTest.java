package root.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.seasar.framework.unit.S2Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

import root.dto.MurmurDto;
import root.entity.Murmur;
import root.entity.Tuser;
/**
 * {@link MurmurService}のテストクラスです。
 *
 */
@RunWith(Seasar2.class)
public class MurmurServiceTest{

    private MurmurService murmurService;
    protected Connection con;

    @Before
    public void setUp() throws Exception{
    	setDb();
    }

    private void setDb() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        con = DriverManager.getConnection(url,"root","GinHizi0510");
      }

    /**
     * 直接DBより取得
     * @param id
     * @return
     */
    private Murmur getMurFromDb(int id){
      Murmur twit = null;//復帰値
      String sql ="SELECT * FROM Murmur WHERE murmurID = ?";
      PreparedStatement ps;
      try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
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
    	assertNotNull("NULLである。",result);

    	Murmur tempTwit = getMurFromDb(5);
    	assertNotNull("NULLである", tempTwit);
    	assertEquals("id不一致", tempTwit.userid,result.userid);
    	assertEquals("つぶやき不一致", tempTwit.murmur,result.murmur);

    }

    /**
     * mainListPager Class Test
     */
    public void mainListPagerTest() {
    	List<Murmur> twitList = new ArrayList<Murmur>();
    	List<Integer> useridList = new ArrayList<Integer>();

    	useridList.add(1);
    	useridList.add(2);

    	twitList = murmurService.mainListPager(5, 0, useridList);
    	int twitID = twitList.get(0).murmurid;
    	int twitListLong = twitList.size();
    	assertEquals("つぶやきの結果が違う", twitID , 131);
    	assertEquals("取得したつぶやきの数が違う", twitListLong, 5);

    }

    /**
     * listPager Class Test
     */
    public void listPagerTest() {
    	List<Murmur> twitList = new ArrayList<Murmur>();

    	twitList = murmurService.listPager(5, 0, 1);
    	int twitID = twitList.get(0).murmurid;
    	int twitListLong = twitList.size();
    	assertEquals("つぶやきの結果が違う", twitID , 131);
    	assertEquals("取得したつぶやきの数が違う", twitListLong, 5);
    }

    /**
     * another_user_twitList Class Test
     */
    public void another_user_twitListTest() {
    	List<MurmurDto> twitList = new ArrayList<MurmurDto>();
    	twitList = murmurService.another_user_twitList(2);

    	String twitUsername = twitList.get(0).username;
    	int twitid=twitList.get(0).murmurid;
    	assertEquals("userNameが違う",twitUsername,"にゅるん");
    	assertEquals("twitIdが違う", twitid, 49);
    }


}
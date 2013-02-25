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

import root.entity.Tuser;

/**
 * {@link TuserService}のテストクラスです。
 *
 */
@RunWith(Seasar2.class)
public class TuserServiceTest extends S2TestCase{
    private TuserService tuserService;
    protected Connection con;

    //DataSet expect = accessor.readDb();

    @Before
    public void setUp() throws Exception{
    	setDb();
    }

    /**
     * 直接DB接続
     * @throws Exception
     */
    private void setDb() throws Exception{
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/test";
      con = DriverManager.getConnection(url,"root","");
    }

    /**
     * 直接DBより取得
     * @param id
     * @return
     */
    private Tuser getTuserFromDb(int id){
      Tuser tuser = null;//復帰値
      String sql ="SELECT * FROM Tuser WHERE userID = ?";
      PreparedStatement ps;
      try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
          tuser = new Tuser();
          tuser.userid = rs.getInt("userID");
          tuser.username = rs.getString("userName");
          tuser.usernick = rs.getString("userNick");
          tuser.follow = rs.getInt("follow");
          tuser.followed = rs.getInt("followed");
          tuser.mailad = rs.getString("mailAd");
          tuser.passWord = rs.getString("Pass_Word");
          tuser.postNum = rs.getInt("post_num");
          tuser.newMur = rs.getString("new_mur");
          tuser.newMurD = rs.getTimestamp("new_mur_d");
        }
      } catch (SQLException e) {
    	  e.printStackTrace();
      }
      return tuser;
    }

    public void testAvailable() throws Exception {
    	assertNotNull(tuserService);
    }

    /**
     * FindByメソッドUnitテスト
     */
    public void testFindById() {
    	Tuser result1 = tuserService.findById(0);
    	assertNull("NULLじゃないよ。",result1);

    	Tuser result = tuserService.findById(1);
    	assertNotNull("NULLである。",result);

    	Tuser tempTuser = getTuserFromDb(1);
    	assertNotNull("NULLである", tempTuser);
    	assertEquals("id不一致", tempTuser.userid,result.userid);

    	//SqlReader reader = new SqlReader(getDataSource());

    }

    /**
     * FindByName(nicknameの検索)のテスト
     */
    public void testFindbyName(){
       	Tuser tempTuser = getTuserFromDb(1);
    	Tuser result = tuserService.findByNameForCheck("kyuuuuuuri");
    	assertNotNull("ニックネーム検索がNullになる", result);
    	assertEquals("ニックネーム検索が正確ではない", tempTuser.username, result.username);
    }

    /**
     *id件数の検索のテスト
     */
    public void testTuserListcount(){
    	List<Integer> idListTestCase = new ArrayList<Integer>();
    	for(int i=0; i<4; i++){
    		idListTestCase.add(i+1);
    	}
    	long idListCount = tuserService.tuserListcount(idListTestCase);
    	assertEquals("id検索件数の数が一致しない", idListCount,4);
    }

    /**
     * pagerのテスト
     */
    public void tuserPager(){
    	List<Tuser> tuserList = new ArrayList<Tuser>();
    	List<Tuser> tuserList2 = new ArrayList<Tuser>();

    	List<Integer> tuserInt1 = new ArrayList<Integer>();
    	List<Integer> tuserInt2 = new ArrayList<Integer>();
    	tuserInt1.add(1);
    	tuserInt1.add(2);
    	tuserList = tuserService.tuserPager(5, 0, tuserInt1);

    	//取得件数のテスト
    	int tuserListCount;
    	tuserListCount = tuserList.size();
    	//System.out.print(tuserListCount+"kyuri");

        assertEquals("ページングクラスによって取得した件数が一致しない(2件)",tuserListCount, 2);
    	//取得内容のテスト
        int tuserIDtest = tuserList.get(0).userid;
        int tuserIDtest2 = tuserList.get(1).userid;
        assertEquals("ページングによって取得した内容が間違っている",tuserIDtest,1);
        assertEquals("ページングによって取得した内容が間違っている",tuserIDtest2, 2);

        for(int i=0; i<5; i++){
        	tuserInt2.add(i+1);
        }

    	tuserList2 = tuserService.tuserPager(5, 0, tuserInt2);
    	//取得件数のテスト
    	assertEquals("ページングクラスによって取得した件数が一致しない(5件)", tuserList2.size(),5);

    	//取得内容のテスト
    	tuserIDtest = tuserList2.get(0).userid;
    	assertEquals("ページングによって取得した内容が間違っている",tuserIDtest,1);

    }

    /**
     * UserSearchClassTest
     */
    public void tuserSerchTest(){
    	List<Tuser> tuserSerch = new ArrayList<Tuser>();
    	tuserSerch = tuserService.tuserSerch("kyuuuuuuri");
    	int tusernumber = tuserSerch.get(0).userid;
    	assertEquals("検査クラスに寄って検索された結果が間違っている",tusernumber,1);
    }

    public void tuserdearchTest(){
    	List<Tuser> tuserSearch = new ArrayList<Tuser>();
    	tuserSearch = tuserService.tuserSearch("nyu", 1, 1, 0);
    	if(tuserSearch.isEmpty()){
    		System.out.println("見つからない…みつからないよ");
    	}else{
    		for(Tuser t: tuserSearch){
    			System.out.println(t.username + t.usernick + t.ffollowList.get(0).userid);
    		}
    	}
    }


}
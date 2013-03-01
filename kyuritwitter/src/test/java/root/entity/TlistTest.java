package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static root.entity.TlistNames.*;

/**
 * {@link Tlist}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2013/02/28 3:52:13")
public class TlistTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(Tlist.class).id(1).getSingleResult();
    }

    /**
     * inListUserListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_inListUserList() throws Exception {
        jdbcManager.from(Tlist.class).leftOuterJoin(inListUserList()).id(1).getSingleResult();
    }

    /**
     * listFollowListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_listFollowList() throws Exception {
        jdbcManager.from(Tlist.class).leftOuterJoin(listFollowList()).id(1).getSingleResult();
    }

    /**
     * tuserとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tuser() throws Exception {
        jdbcManager.from(Tlist.class).leftOuterJoin(tuser()).id(1).getSingleResult();
    }
}
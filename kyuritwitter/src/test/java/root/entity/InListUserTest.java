package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static root.entity.InListUserNames.*;

/**
 * {@link InListUser}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2013/02/28 3:52:13")
public class InListUserTest extends S2TestCase {

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
        jdbcManager.from(InListUser.class).id(1).getSingleResult();
    }

    /**
     * tlistとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tlist() throws Exception {
        jdbcManager.from(InListUser.class).leftOuterJoin(tlist()).id(1).getSingleResult();
    }

    /**
     * listとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_list() throws Exception {
        jdbcManager.from(InListUser.class).leftOuterJoin(list()).id(1).getSingleResult();
    }
}
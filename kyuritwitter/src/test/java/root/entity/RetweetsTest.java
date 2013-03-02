package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static root.entity.RetweetsNames.*;

/**
 * {@link Retweets}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2013/03/02 19:05:33")
public class RetweetsTest extends S2TestCase {

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
        jdbcManager.from(Retweets.class).id(1).getSingleResult();
    }

    /**
     * tuserとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tuser() throws Exception {
        jdbcManager.from(Retweets.class).leftOuterJoin(tuser()).id(1).getSingleResult();
    }
}
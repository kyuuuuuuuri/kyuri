package root.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static root.entity.BlockidNames.*;

/**
 * {@link Blockid}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2013/03/05 13:26:01")
public class BlockidTest extends S2TestCase {

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
        jdbcManager.from(Blockid.class).id(1).getSingleResult();
    }

    /**
     * tuserとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_tuser() throws Exception {
        jdbcManager.from(Blockid.class).leftOuterJoin(tuser()).id(1).getSingleResult();
    }

    /**
     * blockとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_block() throws Exception {
        jdbcManager.from(Blockid.class).leftOuterJoin(block()).id(1).getSingleResult();
    }
}
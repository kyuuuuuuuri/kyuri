package root.service;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.unit.S2TestCase;

import root.entity.ListFollow;

/**
 * {@link ListFollowService}のテストクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceTestModelFactoryImpl" }, date = "2013/02/28 3:52:15")
public class ListFollowServiceTest extends S2TestCase {

	private ListFollowService listFollowService;

	/**
	 * 事前処理をします。
	 *
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");
	}

	/**
	 * {@link #listFollowService}が利用可能であることをテストします。
	 *
	 * @throws Exception
	 */
	public void testAvailable() throws Exception {
		assertNotNull(listFollowService);
	}

	public void findListFollowByUserAndMyInfoTest() {
		List<ListFollow> lsf = listFollowService.findListFollowByUserAndMyInfo(1, 1);

		for(ListFollow l : lsf){
			System.out.println(l.tlist.listFollowList.get(0).followThisUserid + l.tlist.listname);
		}
	}

}
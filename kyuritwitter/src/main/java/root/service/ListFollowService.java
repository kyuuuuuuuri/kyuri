package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.ListFollowNames.*;

import java.util.List;

import javax.annotation.Generated;

import root.entity.ListFollow;

/**
 * {@link ListFollow}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/02/28 3:52:11")
public class ListFollowService extends AbstractService<ListFollow> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public ListFollow findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<ListFollow> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}

	/**
	 * userがフォローしているリストを返す(自分制作含む)
	 * @param userid
	 * @return
	 */
	public List<ListFollow> findListFollowByUserid(int userid) {
		return jdbcManager
				.from(ListFollow.class)
				.innerJoin("tlist")
				.innerJoin("tlist.tuser")
				.where("followThisUserid = ?", userid)
				.getResultList();
	}

}
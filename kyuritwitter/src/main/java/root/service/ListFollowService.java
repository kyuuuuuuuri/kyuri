package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.ListFollowNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

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

	/**
	 *
	 * @param userid
	 * @param MyUserid
	 * @return
	 */
	public List<ListFollow> findListFollowByUserAndMyInfo(int userid, int MyUserid){
		System.out.println(MyUserid+"kyuri");
		return jdbcManager
				.from(ListFollow.class)
				.where("followThisUserid = ?", userid)
				.innerJoin("tlist")
				.innerJoin("tlist.tuser")
				.getResultList();
	}

	/**
	 * listFollowが存在するかどうか
	 * @param listid
	 * @param userid
	 * @return
	 */
	public boolean existListFollow(int listid, int userid){
		ListFollow listfollow =
				jdbcManager
						.from(ListFollow.class)
						.where(
								and(
										new SimpleWhere().eq("listid", listid),
										new SimpleWhere().eq("followThisUserid", userid)
								)
						)
						.getSingleResult();
		if (listfollow == null) {
			return false;
		}
		return true;
	}

	/**
	 * deleteするユーザを取得する
	 * @param listid
	 * @param userid
	 * @return
	 */
	public ListFollow findListFollowForDelete (int listid, int userid) {
		return select().where(
				and(
						new SimpleWhere().eq("listid", listid),
						new SimpleWhere().eq("followThisUserid", userid)
						)
				)
		.getSingleResult();
	}


}
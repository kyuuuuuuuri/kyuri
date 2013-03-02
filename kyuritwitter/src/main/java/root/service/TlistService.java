package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.TlistNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Tlist;

/**
 * {@link Tlist}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/02/28 3:52:11")
public class TlistService extends AbstractService<Tlist> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param listid
	 *            識別子
	 * @return エンティティ
	 */
	public Tlist findById(Integer listid) {
		return select().id(listid).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Tlist> findAllOrderById() {
		return select().orderBy(asc(listid())).getResultList();
	}

	public List<Tlist> findByUserId(int userid){
		return jdbcManager
				.from(Tlist.class)
				.innerJoin("tuser")
				.where(new SimpleWhere().eq(userid(), userid))
				.getResultList();
	}

	public Tlist findByListId(int listid){
		return jdbcManager
				.from(Tlist.class)
				.innerJoin("tuser")
				.where(new SimpleWhere().eq(listid(), listid))
				.getSingleResult();
	}

	public List<Tlist> findTlistUserMade(int thisUserid, int userid){
		return jdbcManager
				.from(Tlist.class)
				.leftOuterJoin("listFollowList",
						new SimpleWhere().eq("listFollowList.followThisUserid", userid))
				.where("userid = ?", thisUserid)
				.getResultList();
	}

	/**
	 * tlistにデータを格納しつつ、新しいidを取ってくる
	 * @param tlist
	 * @return
	 */
	public int insertTlist(Tlist tlist){
		insert(tlist);
		List<Tlist> list = select().orderBy(desc("listid")).getResultList();
		return list.get(0).listid;
	}

}
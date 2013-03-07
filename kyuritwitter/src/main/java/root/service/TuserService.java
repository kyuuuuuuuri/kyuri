package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.*;
import static root.entity.TuserNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Tuser;

/**
 * {@link Tuser}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2012/05/22 14:16:36")
public class TuserService extends AbstractService<Tuser> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param userid
	 *            識別子
	 * @return エンティティ
	 */
	public Tuser findById(Integer userid) {
		return select().id(userid).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Tuser> findAllOrderById() {
		return select().orderBy(asc(userid())).getResultList();
	}

	/**
	 * findById の usernick ver
	 * @param userni
	 * @return
	 */
	public Tuser findByName(String userni, int userid) {
		return jdbcManager
				.from(Tuser.class)
				.leftOuterJoin("blockedUserList", new SimpleWhere().eq("blockedUserList.userid", userid))
				.leftOuterJoin("ffollowList" , new SimpleWhere().eq("ffollowList.userid", userid))
				.leftOuterJoin("ffollowList.tuser")
				.where("usernick = ?",userni)
				.getSingleResult();
	}

	/**
	 * followしているユーザの検索
	 * @param userni
	 * @return
	 */
	public List<Tuser> findForFollow(int id , int userid){
		return jdbcManager
				.from(Tuser.class)
				.innerJoin("followList")
				.leftOuterJoin("ffollowList" , new SimpleWhere().eq("ffollowList.userid", userid))
				.where("userid = ?", id)
				.getResultList();
	}

	/**
	 * followしているユーザの数
	 * @return
	 */
	public long findForFollowCount(int id){
		return jdbcManager
				.from(Tuser.class)
				.where("userid", id)
				.getCount();
	}

	/**
	 *
	 * @param userni
	 * @return
	 */
	public Tuser findByNameForCheck(String userni) {
		return jdbcManager
				.from(Tuser.class)
				.where("usernick =?",userni)
				.getSingleResult();
	}



	/**
	 * idにマッチしたTwitterユーザをすべて取得する
	 * @param Integer id
	 * @return Long
	 */
	public long tuserListcount(List<Integer> id) {
		return select().where(new SimpleWhere().in("userid", id)).getCount();
	}

	public List<Tuser> tuserPager(int LIMIT, int page, List<Integer> idList) {
		return select().where(new SimpleWhere().in("userid", idList))
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}


	/**
	 * User検索メソッド
	 * @param String
	 * @return Tuser List
	 */
	public List<Tuser> tuserSerch(String searchUser) {
		return jdbcManager
				.from(Tuser.class)
				.where(
						or(
								like(tuser().username(), searchUser),
								like(tuser().usernick(), searchUser)
						)
				)
				.getResultList();
	}

	/**
	 * User検索メソッド
	 * @param String
	 * @return Tuser List
	 */
	public List<Tuser> tuserSearch(String searchUser, int userid, int LIMIT, int page) {
		return jdbcManager
				.from(Tuser.class)
				.leftOuterJoin("ffollowList", new SimpleWhere().eq("ffollowList.userid", userid))
				.where(
						or(
								new SimpleWhere().starts("usernick", searchUser),
								new SimpleWhere().starts("username", searchUser)
						)
				)
				.limit(LIMIT)
				.offset(page)
				.getResultList();
	}

	/**
	 * passを新しく更新する
	 * @param tuser
	 */
	public void updatePassWord(Tuser tuser){
		jdbcManager.update(tuser).includes("passWord").execute();
	}

	public List<Tuser> tuserSerch(int LIMIT, int page, String searchUser) {
		return jdbcManager
				.from(Tuser.class)
				.where(
						or(
								like(tuser().username(), searchUser),
								like(tuser().usernick(), searchUser)
						)
				)
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}

	/**
	 * followしたあとに呼ばれる
	 * @param tuser
	 */
	public void updateTuserAfterFollow(Tuser tuser){
		jdbcManager.update(tuser).includes("follow").execute();
	}

	/**
	 * followされたら呼ばれる
	 * @param tuser
	 */
	public void updateTuserAfterBeFollowed(Tuser tuser){
		jdbcManager.update(tuser).includes("followed").execute();
	}

/**
 *
 * @param tuser
 */
	public void updateTuserAfterTwit(Tuser tuser){
		jdbcManager.update(tuser).includes("newMur", "postNum", "newMurD").execute();
	}


	public void updateTuserAfterDel(Tuser tuser){
		jdbcManager.update(tuser).includes("postNum").execute();
	}

	/*
	 * プロフィール画像をアップロードする
	 */
	public void tuserImgUpdate(byte[] img, int id) {
		Tuser tuser = new Tuser();
		tuser.profileimg = img;
		tuser.userid = id;
		jdbcManager.update(tuser).includes("profileimg").execute();

	}

	/*
	 * プロフィール画像を取得する
	 */
	public Tuser getTuserImg(int id){
		return select()
				.id(id)
				.eager("profileimg")
				.getSingleResult();

	}

	//usernickを更新する
	public void tuserNameUpdate(int id, String username, int skey){
		Tuser tuser = new Tuser();
		tuser.userid = id;
		tuser.username = username;
		tuser.skey = skey;
		jdbcManager.update(tuser).includes("username","skey").execute();
	}

	public List<Tuser> tuserListFollowByUser(int listid, int userid){
		return jdbcManager
				.from(Tuser.class)
				.leftOuterJoin("ffollowList", new SimpleWhere().eq("ffollowList.userid", userid))
				.leftOuterJoin("inListUserList")
				.where("inListUserList.listid = ?", listid)
				.getResultList();
	}

	public List<Tuser> recommend (List<Integer> idList) {
		return select()
				.where(new SimpleWhere().notIn("userid", idList))
				.orderBy(desc("followed"))
				.limit(3)
				.getResultList();
	}

}

package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.FollowNames.*;
import static root.entity.Names.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.IterationCallback;
import org.seasar.extension.jdbc.IterationContext;
import org.seasar.extension.jdbc.where.SimpleWhere;

import root.dto.RecommendDto;
import root.entity.Follow;

/**
 * {@link Follow}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2012/05/22 14:16:36")
public class FollowService extends AbstractService<Follow> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param followid
	 *            識別子
	 * @return エンティティ
	 */
	public Follow findById(Integer followid) {
		return select().id(followid).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Follow> findAllOrderById() {
		return select().orderBy(asc(followid())).getResultList();
	}

	public List<Follow> findUserFollow(Integer userid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userid", userid);

		return select().where(map).getResultList();
	}

	/**
	 * ユーザがフォローしているユーザを検索する
	 * @param id
	 * @return
	 */
	public List<Follow> findFollowUser(int id , int userid) {
		return jdbcManager
				.from(Follow.class)
				.innerJoin("ftuser")
				.leftOuterJoin("ftuser.ffollowList" , new SimpleWhere().eq("ftuser.ffollowList.userid", userid))
				.where(new SimpleWhere().eq(follow().userid(), id))
				.orderBy("followid")
				.limit(10)
				.getResultList();
	}

	/**
	 * ユーザがフォローしているユーザを検索するajax
	 * @param id
	 * @return
	 */
	public List<Follow> findFollowUserOld(int id , int userid, int lastid) {
		return jdbcManager
				.from(Follow.class)
				.innerJoin("ftuser")
				.leftOuterJoin("ftuser.ffollowList" , new SimpleWhere().eq("ftuser.ffollowList.userid", userid))
				.where(new SimpleWhere().eq(follow().userid(), id),
						new SimpleWhere().lt("followid", lastid))
				.orderBy(desc("followid"))
				.limit(10)
				.getResultList();
	}


	/**
	 * フォローされているユーザを検索する
	 * @param id
	 * @param userid
	 * @return
	 */
	public List<Follow> findFollowedUser(int id, int userid) {
		return jdbcManager
				.from(Follow.class)
				.innerJoin("tuser")
				.leftOuterJoin("tuser.ffollowList" , new SimpleWhere().eq("tuser.ffollowList.userid", userid))
				.where(new SimpleWhere().eq(follow().fuserid(), id))
				.orderBy("followid")
				.limit(10)
				.getResultList();
	}

	/**
	 * フォローされているユーザを検索する
	 * @param id
	 * @param userid
	 * @return
	 */
	public List<Follow> findFollowedUserOld(int id, int userid, int lastid) {
		return jdbcManager
				.from(Follow.class)
				.innerJoin("tuser")
				.leftOuterJoin("tuser.ffollowList" , new SimpleWhere().eq("tuser.ffollowList.userid", userid))
				.where(new SimpleWhere().eq(follow().fuserid(), id),
						new SimpleWhere().lt("followid", lastid))
				.orderBy(desc("followid"))
				.limit(10)
				.getResultList();
	}


	/**
	 * follow しているユーザの数
	 * @param id
	 * @return long
	 */
	public long followCount(Integer id) {
		return select().where(eq(follow().userid(), id)).getCount();
	}

	/**
	 * follow されているユーザの数
	 * @param id
	 * @return
	 */
	public long beFollowedCount(Integer id) {
		return select().where(eq(follow().fuserid(), id)).getCount();
	}

	public List<Follow> beFollowedList(Integer id) {
		return select().where(eq(follow().fuserid(), id)).getResultList();
	}

	/**
	 * ユーザがフォローしているユーザを抜き出すとともに、Listに入っているかを抜き出す
	 * @return
	 */
	public List<Follow> findFollowListAndMakeList(int userid, int listid, int LIMIT){
		return jdbcManager.from(Follow.class)
				.innerJoin("ftuser")
				.leftOuterJoin("ftuser.inListUserList", new SimpleWhere().eq("ftuser.inListUserList.listid", listid))
				.where("userid = ?", userid)
				.limit(LIMIT)
				.getResultList();
	}

	/**
	 * フォローユーザの検索
	 * @param delete_userID
	 * @param userid
	 * @return delete following UserID
	 */
	public Follow delFollow(Integer f_userID, Integer userid) {
		return select()
				.where(
						and(
								eq(follow().fuserid(), f_userID),
								eq(follow().userid(), userid)
						)
				)
				.getSingleResult();
	}


	public List<Follow> recommendUserNull(List<Integer> useridList){

		return select()
				.innerJoin("ftuser")
				.where(new SimpleWhere().notIn("fuserid", useridList))
				.orderBy(desc("ftuser.followed"))
				.iterate(new IterationCallback<Follow, List<Follow>>() {
					List<Follow> followList = new ArrayList<Follow>();
					int count = 0;
					List<Integer> idList = new ArrayList<Integer>();

					public List<Follow> iterate(Follow f, IterationContext context) {

						if (idList.contains(f.fuserid)) {
							//何もしない

						}else{
							idList.add(f.fuserid);
							followList.add(f);
							++count;
						}
						if(count == 3){
							return followList;
						}
						return followList;
					}
				});
	}

	/**
	 * おすすめユーザ
	 * @param useridList
	 * @return
	 */
	public List<Follow> recommendUser(List<Integer> useridList, int userid){

		return select()
				.innerJoin("tuser")
				.innerJoin("ftuser")
				.where(
						and(
								new SimpleWhere().in("userid", useridList),
								new SimpleWhere().notIn("fuserid", useridList),
								new SimpleWhere().notIn("fuserid", userid)
						))
//				.getResultList();

				.iterate(new IterationCallback<Follow, List<Follow>>() {
					List<Follow> followList = new ArrayList<Follow>();
					int count = 0;
					List<Integer> idList = new ArrayList<Integer>();

					public List<Follow> iterate(Follow f, IterationContext context) {

						if (idList.contains(f.fuserid)) {
							//何もしない

						}else{
							idList.add(f.fuserid);
							followList.add(f);
							++count;
						}
						if(count == 3){
							return followList;
						}
						return followList;
					}
				});
		//				.getResultList();
	}

	//おすすめユーザすべて！一覧！を表示する
	public Map<Integer, RecommendDto> recommendUserAllList(List<Integer> useridList, int userid){
		return select()
				.innerJoin("tuser")
				.innerJoin("ftuser")
				.where(
						and(
								new SimpleWhere().in("userid", useridList),
								new SimpleWhere().notIn("fuserid", useridList),
								new SimpleWhere().notIn("fuserid", userid)
						))
				.orderBy(desc("followid"))
				.iterate(new IterationCallback<Follow, Map<Integer, RecommendDto>>() {
					int count = 0;

					List<Follow> followList = new ArrayList<Follow>();
//					List<Integer> idList = new ArrayList<Integer>();
					Map<Integer, RecommendDto> userMap = new HashMap<Integer, RecommendDto>();

					public Map<Integer, RecommendDto> iterate(Follow f, IterationContext context) {

						if (userMap.containsKey(f.ftuser.userid)) {
							RecommendDto rd = userMap.get(f.ftuser.userid);
							List<String> fu = new ArrayList<String>();
							int id = f.ftuser.userid;

							rd = userMap.get(id);
							fu = rd.followUserList;
							fu.add(f.tuser.username);
							rd.followUserList = fu;

							userMap.put(id, rd);

						}else{
							RecommendDto re = new RecommendDto();
							List<String> fu = new ArrayList<String>();
							fu.add(f.tuser.username);

							re.userid = f.ftuser.userid;
							re.usernick = f.ftuser.usernick;
							re.username = f.ftuser.username;
							re.followUserList = fu;

							userMap.put(f.ftuser.userid, re);
							followList.add(f);
							count++;
						}
						if(count == 10){
							return userMap;
						}
						return userMap;
					}
				});
	}








}
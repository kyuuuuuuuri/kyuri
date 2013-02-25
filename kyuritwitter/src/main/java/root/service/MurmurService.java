package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.MurmurNames.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.dto.MurmurDto;
import root.entity.Murmur;

/**
 * {@link Murmur}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2012/05/22 14:16:36")
public class MurmurService extends AbstractService<Murmur> {

	private static final String MurMur_DTO =
			" select murmur.*, tuser.username, tuser.usernick"
					+ " from murmur inner join tuser"
					+ " on murmur.userid = tuser.userid"
					+ " where murmur.userid = ? "
					+ " order by murmur.murmurid desc";

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param murmurid
	 *            識別子
	 * @return エンティティ
	 */
	public Murmur findById(Integer murmurid) {
		return select().id(murmurid).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Murmur> findAllOrderById() {
		return select().orderBy(asc(murmurid())).getResultList();
	}

	/**
	 * 返信(前)のツイートを取ってくる
	 * @param id
	 * @return
	 */
	public List<Murmur> zibunJoinBeforeList(int id, int userid){
//		List<Murmur> list=
		List<Murmur> murmur = new ArrayList<Murmur>();
		Murmur mur=
				jdbcManager.from(Murmur.class)
				.innerJoin("tuser")
				.leftOuterJoin("beforeParent")
				.leftOuterJoin("beforeParent.tuser")
				.leftOuterJoin("beforeParent.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.leftOuterJoin("beforeParent.beforeParent")
				.leftOuterJoin("beforeParent.beforeParent.tuser")
				.leftOuterJoin("beforeParent.beforeParent.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.leftOuterJoin("beforeParent.beforeParent.beforeParent")
				.leftOuterJoin("beforeParent.beforeParent.beforeParent.tuser")
				.leftOuterJoin("beforeParent.beforeParent.beforeParent.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.where("murmurid = ?",id)
				.orderBy(asc("murmurid"))
				.getSingleResult();

		if(mur == null){
			System.out.println("見つからないよ見つからない…");
		}
		murmur = MakezibunJoinBeforeList(mur,id);
		return murmur;
	}

	/**
	 * beforeをすべてツリー構造にする
	 * @param mur
	 * @param id
	 * @return
	 */
	public List<Murmur> MakezibunJoinBeforeList(Murmur mur, int id){

		System.out.println(mur.murmur);
		if (mur.murmurid != id) {
			murmurafter.add(mur);
			System.out.println(mur.murmur + mur.murmurid + "before");
		}
		if (mur.beforeParent != null) {
			MakezibunJoinBeforeList(mur.beforeParent , id);
		}

		return murmurafter;
	}

	/**
	 * twitのあとの返信リストを返す
	 * @param id
	 * @return
	 */
	public List<Murmur> zibunJoinAfterList(int id ,int userid){
		List<Murmur> murmur = new ArrayList<Murmur>();

		List<Murmur> list=
				jdbcManager.from(Murmur.class)
				.where("murmurid=?", id)
				//.innerJoin("tuser")
				.innerJoin("murmurList")
				.leftOuterJoin("murmurList.tuser")
				.leftOuterJoin("murmurList.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.leftOuterJoin("murmurList.murmurList")
				.leftOuterJoin("murmurList.murmurList.tuser")
				.leftOuterJoin("murmurList.murmurList.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.leftOuterJoin("murmurList.murmurList.murmurList")
				.leftOuterJoin("murmurList.murmurList.murmurList.tuser")
				.leftOuterJoin("murmurList.murmurList.murmurList.favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.orderBy(desc("murmurid"))
				.getResultList();

		if(list.isEmpty()){
			System.out.println("空だよ");
			return null;
		}

		murmur = MakezibunJoinAfterList(list, id);

		return murmur;

	}

	List<Murmur> murmurafter = new ArrayList<Murmur>();
	/**
	 * afterをすべてツリー構造にする
	 * @param mur
	 */
	public List<Murmur> MakezibunJoinAfterList(List<Murmur> mur, int id) {

		for (Murmur m : mur) {
			System.out.println(m.murmur);

			if(m.murmurid != id){
				murmurafter.add(m);
				System.out.println(m.murmur + m.murmurid + "after");
			}
			if(m.murmurList != null){
				if (!(m.murmurList.isEmpty())) {
					System.out.println();
					MakezibunJoinAfterList(m.murmurList, id);
				}
			}
		}
		return murmurafter;
	}


	/**
	 *
	 * @param LIMIT
	 * @param page
	 * @param murmur_userid
	 * @return
	 */

	public List<Murmur> mainListPager(int LIMIT, int page, List<Integer> murmur_userid, int userid) {
		return jdbcManager.from(Murmur.class).innerJoin("tuser")
				.where(new SimpleWhere().in("userid", murmur_userid.toArray()))
				.leftOuterJoin("favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.orderBy(desc("murmurid"))
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}

	public List<Murmur> listPager(int LIMIT, int page, String userni , int userid) {
		return select()
				.innerJoin("tuser")
				.leftOuterJoin("favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.where("usernick = ?", userni)
				.orderBy(desc("murmurid"))
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}


	/**
	 * つぶやきに更新があったか
	 * @param id
	 * @return
	 */
	public boolean existNewTwit(int id, List<Integer> murmurId){
//		for(int i=0; i<murmurId.size() ; i++){
//			System.out.println(murmurId.get(i));
//		}
		List<Murmur> murmur = new ArrayList<Murmur>();
		murmur = select()
				.innerJoin("tuser")
				.where(
						and(
								new SimpleWhere().in("userid", murmurId.toArray()),
								new SimpleWhere().gt("murmurid", id)
							)
						)
		.orderBy(desc("murmurid"))
		.getResultList();
		if(!murmur.isEmpty()){

			return true;
		}
		return false;
	}

	/**
	 * 新しいつぶやきを読み込む
	 * @param id
	 * @return
	 */
	public List<Murmur> selectNewTwit(int id, List<Integer> murmurId, int userid){
		return select()
				.innerJoin("tuser")
				.leftOuterJoin("favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.where(
						and(
								new SimpleWhere().in("userid", murmurId.toArray()),
								new SimpleWhere().gt("murmurid", id)
						)
				)
				.orderBy(desc("murmurid"))
				.getResultList();
	}

	public List<Murmur> selectOldTwit(int id , List<Integer> murmurId, int userid){
		return select()
				.innerJoin("tuser")
				.leftOuterJoin("favolite",
						new SimpleWhere().eq("userid", userid)
						)
				.where(and(
						new SimpleWhere().in("userid", murmurId.toArray()),
						new SimpleWhere().lt("murmurid", id)
						)
						)
						.orderBy(desc("murmurid"))
						.limit(10)
						.getResultList();
	}

	/**
	 * 検索でひっかかったリストを表示する
	 * @param murmurId
	 * @return
	 */
	public List<Murmur> SelectListSearch(List<Integer> murmurId){
		return select()
				.innerJoin("tuser")
				.where(
						and(
								new SimpleWhere().in("murmurid", murmurId.toArray()),
								new SimpleWhere().eq("tuser.skey", 0)
								)
						)
				.orderBy(desc("murmurid"))
				.getResultList();
	}

	/**
	 * お気に入りフラグを変更する
	 */
	public void updateFavoriteFlagTrue(Murmur murmur){
		jdbcManager.update(murmur).includes("favoritenum").execute();
	}

	/**
	 * つぶやきを格納しつつソーラに登録するためのidを返す
	 * @param murmur
	 * @return
	 */
	public int insertMurmur(Murmur murmur) {
		jdbcManager.insert(murmur).execute();
		//Murmur twit = jdbcManager.selectBySql(Murmur.class, "SELECT LAST_INSERT_ID").getSingleResult();
		List<Murmur> twits = select().orderBy(desc("murmurid")).getResultList();
		Murmur twit = twits.get(0);
		return twit.murmurid;
	}

	/**
	 *
	 * @param murmuruserid
	 * @return
	 */
	public long listCount(List<Integer> murmuruserid) {
		return select().innerJoin("tuser")
				.where(new SimpleWhere()
				.in("userid", murmuruserid.toArray())).getCount();
	}

	public long Count(Integer id) {
		return select().innerJoin("tuser").where("userid", id).getCount();
	}

	public boolean hasPrev(int page) {
		return (page != 0) ? true : false;
	}

	public boolean hasNext(int limit, long total, int page) {
		return ((page + 1) * limit < total) ? true : false;
	}

	public List<MurmurDto> another_user_twitList(int show_userID) {
		return jdbcManager
				.selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
				.getResultList();
	}

	public List<MurmurDto> another_user_twitList(int LIMIT, int page, int show_userID) {
		return jdbcManager
				.selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}


}
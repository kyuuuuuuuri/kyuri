package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.MurmurNames.*;

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
	 *
	 * @param LIMIT
	 * @param page
	 * @param murmur_userid
	 * @return
	 */

	public List<Murmur> mainListPager(int LIMIT, int page, List<Integer> murmur_userid) {
		return jdbcManager.from(Murmur.class).innerJoin("tuser")
				.where(new SimpleWhere().in("userid", murmur_userid.toArray()))
				.orderBy(desc("murmurid"))
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}

	public List<Murmur> listPager(int LIMIT, int page, int id) {
		return select()
				.innerJoin("tuser")
				.where("userid", id)
				.orderBy(desc("murmurid"))
				.limit(LIMIT)
				.offset(page * LIMIT)
				.getResultList();
	}

	/**
	 *
	 * @param murmurId
	 * @return
	 */
	public List<Murmur> SelectListSearch(List<Integer> murmurId){
		return select()
				.innerJoin("tuser")
				.where(new SimpleWhere().in("murmurid", murmurId.toArray()))
				.orderBy(desc("murmurid"))
				.getResultList();
	}

	/**
	 *
	 * @param murmur
	 * @return
	 */
	public int insertMurmur(Murmur murmur) {
		jdbcManager.insert(murmur).execute();
		//Murmur twit = jdbcManager.selectBySql(Murmur.class, "SELECT LAST_INSERT_ID").getSingleResult();
		List<Murmur> twits = select().orderBy(desc("murmurid")).getResultList();
		Murmur twit = twits.get(0);
		//System.out.println(twit.murmurid + "moro");
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
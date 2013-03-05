package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.FavoliteNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Favolite;

/**
 * {@link Favolite}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/02/21 19:54:46")
public class FavoliteService extends AbstractService<Favolite> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public Favolite findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Favolite> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}

	public int[] deleteById(List<Favolite> favoliteList) {
		return jdbcManager.deleteBatch(favoliteList).execute();
	}

	/**
	 * 削除するidのを探します
	 * @param favolite
	 * @return
	 */
	public List<Favolite> findDeleteFavoList(Favolite favolite) {
		return select().where(
				and(
						new SimpleWhere().eq("murmurid", favolite.murmurid),
						new SimpleWhere().eq("userid", favolite.userid)
				)
				).getResultList();
	}

	/**
	 * つぶやきidに一致するmurmurlistを検索します
	 * @param id
	 * @return
	 */
	public List<Favolite> findFavoliList(int id) {
		return select().where("murmurid = ?", id).getResultList();
	}

	/**
	 * つぶやきidに一致するお気に入りを削除します
	 * @param id
	 */
	public void deleteFavoliteList(int id) {
		List<Favolite> favoliteList = findFavoliList(id);

		if (favoliteList.isEmpty()) {
			//何もしない
		} else {
			jdbcManager.deleteBatch(favoliteList).execute();
		}

	}

	/**
	 * お気に入りしているユーザ一覧
	 * @param murmurid
	 * @return
	 */
	public List<Favolite> findFavoUser(int murmurid, int userid){
		return select()
				.innerJoin("tuser")
				.leftOuterJoin("tuser.ffollowList",
						new SimpleWhere().eq("tuser.ffollowList.userid", userid))
				.where("murmurid = ?", murmurid)
				.getResultList();
	}


	//お気に入りされている数をとってくる
	public long findFavoNum(int murmurid){
		return select()
				.where("murmurid = ?", murmurid)
				.getCount();
	}
}
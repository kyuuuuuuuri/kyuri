package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.RetweetsNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Retweets;

/**
 * {@link Retweets}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/03/02 19:05:31")
public class RetweetsService extends AbstractService<Retweets> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public Retweets findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Retweets> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}

	public long findRetweetNum(int murmurid){
		return select()
				.where("murmurid = ?", murmurid)
				.getCount();
	}

	/**
	 * リツイートしているかどうかをチェックする
	 * @param userid
	 * @param murmurid
	 * @return
	 */
	public boolean existRetweetNum(int userid, int murmurid){
		Retweets retweet =
				jdbcManager
				.from(Retweets.class)
				.where(and(
						new SimpleWhere().eq("userid", userid),
						new SimpleWhere().eq("murmurid", murmurid)
						))
				.getSingleResult();
		if(retweet == null){
			return false;
		}
		return true;
	}

	/**
	 * リツイートをとってくる
	 * @param userid
	 * @param murmurid
	 * @return
	 */
	public Retweets findRetweetNumForDel(int userid, int murmurid){
		return
				jdbcManager
				.from(Retweets.class)
				.where(and(
						new SimpleWhere().eq("userid", userid),
						new SimpleWhere().eq("murmurid", murmurid)
						))
				.getSingleResult();

	}

	/**
	 * リツイートしているユーザ一覧を取ってくる
	 * @param murmurid
	 * @return
	 */
	public List<Retweets> findRetweetUser(int murmurid, int userid){
		return select()
				.innerJoin("tuser")
				.leftOuterJoin("tuser.ffollowList",
						new SimpleWhere().eq("tuser.ffollowList.userid", userid))
				.where("murmurid = ?", murmurid)
				.getResultList();
	}

	/**
	 * ツイートが消された後、リツイートされている情報を消す
	 * @param murmurid
	 */
	public void deleteTwitAfterUserDelTwit(int murmurid){
		List<Retweets> retweetsList =
				jdbcManager.from(Retweets.class)
				.where("", murmurid)
				.getResultList();
		jdbcManager.deleteBatch(retweetsList);
	}
}
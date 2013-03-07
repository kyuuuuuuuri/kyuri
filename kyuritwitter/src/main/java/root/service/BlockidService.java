package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.BlockidNames.*;

import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.Blockid;

/**
 * {@link Blockid}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/03/05 13:25:59")
public class BlockidService extends AbstractService<Blockid> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public Blockid findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Blockid> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}

	public Blockid findBlockid(int userid, int blockedUserid) {
		return select()
				.where(
						and(
								new SimpleWhere().eq("userid", userid),
								new SimpleWhere().eq("blockUserid", blockedUserid)
						)
				)
				.getSingleResult();
	}
}
package root.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.InListUserNames.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.extension.jdbc.where.SimpleWhere;

import root.entity.InListUser;

/**
 * {@link InListUser}のサービスクラスです。
 *
 */
@Generated(value = { "S2JDBC-Gen 2.4.45", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" }, date = "2013/02/28 3:52:11")
public class InListUserService extends AbstractService<InListUser> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public InListUser findById(Integer id) {
		return select().id(id).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<InListUser> findAllOrderById() {
		return select().orderBy(asc(id())).getResultList();
	}


	public List<Integer> findUserInTheList(int listid) {
		List<InListUser> inListUser = select().where("listid = ?", listid).getResultList();
		List<Integer> useridList = new ArrayList<Integer>();
		for(InListUser i : inListUser){
			useridList.add(i.listUserid);
			System.out.println(i.listUserid + " kyuri");
		}

		return useridList;
	}

	/**
	 * deleteするユーザを探す
	 * @param listid
	 * @param userid
	 * @return
	 */
	public InListUser delUserInList(int listid, int userid){
		return
				select()
				.where(
						and(
								new SimpleWhere().eq("listid", listid)),
								new SimpleWhere().eq("listUserid", userid)
						)
				.getSingleResult();
}

	/**
	 * userがリストの中に存在するかどうかをチェックする
	 * @param listid
	 * @param userid
	 * @return
	 */
	public boolean existUserInList(int listid, int userid){
		InListUser user =
				select()
				.where(
						and(
								new SimpleWhere().eq("listid", listid)),
								new SimpleWhere().eq("listUserid", userid)
						)
				.getSingleResult();

		if(user == null){
			return false;
		}
		return true;

	}
}
package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.entity.Follow;
import root.entity.Tuser;
import root.form.SearchForm;

public class SearchAction extends SuperAction {

	private final String SearchPageJsp = "searchpage.jsp";
	private final String SearchResultJsp = "searchResult.jsp";

	@ActionForm
	@Resource
	protected SearchForm searchForm;

	public int followCheck;

	public long followcheckcount = 0;
	public List<Integer> fc_userid = new ArrayList<Integer>();
	public String search;

	public List<Tuser> tuserList = new ArrayList<Tuser>();

	@Execute(validator = false)
	public String index() {
		int userid = userDto.userID;
		mine = userid;
		menuFlag = 1;

		String searchUsernick = searchForm.search;
		tuserList = tuserService.tuserSearch(searchUsernick, userid, LIMIT, 0);

		return "searchResult.jsp";
	}

	public List<Tuser> searchUser = new ArrayList<Tuser>();

	//検索処理
	@Execute(validator = false)
	public String searchSubmit() {
		int userid = userDto.userID;
		mine = userid;

		mydata = tuserService.findById(mine);

		//ページ番号を取得
		int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);
		search = searchForm.search;
		searchUser = tuserService.tuserSerch(search);

		List<Integer> fed_userid = new ArrayList<Integer>();

		if (searchUser != null) {

			for (Tuser f : searchUser) {
				fed_userid.add(f.userid);
			}

			//ログインユーザは、そのユーザをフォローしているか検証
			List<Follow> followcheck = followService.findUserFollow(userid);

			followcheckcount = followService.followCount(userid);

			if (followcheckcount != 0) {
				for (Follow c : followcheck) {
					fc_userid.add(c.fuserid);
				}
			}

			//以下、ページング処理
			//総件数を取得
			this.total = searchUser.size();

			searchUser = tuserService.tuserSerch(LIMIT, page, search);

			//前ページがあるかどうかを判定
			hasPrev = murmurService.hasPrev(page);
			//次のページがあるかどうかを判定
			hasNext = murmurService.hasNext(LIMIT, this.total, page);
		}
		return SearchResultJsp;
	}

	@Resource
	HttpServletRequest req;

	//フォローする
	@Execute(validator = false)
	public String toFollow() {
		int userid = userDto.userID;
		String useridStr = req.getParameter("followUserId");
		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol != null) {
			System.out.println("おかしいよ");
			return null;
		} else {
			//フォローしていなかったらフォロワ―をinsertする。
			Follow foluser = new Follow();
			foluser.userid = userid;
			foluser.fuserid = toFollowUserId;

			followService.insert(foluser);

			//フォロー数を更新
			Tuser tuser = tuserService.findById(userid);
			tuser.follow += 1;
			tuserService.updateTuserAfterFollow(tuser);

			//フォロワ―数を更新
			Tuser tuser2 = tuserService.findById(toFollowUserId);
			tuser2.followed += 1;
			tuserService.updateTuserAfterBeFollowed(tuser2);
		}

		return null;
	}

	@Execute(validator = false)
	public String toUnFollow() {
		int userid = userDto.userID;
		String useridStr = req.getParameter("unFollowUserId");
		System.out.println(useridStr + "きゅうり");
		int toFollowUserId = Integer.parseInt(useridStr);

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(toFollowUserId, userid);

		if (fol == null) {
			System.out.println("おかしいよ");
			return null;
		} else {
			//フォローしていなかったらフォロワ―をinsertする。

			followService.delete(fol);

			//フォロー数を更新
			Tuser tuser = tuserService.findById(userid);
			tuser.follow -= 1;
			tuserService.updateTuserAfterFollow(tuser);

			//フォロワ―数を更新
			Tuser tuser2 = tuserService.findById(toFollowUserId);
			tuser2.followed -= 1;
			tuserService.updateTuserAfterBeFollowed(tuser2);
		}
		return null;
	}

	//フォロー解除する

	//検索したユーザをフォローする
	@Execute(validator = false, urlPattern = "infollow/{userid}")
	public String infollow() {

		int userid = userDto.userID;

		mydata = tuserService.findById(userid);

		//すでにフォローしていたら何もしない
		Follow fol = followService.delFollow(searchForm.userid, userid);

		if (fol != null) {
			return "searchSubmit";
		} else {

			//フォローしていなかったらフォロワ―をinsertする…。

			Follow foluser = new Follow();
			foluser.userid = userid;
			foluser.fuserid = searchForm.userid;

			followService.insert(foluser);

			//フォロー数を更新
			Tuser tuser = tuserService.findById(userid);
			tuser.follow += 1;
			tuserService.update(tuser);

			//フォロワ―数を更新
			Tuser tuser2 = tuserService.findById(searchForm.userid);
			tuser2.followed += 1;
			tuserService.update(tuser2);

			//ページ番号を取得
			int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);

			searchUser = tuserService.tuserSerch(searchForm.search);

			List<Integer> fed_userid = new ArrayList<Integer>();

			if (searchUser != null) {
				for (Tuser f : searchUser) {
					fed_userid.add(f.userid);
				}

				//ログインユーザは、そのユーザをフォローしているか検証
				List<Follow> followcheck = followService.findUserFollow(userid);

				followcheckcount = followService.followCount(userid);

				if (followcheckcount != 0) {
					for (Follow c : followcheck) {
						fc_userid.add(c.fuserid);
					}
				}

				//ページング処理
				//総件数を取得
				this.total = searchUser.size();

				searchUser = tuserService.tuserSerch(LIMIT, page, searchForm.search);

				//前ページがあるかどうかを判定
				hasPrev = murmurService.hasPrev(page);
				//次のページがあるかどうかを判定
				hasNext = murmurService.hasNext(LIMIT, this.total, page);
			}

			return "searchResult.jsp";
		}
	}

}

package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.dto.SearchDto;
import root.entity.Follow;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.SearchForm;

public class SearchAction extends SuperAction {

	private final String SearchResultJsp = "searchResult.jsp";

	@ActionForm
	@Resource
	protected SearchForm searchForm;

	public List<SearchDto> searchDto;

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

	public List<Murmur> murmurList = new ArrayList<Murmur>();

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

	@Execute(validator = false)
	public String searchUsershort(){
		String searchUser = req.getParameter("searchword");

		int userid = userDto.userID;

		tuserList = tuserService.tuserSearch(searchUser, userid, 3, 0);
		if(tuserList.isEmpty()){
		}

		return "/main/searchUser.jsp";
	}

	@Execute(validator = false , urlPattern="search/{searchUser}")
	public String searchUserAll(){
		String searchUsernick = searchForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}

}



	//フォロー解除する


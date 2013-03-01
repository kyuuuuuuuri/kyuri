package root.action;

import java.util.ArrayList;
import java.util.List;

import javasource.GetTwitFromSolr;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.SuperAction;
import root.dto.SearchDto;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.SearchtwitForm;

public class SearchtwitAction extends SuperAction{


	@ActionForm
	@Resource
	protected SearchtwitForm searchtwitForm;

	public List<SearchDto> searchDto;

	public List<Tuser> tuserList = new ArrayList<Tuser>();


	@Resource
	HttpServletRequest req;

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();

	public int fFlag =0;


	//返信リストを返すafter
	@Execute(validator = false)
	public String repListAfter() {
		System.out.println("もろきゅう");
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");

		List<Murmur> murmur = murmurService.zibunJoinAfterList(Integer.parseInt(murId), userid);
		if (murmur == null) {
			return null;
		}
		if (murmur.isEmpty()) {
			return null;
		}

		murmurList = murmur;

		return "twitplus.jsp";
	}

	//返信リストを返すbefore
	@Execute(validator = false)
	public String repListBefore() {
		int userid = userDto.userID;
		String murId = req.getParameter("tubuyakiId");
		System.out.println(murId);
		List<Murmur> murmur = murmurService.zibunJoinBeforeList(Integer.parseInt(murId), userid);

		if (murmur.isEmpty()) {
			return null;
		}
		murmurList = murmur;

		return "twitplus.jsp";
	}



//	//solrを検索する
	@Execute(validator = false)
	public String index(){
		menuFlag = 1;
		int id;
		mine = userDto.userID;
		String searchWord = userDto.searchWord;
		System.out.println(searchWord + "mokyusearch");
		List<Integer> idList = new ArrayList<Integer>();

		if (searchWord.isEmpty()) {
			throw new ActionMessagesException("なにか入力してください", false);
		}

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(searchWord, 0);
		if(!searchDto.isEmpty()){
			for (int i = 0; i < searchDto.size(); i++) {
				id = Integer.valueOf(searchDto.get(i).getId());
				idList.add(id);
			}
			murmurList = murmurService.SelectListSearch(idList);
		}

		return "searchtwit.jsp";
	}

	@Execute(validator = false)
	public String searchAjax(){

		fFlag = 1;
		//System.out.println("morokyumain");
		int id;
		String pagePram = req.getParameter("page");
		int page = Integer.valueOf(pagePram);

		List<Integer> idList = new ArrayList<Integer>();

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getAllTwit(userDto.searchWord, page*10);

		if(!searchDto.isEmpty()){
			for (int i = 0; i < searchDto.size(); i++) {
				id = Integer.valueOf(searchDto.get(i).getId());
				idList.add(id);
			}
			murmurList = murmurService.SelectListSearch(idList);
		}

		return "showOldTwit.jsp";
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
		String searchUsernick = searchtwitForm.searchUser;

		return "/search?search="+ searchUsernick +"?redirect=true";
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "userpage/{userni}")
	public String showdata() {

		String nick = searchtwitForm.userni;

		return "/userpage?userni=" + nick + "?redirect=true";
	}

	//hashタグリストを出力する
	@Execute(validator = false, urlPattern = "showHashData/{hashtag}")
	public String showHashData() {
		menuFlag = 1;
		int id;
		String hash = searchtwitForm.hashtag;
		List<Integer> idList = new ArrayList<Integer>();
		mine = userDto.userID;

		GetTwitFromSolr getTwitFromSolr = new GetTwitFromSolr();
		searchDto = new ArrayList<SearchDto>();
		searchDto = getTwitFromSolr.getHashTwit(hash);

		for (int i = 0; i < searchDto.size(); i++) {
			id = Integer.valueOf(searchDto.get(i).getId());
			idList.add(id);
		}

		murmurList = murmurService.SelectListSearch(idList);

		return "searchtwit.jsp";
	}



}

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



}

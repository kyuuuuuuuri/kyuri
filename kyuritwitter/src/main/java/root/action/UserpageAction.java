package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import root.SuperAction;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.UserpageForm;

public class UserpageAction extends SuperAction{


	@ActionForm
	@Resource
	protected UserpageForm userpageForm;

	public int fFlag = 0;

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();


	@Execute(validator = false, urlPattern = "{userni}")
	public String index() {
		String userni = userpageForm.userni;
		int userid = userDto.userID;
		mine = userid;
		menuFlag = 2;

		//System.out.println(userni + "" + userid + " 入ったよ");
		murmurList = murmurService.listPager(LIMIT, 0, userni, userid);

		mydata = tuserService.findByName(userni,userid);

		return "userMypage.jsp";
	}

	@Execute(validator = false)
	public String userpage(){
		return "";
	}

	//ユーザプロフィール画像
	@Execute(validator=false, urlPattern="showUserImg/{userid}")
	public String showUserImg(){
		int id = userpageForm.userid;
		Tuser userImg = tuserService.getTuserImg(id);
		String filename = userImg.userid+".jpg";
		if(userImg != null){
			ResponseUtil.download(filename, userImg.profileimg);
		}

		return null;
	}



}

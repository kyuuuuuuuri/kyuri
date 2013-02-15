package root.action.main;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.*;
import javasource.SetTwitToSolr;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.dto.UserDto;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.MainForm;
import root.service.MurmurService;
import root.service.TuserService;


public class DeleteAction {

	@Resource
	protected UserDto userDto;

	@ActionForm
	@Resource
	protected MainForm mainForm;

	@Resource
    protected MurmurService murmurService;

	@Resource
	protected TuserService tuserService;

	// JdbcManagerのインスタンスを取得
	JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");

	//自分のつぶやきを削除する
		@Execute(validator=false,urlPattern = "{tubuyakiid}")
		public String index(){
			SetTwitToSolr setTwitToSolr = new SetTwitToSolr();

			int userid = userDto.userID;

			int delete_murID = mainForm.tubuyakiid;


			Murmur delResult=new Murmur();
			delResult.murmurid=delete_murID;

			int count=jdbcManager.delete(delResult).execute();

			//solrのも消す
			setTwitToSolr.deleteTwit(delete_murID);

	    	//投稿数を1減らす
	    	Tuser tuser =
	    			jdbcManager
	    			.from(Tuser.class)
	    			.where(eq(tuser().userid(),userid))
	    			.getSingleResult();
	    		tuser.postNum -=1;
	    		tuserService.updateTuserAfterDel(tuser);

			return "/main";
		}


}

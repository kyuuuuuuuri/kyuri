package root;

import javax.annotation.Resource;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

import root.dto.MurmurDto;
import root.dto.UserDto;
import root.entity.Tuser;
import root.service.FavoliteService;
import root.service.FollowService;
import root.service.MurmurService;
import root.service.ReptwitidService;
import root.service.TuserService;

public class SuperAction {

	//Dto Class
	@Resource
	protected UserDto userDto;
	@Resource
	protected MurmurDto murmurDto;


	//Service Class
	@Resource
    protected FollowService followService;
    @Resource
    protected TuserService tuserService;
    @Resource
    protected MurmurService murmurService;
    @Resource
    protected FavoliteService favoliteService;
    @Resource
    protected ReptwitidService reptwitidService;

    //loginUser number
    public int mine=0;

    //mydata
	public Tuser mydata = new Tuser();

	//表示するメニュー
	public int menuFlag = 0;


    //Pager
    //前のページがあるかどうか
	public boolean hasNext = false;
	//次のページがあるかどうか
	public boolean hasPrev = false;
	//総件数
	public long total;
	//1ページに表示する件数
	protected static final int LIMIT = 10;


	//ログアウトmethode
	@Execute(validator=false)
	@RemoveSession(name="userDto")
	public String logout(){
		System.out.println("moro");
		return "/login/";
	}

	//設定
	@Execute(validator=false)
	public String setting(){
		return "/setting/";
	}

}

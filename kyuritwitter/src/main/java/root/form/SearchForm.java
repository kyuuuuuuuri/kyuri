package root.form;

import org.seasar.struts.annotation.IntegerType;

public class SearchForm extends CommonForm{

	public String search;

	@IntegerType
	public String page;

	public Integer userid;

}

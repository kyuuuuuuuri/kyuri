package root.form;

import java.io.Serializable;

import org.seasar.struts.annotation.IntegerType;

public class SearchForm implements Serializable{

	public String search;

	@IntegerType
	public String page;

	public Integer userid;

}

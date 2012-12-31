package root.interceptor;


import java.util.Map;
//import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.Execute;
//import org.seasar.struts.annotation.Execute;

//import root.annotation.Auth;
import root.dto.UserDto;

public class LoginConfirmInterceptor extends AbstractInterceptor {

	  private static final long serialVersionUID = 1L;

	    @Resource
	    protected UserDto userDto;

	    public Object invoke(MethodInvocation invocation) throws Throwable {
	    	return (!isExecuteMethod(invocation) || isLoggedIn())
	                ? invocation.proceed() : "/login?redirect=true";
	    }

//	    private boolean isTargetMethod(MethodInvocation invocation) {
//
//	        Method method = invocation.getMethod();
//	        Class<?> clazz = invocation.getThis().getClass();
//
//	        Execute execute = method.getAnnotation(Execute.class);
//	        Auth auth = clazz.getAnnotation(Auth.class);
//
//	        return execute != null && auth != null;
//	    }

	    private boolean isExecuteMethod(MethodInvocation invocation) {
	        return invocation.getMethod().isAnnotationPresent(Execute.class);
	    }

	    private boolean isLoggedIn() {
	    	 Map<String, Object> sessionScope = SingletonS2Container.getComponent("sessionScope");

	         UserDto dto = (UserDto) sessionScope.get("userDto");

	         return (dto != null && dto.userID != null);
	    }

}

package root.interceptor;

import java.util.Map;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.Execute;

import root.dto.UserDto;

public class LoginActionIfLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Resource
	protected UserDto userDto;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return (!isLoggedIn() && isExecuteMethod(invocation) ? invocation.proceed() : "/main?redirect=true");

	}

    private boolean isExecuteMethod(MethodInvocation invocation) {
        return invocation.getMethod().isAnnotationPresent(Execute.class);
    }


	private boolean isLoggedIn() {
		Map<String, Object> sessionScope = SingletonS2Container.getComponent("sessionScope");

		UserDto dto = (UserDto) sessionScope.get("userDto");

		return (dto != null && dto.userID != null);
	}
}

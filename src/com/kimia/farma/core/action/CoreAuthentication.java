package com.kimia.farma.core.action;

import java.util.Map;

import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CoreAuthentication implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init() {
		
		
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		User user = (User)session.get("userName");
		if(user == null){
			return ActionSupport.LOGIN;
		}
		return actionInvocation.invoke();
	}
	
	
}

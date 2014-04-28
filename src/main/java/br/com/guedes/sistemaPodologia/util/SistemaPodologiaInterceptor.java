package br.com.guedes.sistemaPodologia.util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SistemaPodologiaInterceptor implements Interceptor {

	private static final long serialVersionUID = -1696523737216925098L;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init() {
		// TODO Auto-generated method stub
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String result = invocation.invoke();
		
		return null;
	}
}

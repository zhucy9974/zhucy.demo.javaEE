package demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import demo.view.entityview.UserView;

public class CustomInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		UserView user = (UserView) request.getSession().getAttribute("user");
		if(user == null) {
			return true;
		}
		return true;
	}
}

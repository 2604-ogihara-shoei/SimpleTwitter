package chapter6.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@WebFilter({"/setting", "/edit"})
public class LoginFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession();

		if (httpSession.getAttribute("loginUser") != null) {
			chain.doFilter(request, response);
		} else {
			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add("ログインしてください");
			request.setAttribute("errorMessages", errorMessages);
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
	}
		@Override
		public void init(FilterConfig config) {
		}
		@Override
		public void destroy() {
		}
}


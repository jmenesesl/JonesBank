


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class RedirectFilter
 */
@WebFilter(urlPatterns={"/uno.jsp", "/dos.jsp"})
public class RedirectFilter implements Filter {
       
    /**
     * @see Filter#Filter()
     */
    public RedirectFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
//		chain.doFilter(request, response);
		String path = ((HttpServletRequest)request).getRequestURI();
		if (path.equalsIgnoreCase("/JonesBank/uno.jsp")) {
			request.getRequestDispatcher("dos.jsp").include(request, response);
		} else if (path.equalsIgnoreCase("/JonesBank/dos.jsp")) {
			request.getRequestDispatcher("uno.jsp").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

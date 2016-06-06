/**
 * 
 */
package edu.intuit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Harsha.Ramakrishnapp
 *
 *Class not used. Tomcat error resolved by changing servlet class to org.sun....
 */
public class HttpPageServlet extends HttpServlet{

	private ServletConfig config;
	@Override
	public void destroy() {

		super.destroy();
		System.out.println("Application has stopped");
	}

	@Override
	public void init(ServletConfig config) throws ServletException{

		super.init(config);
		this.config = config;
	}

	

}

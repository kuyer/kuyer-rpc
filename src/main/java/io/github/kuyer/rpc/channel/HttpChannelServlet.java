package io.github.kuyer.rpc.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http服务端Servlet通道
 * @author Rory.Zhang
 */
public class HttpChannelServlet extends HttpServlet {

	private static final long serialVersionUID = 7319203211388583117L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("to http channel servlet.");
	}

}

package uitox.eric.ch13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class EchoServlet extends HttpServlet  {
	private static final long serialVersionUID = 2584290567948312767L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setStatus(HttpServletResponse.SC_OK);

		final ServletInputStream requestInputStream = request.getInputStream();
		final ServletOutputStream responseOutputStream = response
				.getOutputStream();

		IOUtils.copy(requestInputStream, responseOutputStream);
	}
}

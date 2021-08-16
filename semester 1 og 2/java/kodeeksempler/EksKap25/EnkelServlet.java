/*
 * EnkelServlet.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Servleten skriver ut tidspunkt for nedlasting slik det ble registrert på tjenersiden.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EnkelServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print("<html>");
    out.print("<head>");
    out.print("<title>Et eksempel på tjenersideprogrammering</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("Tidspunkt for nedlasting: " + new java.util.Date());
    out.println("</body>");
    out.println("</html>");
  }
}
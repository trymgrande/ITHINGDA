import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;

public class WebServer {
	static HttpServer server;
	public static void main(String[] args) throws IOException {
		final int PORT = 8500;
		final String PATH = "/";
		server = HttpServer.create(new InetSocketAddress(PORT), 0);
		HttpContext context = server.createContext(PATH);
		context.setHandler(WebServer::handleRequest);
		server.start();
		System.out.println("server is running at " + "localhost:" + PORT + PATH);
	}

	private static void handleRequest(HttpExchange exchange) throws IOException {
//		URI requestURI = exchange.getRequestURI();
		printRequestInfo(exchange);
//		String response = "This is the response at " + requestURI;
		Headers requestHeaders = exchange.getRequestHeaders();
		System.out.println(requestHeaders.toString());
		ArrayList<String> headerList = new ArrayList<String>();
		requestHeaders.entrySet().forEach(e -> headerList.add(e.toString()));
		ArrayList<String> headerListNew = new ArrayList<String>();
		headerList.forEach(e -> headerListNew.add("<li>" + e + "</li>"));


		String response = "" +
				"<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <title>Tittel</title>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"    <h1>\n" +
				"        velkomstmelding\n" +
				"    </h1>\n" +
				"	header fra klient er: " +
				"    <ul>\n" +
				headerListNew +
				"    </ul>" +
				"</body>\n" +
				"</html>\n" +
				"\n";

		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		exchange.close();
		server.stop(0);
	}

	private static void printRequestInfo(HttpExchange exchange) {
		System.out.println("-- headers --");
		Headers requestHeaders = exchange.getRequestHeaders();
		requestHeaders.entrySet().forEach(System.out::println);

		System.out.println("-- principle --");
		HttpPrincipal principal = exchange.getPrincipal();
		System.out.println(principal);

		System.out.println("-- HTTP method --");
		String requestMethod = exchange.getRequestMethod();
		System.out.println(requestMethod);

		System.out.println("-- query --");
		URI requestURI = exchange.getRequestURI();
		String query = requestURI.getQuery();
		System.out.println(query);
	}
}
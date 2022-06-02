/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.net.Proxy.Type.HTTP;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author HMartin
 */
@WebServlet(name = "Conexion", urlPatterns = { "/Conexion" })
public class Conexion extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Conexion</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet Conexion at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Si me llega");
		if (request.getParameter("nums").equals("") || request.getParameter("groups").equals("")
				|| request.getParameter("moneyN").equals("") || request.getParameter("moneyG").equals("")) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			Apuesta apuestaVacia = new Apuesta(null, null, null, null);
			NumWin numeroGanador = new NumWin();
			int x = numeroGanador.getNumRuleta();
			apuestaVacia.checkBet();
			System.out.println("llego");
			response.getWriter().append("{\"premio\": \"" + 0 + "\",\"numeroRuleta\":\"" + x + "\"}");
			System.out.println("lo he enviado");
			System.out.println(dtf);

		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			NumWin numeroGanador = new NumWin();
			int x = numeroGanador.getNumRuleta();

			Integer.parseInt(request.getParameter("nums"));
			Apuesta apuesta = new Apuesta(Integer.parseInt(request.getParameter("nums")),
					request.getParameter("groups"), Double.parseDouble(request.getParameter("moneyN")),
					Double.parseDouble(request.getParameter("moneyG")));
			System.out.println("no he mandado nada vaccio");

			apuesta.checkBet();

			System.out.println("he pasado la funcion gains");
			response.getWriter().append("{\"prime\": \"" + apuesta.gains(x) + "\",\"numberW\":\"" + x + "\"}");
			System.out.println("he mandado la info");

		}


		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}

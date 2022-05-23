/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casino;

import java.lang.Math;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
@WebServlet(name = "SlotGame", urlPatterns = {"/SlotGame"})
public class SlotGame extends HttpServlet {

    User user;
    double bet = 0.00;
    Partida partida;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SlotGame</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SlotGame at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int id = 3;

        Reel reel1 = new Reel();
        Reel reel2 = new Reel();
        Reel reel3 = new Reel();

        reel1.setNum1(reel1.numberSelector(reel1.getRandomNumber()));
        reel1.setNum2(reel1.numberSelector(reel1.getRandomNumber()));
        reel1.setNum3(reel1.numberSelector(reel1.getRandomNumber()));

        reel2.setNum1(reel2.numberSelector(reel2.getRandomNumber()));
        reel2.setNum2(reel2.numberSelector(reel2.getRandomNumber()));
        reel2.setNum3(reel2.numberSelector(reel2.getRandomNumber()));

        reel3.setNum1(reel3.numberSelector(reel3.getRandomNumber()));
        reel3.setNum2(reel3.numberSelector(reel3.getRandomNumber()));
        reel3.setNum3(reel3.numberSelector(reel3.getRandomNumber()));
        
        partida = new Partida(id, bet, 0, dtf.format(now));

        double reward = Reel.rewardCalculator(this.partida, reel1, reel2, reel3);

        reward = Math.floor(reward * 100) / 100;
        
        System.out.println(reward);

        double balance = reward - bet;
        
        this.partida.setBalance(balance);
        
        System.out.println(partida.toString());
        
        String[] dateTime = partida.toStringDateTime().split(" ");
        
        System.out.println(partida.toString() + dateTime[0] +" "+ dateTime[1]);
        
        HttpSession session = (HttpSession) request.getSession();
        
        user = (User) session.getAttribute("usuario"); 
        
        try{
            System.out.println(user);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        
        response.getWriter().append("{\"num1\":\"" + reel1.getNum1() + "\",\"num2\":\"" + reel1.getNum2() + "\",\"num3\":\"" + reel1.getNum3() + "\",\"num4\":\"" + reel2.getNum1() + "\",\"num5\":\"" + reel2.getNum2() + "\",\"num6\":\"" + reel2.getNum3() + "\",\"num7\":\"" + reel3.getNum1() + "\",\"num8\":\"" + reel3.getNum2() + "\",\"num9\":\"" + reel3.getNum3() + "\",\"reward\":\"" + reward + "\"}");
        /*
        try {
            QueryClass.updateDB(user, partida);
        } catch (SQLException ex) {
            Logger.getLogger(SlotGame.class.getName()).log(Level.SEVERE, null, ex);
        } */

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        this.bet = Double.parseDouble(request.getParameter("bet"));

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

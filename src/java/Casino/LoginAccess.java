package Casino;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginAccess", urlPatterns = {"/LoginAccess"})
public class LoginAccess extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginAccess</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAccess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        boolean verify = false;

        User user = new User(-1, request.getParameter("password"), "", "", request.getParameter("username"), "");

        try {
            verify = QueryClass.login(user);

        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (verify == true) {
            try {
                user = QueryClass.userData(user);
                response.getWriter().append("{\"ID\":\"" + user.getId() + "\",\"DNI\":\"" + user.toStringDNI() + "\",\"FullName\":\"" + user.toStringFullName() + "\",\"Birth\":\"" + user.toStringNacimiento() + "\",\"Name\":\"" + user.toStringNombre() + "\", \"Check\":" + verify + "}");
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        HttpSession session = request.getSession();
        
        session.setAttribute("usuario", user);
        
        } else {
            response.getWriter().append("{\"ID\":\"" + user.getId() + "\",\"DNI\":\"" + user.toStringDNI() + "\",\"FullName\":\"" + user.toStringFullName() + "\",\"Birth\":\"" + user.toStringNacimiento() + "\",\"Name\":\"" + user.toStringNombre() + "\", \"Check\":" + verify + "}");

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

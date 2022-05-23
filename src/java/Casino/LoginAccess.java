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

    User user;

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

        this.user = new User(-1, request.getParameter("password"), "", "", request.getParameter("username"), "");

        try {
            verify = QueryClass.login(this.user);

        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (verify == true) {
            try {
                this.user.setNombre(QueryClass.userData(this.user).getNombre());
                this.user.setApellido(QueryClass.userData(this.user).getApellido());
                this.user.setFechaNacimiento(QueryClass.userData(this.user).getFechaNacimiento());

                response.getWriter().append("{\"ID\":\"" + this.user.getId() + "\",\"DNI\":\"" + this.user.toStringDNI() + "\",\"FullName\":\"" + this.user.toStringFullName() + "\",\"Birth\":\"" + this.user.toStringNacimiento() + "\",\"Name\":\"" + this.user.toStringNombre() + "\", \"Check\":" + verify + "}");

                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", this.user);

            } catch (SQLException ex) {
                Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            response.getWriter().append("{\"ID\":\"" + this.user.getId() + "\",\"DNI\":\"" + this.user.toStringDNI() + "\",\"FullName\":\"" + this.user.toStringFullName() + "\",\"Birth\":\"" + this.user.toStringNacimiento() + "\",\"Name\":\"" + this.user.toStringNombre() + "\", \"Check\":" + verify + "}");

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

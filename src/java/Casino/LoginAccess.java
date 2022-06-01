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
           
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Access-Control-Allow-Origin", "*");
        boolean verify = false;
        boolean ban = true;

        this.user = new User(-1, request.getParameter("password"), "", "", request.getParameter("username"), "", 0.0);         
        this.user = new User(-1, request.getParameter("password"), "", "", request.getParameter("username"), "", 0.0);


        try {
            ban = QueryClass.banCheck(user);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ban == false) {
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
                    this.user.setId((QueryClass.userData(this.user).getId()));
                    this.user.setCredito(QueryClass.userData(this.user).getCredito());

                    //ARREGLAR
                   /* HttpSession session = request.getSession();
                    System.out.println("sesion creada:" + session.getId());
                    session.setAttribute("user", user); */

                    response.getWriter().append("{\"ID\":\"" + this.user.getId() + "\",\"DNI\":\"" + this.user.toStringDNI() + "\",\"FullName\":\"" + this.user.toStringFullName() + "\",\"Birth\":\"" + this.user.toStringNacimiento() + "\",\"Name\":\"" + this.user.toStringNombre() + "\", \"Credito\":\"" + this.user.toStringCredito() + "\", \"Check\":" + verify + "}");

                } catch (SQLException ex) {
                    Logger.getLogger(LoginAccess.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                response.getWriter().append("{\"ID\":\"" + this.user.getId() + "\",\"DNI\":\"" + this.user.toStringDNI() + "\",\"FullName\":\"" + this.user.toStringFullName() + "\",\"Birth\":\"" + this.user.toStringNacimiento() + "\",\"Name\":\"" + this.user.toStringNombre() + "\", \"Check\":" + verify + "}");

            }
        }
        else{
            response.getWriter().append("Esta cuenta tiene el acceso restringido");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

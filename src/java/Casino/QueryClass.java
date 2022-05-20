package Casino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryClass {

    private static final String JDBC = "jdbc:mysql://localhost:3306/casino";

    public static boolean login(User user) throws SQLException {

        boolean login = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");
        Statement st = con.createStatement();

        String psswd = "SELECT contrasenia FROM contrasenia WHERE contrasenia.idUsuario = (SELECT id FROM usuario WHERE usuario.DNI = " + "\"" + user.getDni().toUpperCase() + "\");";
        String username = "SELECT existe('" + user.getDni() + "') AS existe";

        ResultSet rs1 = st.executeQuery(username);

        while (rs1.next()) {
            if (rs1.getBoolean("existe") == false) {
                login = false;
            }
        }

        ResultSet rs = st.executeQuery(psswd);

        while (rs.next()) {
            String password = rs.getString("contrasenia");

            if (!user.getPassword().equals(password)) {
                login = false;
            }
        }

        return login;

    }

    public static User userData(User user) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");
        String query = "SELECT * FROM datosusuario WHERE DNI =" + "\"" + user.getDni() + "\";";
        String query1 = "SELECT id FROM usuario WHERE DNI =" + "\"" + user.getDni() + "\";";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            user.setNombre(rs.getString("Nombre"));
            user.setApellido(rs.getString("Apellido"));
            //user.setCredito(Double.parseDouble(rs.getString("Credito")));
            user.setFechaNacimiento(rs.getString("fechaNacimiento"));

        }

        rs = st.executeQuery(query1);

        while (rs.next()) {
            user.setId(Integer.parseInt(rs.getString("id")));
        }

        return user;

    }

    public static void updateDB(Partida partida, int id, User user) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();

        String update = "INSERT INTO partida VALUES (\"" + id + "\",\"" + partida.getId() + "\",\"" + partida.getBet() + "\", \"" + user.getId() +"\",\"" + partida.getBalance() + "\",\"" + partida.getFechaHora() + "\");";

        st.executeUpdate(update);

    }

    public static void newUser(User user) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();

        String insert1 = "INSERT INTO datosusuario VALUES (\"" + user.getDni().toUpperCase() + "\", \"" + user.getFechaNacimiento() + "\", \"" + user.getNombre() + "\", \"" + user.getApellido() + "\");";
        String insert2 = "INSERT INTO usuario (DNI) VALUES (\"" + user.getDni().toUpperCase() + "\");";
        String insert3 = "INSERT INTO contrasenia VALUES ((SELECT id FROM usuario WHERE DNI = \"" + user.getDni().toUpperCase() + "\"), \"" + user.getPassword() + "\");";

        st.executeUpdate(insert1);
        st.executeUpdate(insert2);
        st.executeUpdate(insert3);
    }

}

package Casino;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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

        String psswd = "SELECT contrasenia FROM contrasenia WHERE contrasenia.idUsuario = (SELECT id FROM usuario WHERE usuario.DNI = " + "\"" + user.getDni().toUpperCase() + "\");";
        
        Statement st = con.createStatement();
        CallableStatement cstmt = con.prepareCall("{CALL existe(? , ?)}");

        cstmt.setString(1, user.getDni());
        boolean mysql = false;
        cstmt.setBoolean(2, mysql);
        
        
        System.out.println(cstmt);
        
        cstmt.execute();

        ResultSet rs1 = cstmt.getResultSet();
        
        System.out.println(rs1);

        if (rs1.getBoolean(1) == false) {
            System.out.println(cstmt.getInt(1));
            login = false;
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

    public static void updateDB(User user, Partida partida) throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();

        String update = "INSERT INTO partida VALUES (\"" + partida.getIdJuego() + "\", \"" + user.getId() + "\", \"" + partida.getBet() + "\",\"" + partida.getBalance() + "\",\"" + partida.getFechaHora() + "\");";

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

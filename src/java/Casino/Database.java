
package Casino;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static String getSelect(String id) throws SQLException {
        
        String DNI = "";
        String url = "jdbc:mysql://localhost:3306/?user=root";
        

        Connection con = DriverManager.getConnection(url);

        /*para ejecutar por ejemplo una query para sacar el dni del usuario*/
                
        Statement st = con.createStatement();
        
        String query = "SELECT dni, contraseña FROM Usuarios WHERE dni = '"+id+"'"; //DNI igual a lo que introduzca desde el front
        
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            DNI = rs.getString("dni");
        }
        
        return DNI;
    }
}
import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345678");

        Statement st=con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini
        // 16000 olarak UPDATE edin.
        String sql1="update companies set number_of_employees=16000 where number_of_employees<" +
                "(select avg(number_of_employees) from companies)";
        int updateEdilenSatirSayisi=st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

        ResultSet resultSet=st.executeQuery("select * from companies");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+"--"+resultSet.getString(2)+"--"+resultSet.getInt(3));
        }

        con.close();
        st.close();
        resultSet.close();




    }

}

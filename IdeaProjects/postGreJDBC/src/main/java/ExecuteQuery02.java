import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con= DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345678");

        Statement st=con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan
        // company ve number_of_employees değerlerini çağırın.

        //1. yol: offset ve fetch next ile
        String sql1="select company, number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 row only";
        ResultSet resultSet1=st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"--"+resultSet1.getInt("number_of_employees"));
        }

        //2. yol: Subquery ile
        String sql2="select company, number_of_employees from companies where number_of_employees=" +
                "(select max(number_of_employees) from companies where number_of_employees<" +
                "(select max(number_of_employees) from companies))";
        ResultSet resultSet2=st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"--"+resultSet2.getInt(2));
        }

        con.close();
        st.close();
        resultSet2.close();
        resultSet1.close();


    }


}

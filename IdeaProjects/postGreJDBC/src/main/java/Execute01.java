import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. adim= driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. adim= database'e baglan
        Connection con= DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345678");

        //3. adim= statement olustur
        Statement st=con.createStatement();

        //4. adim= query calistir

        /*
            execute() methodu DDL(create, drop, alter table) ve DQL(select) icin kullanilabilir.
            1) eger execute() methodu DDL icin kullanilirsa 'false' return yapar.
            2) eger execute() methodu DQL icin kullanilirsa 'true' aksi halde 'false' verir.
         */

        //1. ornek= 'workers' adinda bir table olusturup 'worker_id, worker_name, worker_salary' sutunlari ekleyin
        boolean sql1=
                st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println("sql1 = " + sql1);//false return eder cunku data cagirmiyoruz.

        //2. ornek= table' a worker_address sutunu ekleyerek alter yapin
        st.execute("alter table workers add worker_address varchar(80)");

        //3. ornek= workers table'ini silin.
        st.execute("drop table workers");

        //5. adim= baglanti ve statement'i kapat
        con.close();
        st.close();


    }

}

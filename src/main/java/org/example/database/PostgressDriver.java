package org.example.database;

import lombok.val;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.framework.Constants.*;

public class PostgressDriver {

 public    Jdbc3PoolingDataSource jdbc3PoolingDataSource = new Jdbc3PoolingDataSource();

    {   jdbc3PoolingDataSource.setDataSourceName("A Test Data Source");
        jdbc3PoolingDataSource.setServerNames(new String[]{SERVER_HOST});
        jdbc3PoolingDataSource.setPortNumbers(new int[]{PORT_NO});
        jdbc3PoolingDataSource.setDatabaseName(DB_NAME);
        jdbc3PoolingDataSource.setUser(POSTGRES_USERNAME);
        jdbc3PoolingDataSource.setPassword(POSTGRES_PASSWORD);
        jdbc3PoolingDataSource.setMaxConnections(2);
    }

    public void read() throws SQLException {

        try (val conn = jdbc3PoolingDataSource.getConnection();
             val st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             val res = st.executeQuery("SELECT * FROM customers");
            ) {
            if (res.isBeforeFirst()) {
                System.out.println("Result set not empty");
            }else {
                System.out.println("Empty");
            }
//            val s = res.getMetaData();
//            System.out.println(s.getColumnCount());
//
//            System.out.println(s.getColumnLabel(1) + "\t" + s.getColumnTypeName(1));
//            System.out.println(s.getColumnLabel(2) + "\t" + s.getColumnTypeName(2));
//            System.out.println(s.getColumnLabel(3) + "\t" + s.getColumnTypeName(3));
//            System.out.println(s.getColumnLabel(4) + "\t" + s.getColumnTypeName(4));

        }
    }
    public static void main(String[] args) throws SQLException {
        val s = new PostgressDriver();
        s.read();
    }
}

package databasedrivertests;

import lombok.val;
import org.example.database.PostgressDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Test
public class PostgressTests {

    public void northwindTestOne()  {
        PostgressDriver postgressDriver = new PostgressDriver();
        try (val conn = postgressDriver.jdbc3PoolingDataSource.getConnection();
             val st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             val res = st.executeQuery("SELECT * FROM cities")
        ) {
            if (res.isBeforeFirst()) {
                System.out.println("Result set not empty");
            }else {
                Assert.fail("Empty Result set");
            }

            while (res.next()){
                System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" +
                        res.getInt(3) + "\t" + res.getInt(4));
            }
    }catch (SQLException e){
            e.printStackTrace();
        }
}

}

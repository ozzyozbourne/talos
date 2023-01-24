package org.example.framework;

import java.io.File;

public interface Constants {

   String PATH_TEST_RC = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
   String TIER = System.getProperty("tier");
   String BROWSER = System.getProperty("browser");
   String SERVER = System.getProperty("server");
   String PRODUCT = System.getProperty("product");

   String POSTGRES_PASSWORD = "qwerty";
   String POSTGRES_USERNAME = "ozzyoz";
   String DB_NAME = System.getProperty("dbname");
   String SERVER_HOST = "172.17.0.2";
   Integer PORT_NO = 5432;

   String PATH_TO_XLSX = PATH_TEST_RC + "XLSXFiles" + File.separator + "testpage.xlsx";



}

package org.example.framework;

import java.io.File;

public interface Constants {

   String PATH_TEST_RC = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
   String TIER = System.getProperty("tier");
   String BROWSER = System.getProperty("browser");
   String SERVER = System.getProperty("server");
   String PRODUCT = System.getProperty("product");

}

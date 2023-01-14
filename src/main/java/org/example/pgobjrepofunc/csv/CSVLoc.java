package org.example.pgobjrepofunc.csv;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.assertj.core.api.Assertions;
import org.example.pojos.PgObjCSV;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.example.framework.Constants.PATH_TEST_RC;
import static org.example.framework.Constants.TIER;
import static org.example.readers.CSV.getPojoCsvList;

public final  class CSVLoc {

    private static final String PATH_TO_DIR = PATH_TEST_RC + "CSVPageObjRepo" + File.separator;

    private CSVLoc() {
    }

    public static ImmutablePair<String, String> getPair(final String fileName, final String LocatorName) {
        String pathToFile = PATH_TO_DIR + TIER + File.separator + fileName + ".csv";
        PgObjCSV pgObjCSV = null;
        try {
           pgObjCSV = getPgObjCSVPojo(pathToFile, LocatorName);
        }catch (RuntimeException | IOException e) {
            pathToFile = PATH_TO_DIR + "common" + File.separator + fileName + ".csv";
            try {
                pgObjCSV = getPgObjCSVPojo(pathToFile, LocatorName);
            }catch (IOException ef) {
                ef.printStackTrace();
                Assert.fail("No such locator file -> " + fileName);
            }
        }
        return new ImmutablePair<>(pgObjCSV.getType().trim(), pgObjCSV.getLocatorValue().trim());
    }

    private static PgObjCSV searchInPojoList(final List<PgObjCSV> pgObjCSVList, final String LocatorName) throws RuntimeException{
       return pgObjCSVList.parallelStream().filter(l -> l.getElementName().trim().equals(LocatorName)).findFirst()
                .orElseThrow(() -> new RuntimeException("No such locator"));
    }

    private static PgObjCSV getPgObjCSVPojo(final String pathToFile, final String LocatorName) throws IOException {
        Optional<List<PgObjCSV>>  optionalPgObjCSVS =  getPojoCsvList(pathToFile, PgObjCSV.class);
        Assertions.assertThat(optionalPgObjCSVS).as("NULL POJO LIST!").isPresent();
        return searchInPojoList(optionalPgObjCSVS.get(), LocatorName);
    }

}

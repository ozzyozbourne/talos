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
import static org.example.utils.Interpolation.*;

public final  class CSVLoc {

    private static final String PATH_TO_DIR = PATH_TEST_RC + "CSVPageObjRepo" + File.separator;

    private CSVLoc() {
    }

    private static ImmutablePair<String, String> getPair(String fileName, String LocatorName) {
        String pathToFile = PATH_TO_DIR + TIER + File.separator + fileName + ".csv";
        PgObjCSV pgObjCSV = null;
        Optional<List<PgObjCSV>> optionalPgObjCSVS;
        try {
           optionalPgObjCSVS =  getPojoCsvList(pathToFile, PgObjCSV.class);
           Assertions.assertThat(optionalPgObjCSVS).as("NULL POJO LIST!").isPresent();
           pgObjCSV = searchInPojoList(optionalPgObjCSVS.get(), LocatorName);
        }catch (RuntimeException | IOException e) {
            pathToFile = PATH_TO_DIR + "common" + File.separator + fileName + ".csv";
            try {
                optionalPgObjCSVS =  getPojoCsvList(pathToFile, PgObjCSV.class);
                Assertions.assertThat(optionalPgObjCSVS).as("NULL POJO LIST!").isPresent();
                pgObjCSV = searchInPojoList(optionalPgObjCSVS.get(), LocatorName);
            }catch (IOException ef) {
                ef.printStackTrace();
                Assert.fail("No such locator file -> " + fileName);
            }
        }
        System.out.println(pgObjCSV);
        return new ImmutablePair<>(pgObjCSV.getType().trim(), pgObjCSV.getLocatorValue().trim());
    }

    private static PgObjCSV searchInPojoList(List<PgObjCSV> pgObjCSVList, String LocatorName) throws RuntimeException{
       return pgObjCSVList.parallelStream().filter(l -> l.getElementName().trim().equals(LocatorName)).findFirst()
                .orElseThrow(() -> new RuntimeException("No such locator"));
    }

    public static ImmutablePair<String, String> getInterpolatedLoc(String fileName, String LocatorName, String ... replacements){
        ImmutablePair<String, String> immutablePair = getPair(fileName, LocatorName);
        System.out.println(immutablePair.left + "\t" + immutablePair.right);
        final String interpolatedString = switch (replacements.length){
            case 0   -> immutablePair.right;
            case 1   -> replaceOne(immutablePair.right, replacements[0]);
            case 2   -> replaceTwo(immutablePair.right, replacements[0], replacements[1]);
            case 3   -> replaceThree(immutablePair.right, replacements[0], replacements[1], replacements[2]);
            default  -> throw new IllegalStateException("To many replacements only 3 are supported");
            };
        return new ImmutablePair<>(immutablePair.left, interpolatedString);
    }
}

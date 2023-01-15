package org.example.pgobjrepofunc.xlsx;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.assertj.core.api.Assertions;
import org.example.pojos.PgOjbXLSX;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.example.framework.Constants.PATH_TEST_RC;
import static org.example.framework.Constants.TIER;
import static org.example.readers.PoijiXl.getPojoFromXlsx;

public final class XLSXLoc {

    private static final String PATH_TO_DIR = PATH_TEST_RC + "XLSXPageObjRepo" + File.separator;

    private XLSXLoc() {
    }

    public static ImmutablePair<String, String> getPair(final String fileName, final String sheetName , final String LocatorName) {
        String pathToFile = PATH_TO_DIR + TIER + File.separator + fileName + ".xlsx";
        PgOjbXLSX pgOjbXLSX;
        try {
            pgOjbXLSX = getPgOjbXLSXPojo(pathToFile, sheetName, LocatorName);
        }catch (RuntimeException e) {
            pathToFile = PATH_TO_DIR + "common" + File.separator + fileName + ".xlsx";
            pgOjbXLSX = getPgOjbXLSXPojo(pathToFile, sheetName, LocatorName);
        }
        return new ImmutablePair<>(pgOjbXLSX.getType(), pgOjbXLSX.getLocatorValue());
    }

    private static PgOjbXLSX searchInPojoList(final List<PgOjbXLSX> pgOjbXLSXList, final String LocatorName) throws RuntimeException{
        return pgOjbXLSXList.parallelStream().filter(l -> l.getElementName().equals(LocatorName)).findFirst()
                .orElseThrow(() -> new RuntimeException("No such locator"));
    }

    private static PgOjbXLSX getPgOjbXLSXPojo(final String pathToFile, final String sheetName, final String LocatorName) {
        Optional<List<PgOjbXLSX>> optionalPgOjbXLSXList = Optional.of(getPojoFromXlsx(pathToFile, sheetName, PgOjbXLSX.class));
        Assertions.assertThat(optionalPgOjbXLSXList).as("NULL POJO LIST!").isPresent();
        return searchInPojoList(optionalPgOjbXLSXList.get(), LocatorName);
    }
}

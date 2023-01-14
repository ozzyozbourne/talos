package org.example.readers;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.example.framework.Constants;

import java.io.File;
import java.util.List;

public final class PoijiXl {

    private final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "XLSX" + File.separator;

    private PoijiXl() {
    }

    public static <T> List<T> getPojoFromXlsx(final String fileName, final String sheetName, final Class<T> t) {
        final String path = PATH_TO_DIR + fileName + ".xlsx";
        final PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName(sheetName).build();
        return Poiji.fromExcel(new File(path), t, options);
    }

}

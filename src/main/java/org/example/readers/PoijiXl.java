package org.example.readers;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public final class PoijiXl {

    private PoijiXl() {
    }

    public static <T> List<T> getPojoFromXlsx(final String filePath, final String sheetName, final Class<T> t) {
        final PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName(sheetName).build();
        return Poiji.fromExcel(new File(filePath), t, options);
    }

}

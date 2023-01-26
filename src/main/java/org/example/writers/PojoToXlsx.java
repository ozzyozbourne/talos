package org.example.writers;

import lombok.val;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.example.writers.ApachePOI.updateCellXlsx;
import static org.example.writers.ApachePOI.updateRowXlsx;

public final class PojoToXlsx {

    private PojoToXlsx() {
    }

    public static void writeRow(final ImmutablePair<Integer, Map<Integer,String >> immutablePair, final String fileName, final String sheetName) throws IOException {
        val map  = immutablePair.right;
        val valueList = new ArrayList<String>();
        map.entrySet().spliterator().forEachRemaining(s -> valueList.add(s.getValue()));
        updateRowXlsx(fileName, sheetName, immutablePair.left, valueList);
    }

    public static void writeCell(final ImmutablePair<Integer, Map<Integer,String >> immutablePair, final String fileName, final String sheetName)  {
        val map  = immutablePair.right;
        map.forEach((col, colValue) -> {try {updateCellXlsx(fileName, sheetName, immutablePair.left, col, colValue);}
        catch (IOException e) {throw new RuntimeException(e);}});
    }
}

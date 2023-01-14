package org.example.utils;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.text.StringSubstitutor;

import static org.example.pgobjrepofunc.csv.CSVLoc.getPair;
import static org.example.pgobjrepofunc.xlsx.XLSXLoc.getPair;


public final class Interpolation {

    private Interpolation() {
    }

    public static String replaceOne(final String input, final String replace){
        return new StringSubstitutor(ImmutableMap.of("0", replace)).replace(input);
    }

    public static String replaceTwo(final String input, final String replaceOne, final String replaceTwo){
        return new StringSubstitutor(ImmutableMap.of("0", replaceOne, "1", replaceTwo)).replace(input);
    }

    public static String replaceThree(final String input, final String replaceOne, final String replaceTwo, final String replaceThree){
        return new StringSubstitutor(ImmutableMap.of("0", replaceOne, "1", replaceTwo, "2", replaceThree))
                .replace(input);
    }

    public static ImmutablePair<String, String> getLocatorCSV(final String fileName, final String LocatorName, final String ... replacements) {
        ImmutablePair<String, String> immutablePair = getPair(fileName, LocatorName);
        return interpolatePair(immutablePair, replacements);
    }

    public static ImmutablePair<String, String> getLocatorXLSX(final String fileName, final String sheetName, final String LocatorName, final String ... replacements) {
        ImmutablePair<String, String> immutablePair = getPair(fileName,sheetName, LocatorName);
        return interpolatePair(immutablePair, replacements);
    }

    private static ImmutablePair<String, String> interpolatePair(final ImmutablePair<String, String> immutablePair, final String ... replacements) {
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

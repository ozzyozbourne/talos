package org.example.utils;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.text.StringSubstitutor;

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

}

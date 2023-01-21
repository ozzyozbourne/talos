package org.example.readers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import org.assertj.core.api.Assertions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public final class Jsn {

    private Jsn() {
    }

    public static <T> T valFromJson(final String pathToFile, final String pathToGet, final Predicate ... filters){
        Optional<T> t = Optional.empty();
        try {
            t = Optional.of(JsonPath.read(new FileReader(pathToFile), pathToGet, filters));
        }catch (IOException e) {
            System.out.println("IO Exception occurred in Yaml Reader");
            e.printStackTrace();
        }
        Assertions.assertThat(t).isPresent();
        return t.get();
    }

}

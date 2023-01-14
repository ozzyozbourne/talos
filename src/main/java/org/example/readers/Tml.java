package org.example.readers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.assertj.core.api.Assertions;
import org.example.framework.Constants;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class Tml {

    private final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "TOML" + File.separator;

    private Tml() {
    }

    private static Optional<String> path() {
        return switch (Constants.TIER) {
            case "dev"   -> Optional.of(PATH_TO_DIR + "DEV.toml");
            case "stage" -> Optional.of(PATH_TO_DIR + "STAGE.toml");
            case "prod"  -> Optional.of(PATH_TO_DIR + "PROD.toml");
            default      -> Optional.empty();
        };
    }

    private static <T> T tmlToJson(final String pathToGet, final String pathToFile){
        Optional<T> t = Optional.empty();
        try {
            final JsonNode jsonNode = new TomlMapper().readTree(new File(pathToFile));
            t = Optional.of(JsonPath.read(jsonNode.toString(), pathToGet));
        }catch (IOException e) {
            System.out.println("IO Exception occurred in Toml Reader");
            e.printStackTrace();
        }
        Assertions.assertThat(t).isPresent();
        return t.get();
    }

    public static <T> T getValue(String pathJson) {
        Assertions.assertThat(path()).as("WRONG TIER VALUE!").isNotEmpty();
        T t;
        try {
            t = tmlToJson(pathJson, path().get());
        }catch (PathNotFoundException e) {
            t = tmlToJson(pathJson, PATH_TO_DIR + "COMMON.toml");
        }
        return t;
    }

    public static final Function<String, String> getString                  = Tml::getValue;
    public static final Function<String, Map<String, String>> getStringMap  = Tml::getValue;
    public static final Function<String, List<String>> getStringList        = Tml::getValue;

}

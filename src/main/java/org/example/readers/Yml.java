package org.example.readers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.assertj.core.api.Assertions;
import org.example.framework.Constants;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class Yml {

    private final static String PATH_TO_DIR = Constants.PATH_TEST_RC + "YAML" + File.separator;

    private Yml() {
    }

    private static Optional<String> path() {
        return switch (Constants.TIER) {
            case "dev"   -> Optional.of(PATH_TO_DIR + "DEV.yaml");
            case "stage" -> Optional.of(PATH_TO_DIR + "STAGE.yaml");
            case "prod"  -> Optional.of(PATH_TO_DIR + "PROD.yaml");
            default      -> Optional.empty();
        };
    }

    private static <T> T ymlToJson(final String pathToGet, final String pathToFile){
        Optional<T> t = Optional.empty();
        try {
            final JsonNode jsonNode = new ObjectMapper().valueToTree(new Yaml().load(new FileReader(pathToFile)));
            t = Optional.of(JsonPath.read(jsonNode.toString(), pathToGet));
        }catch (IOException e) {
            System.out.println("IO Exception occurred in Yaml Reader");
            e.printStackTrace();
        }
        Assertions.assertThat(t).isPresent();
        return t.get();
    }

    public static <T> T getValue(final String pathJson) {
        Assertions.assertThat(path()).as("WRONG TIER VALUE!").isNotEmpty();
        T t;
        try {
            t = ymlToJson(pathJson, path().get());
        }catch (PathNotFoundException e) {
            t = ymlToJson(pathJson, PATH_TO_DIR + "COMMON.yaml");
        }
        return t;
    }

    public static final Function<String, String> getString                  = Yml::getValue;
    public static final Function<String, Map<String, String>> getStringMap  = Yml::getValue;
    public static final Function<String, List<String>> getStringList        = Yml::getValue;

}

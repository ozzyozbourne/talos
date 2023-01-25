package org.example.annotationconsumer;

import lombok.val;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.example.annotations.ColumnNumber;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ConsumeSheetColumn {

    private static final String ERROR_MSG = "Private Row field Get Method missing -> ";

    private ConsumeSheetColumn() {
    }

    public static <T> ImmutablePair<Integer, Map<Integer,String >> getXlsxTarget(final T t, final Class<T> tClass) throws IllegalAccessException, InvocationTargetException {
        val fields = Objects.requireNonNull(tClass.getDeclaredFields());
        val len = fields.length;

        if(len == 0) throw new IllegalAccessException("No Declared Fields are present");
        val rowGetMethod = getTargetMethodName(fields[0]);
        System.out.println(rowGetMethod);
        int rowNumber = (int)getValue(t, tClass, rowGetMethod);

        final Map<Integer, String> valMap = new HashMap<>();

        for(int i = 1; i < len; i++)
            valMap.put(fields[i].getAnnotation(ColumnNumber.class).number(),
                    (String)getValue(t, tClass , getTargetMethodName(fields[i])));

        return new ImmutablePair<>(rowNumber, valMap);

    }

    private static <T> Object getValue(final T t, final Class<T> tClass, final String methodName) throws InvocationTargetException, IllegalAccessException {
      return Arrays.stream(tClass.getDeclaredMethods())
                .filter(s -> s.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException( ERROR_MSG + methodName + "()"))
                .invoke(t);
    }

    private static String getTargetMethodName(final Field field){
        return new StringBuffer(field.getName().substring(1))
                .insert(0, (char)(field.getName().charAt(0) - 32)).insert(0, "get").toString();
    }
}

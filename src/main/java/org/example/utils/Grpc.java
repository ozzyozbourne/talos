package org.example.utils;

import com.google.protobuf.*;
import com.google.protobuf.util.JsonFormat;
import lombok.val;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class Grpc {

    private Grpc() {}

    public static String convertToJson(MessageOrBuilder messageOrBuilder) throws InvalidProtocolBufferException {return JsonFormat.printer().includingDefaultValueFields().print(messageOrBuilder);}

    public static <T extends AbstractMessageLite<?,?>> void writeToBin(T t, String path) throws IOException {
        try(val fos = new FileOutputStream(path)){t.writeTo(fos);}}

    public static <T extends GeneratedMessageV3> T readFromBin(ErrorFunc<FileInputStream, T> tFunction, String path) throws IOException {
        return tFunction.apply(new FileInputStream(path));}

    @FunctionalInterface public interface ErrorFunc<T, R> { R apply (T var) throws IOException;}
    @FunctionalInterface public interface ConvertMessage<T, R> { R apply (T var) throws InvalidProtocolBufferException;}

}

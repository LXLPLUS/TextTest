package lib.types;

import lib.exception.ParserFailedException;

import java.lang.reflect.Type;

/**
 * 接口类
 */
public interface MapperStrToObjInterface {


    boolean check(String str, Type type) throws ParserFailedException;

    Object getMessage(String str, Type type) throws ParserFailedException;

    String getTypeName();
}

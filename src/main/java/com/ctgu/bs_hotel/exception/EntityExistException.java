package com.ctgu.bs_hotel.exception;

import org.springframework.util.StringUtils;

/**
 * ClassName EntityExistException
 * Description
 * Create by luochuang
 * Date 2022/3/27 9:58 上午
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}

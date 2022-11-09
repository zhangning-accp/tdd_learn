package com.zn.tdd;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/11/2-18:58:50
 */
public class ArgsParse {

    private static Object returnDefaultObject(Schema schema, String value) {
        if (schema.getTypeClass() == Boolean.class && isEmpty(value)) {
            return false;
        }
        if(schema.getTypeClass() == Boolean.class && !isEmpty(value)) {
            return  Boolean.parseBoolean(value);
        }

        if (schema.getTypeClass() == Integer.class && isEmpty(value)) {
            return 0;
        }
        if (schema.getTypeClass() == Integer.class && !isEmpty(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        if (schema.getTypeClass() == String.class && isEmpty(value)) {
            return "";
        }
        if (schema.getTypeClass() == String.class && !isEmpty(value)) {
            return value;
        }
        if(schema.getTypeClass().isArray()) {
            Class clazz = schema.getTypeClass();
            clazz = clazz.getComponentType();
            String [] values = value.split(",");
            if(clazz == String.class) {
                return values;
            }
            if(clazz == Boolean.class || clazz == Boolean.TYPE) {
                Boolean [] booleans = (Boolean[]) ConvertUtils.convert(values,Boolean.class);
                return booleans;
            }
            if(clazz == Integer.class || clazz == Integer.TYPE) {
                Integer [] integers = (Integer[]) ConvertUtils.convert(values,Integer.class);
                return integers;
            }
        }
        return null;
    }


    private static boolean isEmpty(String value) {
        if(value == null || value.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static Map<String, Object> parse(String command, Schema... schemas) {
        //1. 解析command
        //String command = "-l -p 8080 -d /usr/logs";
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < schemas.length; i++) {
            Schema schema = schemas[i];
            String key = schema.getCommandName();
            Object value = "";
            if (i == schemas.length - 1) {

                value = getCommandValue(command,command.indexOf(key) + 1,
                        command.length(),schema);

                map.put(key, value);
            } else {

                value = getCommandValue(command,command.indexOf(key) + 1,
                        command.indexOf(schemas[i + 1].getCommandName()) - 1,schema);

                command = command.substring(command.indexOf(schemas[i + 1].getCommandName()) - 1);

                map.put(key, value);
            }
        }
        return map;
    }

    public static Object getCommandValue(String command,int start,int end,Schema schema){
        Object value;
        String tmpString = command.substring(start,end);
        tmpString = tmpString.trim();
        value = returnDefaultObject(schema, tmpString);

        return value;
    }
}

package com.zn.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/11/2-18:43:18
 */
public class ArgsParseTest {
    //1. 首先理解需求：开发者希望有一个解析命令行参数的工具，能够把一串命令解析出来
    //2. 思考这个工具类会怎么被使用
//    Args.parse("命令", new Schema("l",Boolean.class),
////    new Schema("p",Integer.Class),
////    new Schema("d",List<Integer>.class));
    //3. 拆分任务：样本：-l -p 8080 -d /usr/logs
    /**
     * 3.1 数据类型             3.2 默认值
     *  l- boolean          boolean-false
     *  p- int              int-0
     *  d- string            string-''


     *  Happy Path          Sad Path
     *  l:true              l:false
     *  p:8080              p:0
     *  d: /usr/logs        d:''
     *                      m:exception
     *
     *v2
     * -g this,is,a,list -m 1,2,-3,5
     *     g:表示是一个字符串列表（数组）[“this”, “is”, “a”, “list”]。
     *     m:表示包含一个整数类型的列表：[1, 2, -3, 5]。
     */
    String command = "-l -p 8080 -d /usr/logs";

    Schema [] schemas = new Schema[]{
            new Schema("l", Boolean.class),
            new Schema("p",Integer.class),
            new Schema("d",String.class),
            new Schema("g",String [].class),
            new Schema("m",int [].class)
    };

    //-------------------------------------------------------
    //-------- 单命令测试 -------------------------------------
    //-------------------------------------------------------
    @Test
    public void parse_map_with_command_line_boolean_false(){
        String command = "-l ";
        Schema [] schemas = new Schema[]{
                new Schema("l", Boolean.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("l",false);
        Assert.assertEquals(testMap,map);

        command = "-l false";
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);

        command = "-l 23";
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);

        command = "-l abc";
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);

        command = "-l";
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);
    }

    @Test
    public void parse_map_with_command_line_boolean_true(){
        String command = "-l true";
        Schema [] schemas = new Schema[]{
                new Schema("l", Boolean.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("l",true);
        Assert.assertEquals(testMap,map);

    }
    @Test
    public void parse_map_with_command_line_int_0(){
        String command = "-p true";
        Schema [] schemas = new Schema[]{
                new Schema("p", Integer.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("p",0);
        Assert.assertEquals(testMap,map);

        command = "-p ";
        schemas = new Schema[]{
                new Schema("p", Integer.class)};
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);

        command = "-p e4";
        schemas = new Schema[]{
                new Schema("p", Integer.class)};
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);

        command = "-p 23.09";
        schemas = new Schema[]{
                new Schema("p", Integer.class)};
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);
    }
    @Test
    public void parse_map_with_command_line_int_not_0(){
        String command = "-p 23";
        Schema [] schemas = new Schema[]{
                new Schema("p", Integer.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("p",23);
        Assert.assertEquals(testMap,map);

    }


    @Test
    public void parse_map_with_command_line_string_is_not_empty() {
        String command = "-d";
        Schema [] schemas = new Schema[]{
                new Schema("d", String.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("d","");
        Assert.assertEquals(testMap,map);

        command = "-d";
        map = ArgsParse.parse(command,schemas);
        Assert.assertEquals(testMap,map);


    }

    @Test
    public void parse_map_with_command_line_string_is_empty() {
        String command = "-d /usr/logs";
        Schema [] schemas = new Schema[]{
                new Schema("d", String.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("d","/usr/logs");
        Assert.assertEquals(testMap,map);
    }

    @Test
    public void parse_map_with_command_line_string_array_is_empty() {
        String command = "-d";
        Schema [] schemas = new Schema[]{
                new Schema("d", String[].class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        String [] obj = (String[])  map.get("d");
        Assert.assertEquals("",obj[0]);
    }

    @Test
    public void parse_map_with_command_line_string_array_is_no_empty() {
        String command = "-d /usr/log";
        Schema [] schemas = new Schema[]{
                new Schema("d", String[].class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        String [] obj = (String[])  map.get("d");

        Assert.assertEquals("/usr/log",obj[0]);

        command = "-d /usr/log,/var/app";
        map = ArgsParse.parse(command,schemas);
        obj = (String[])  map.get("d");
        Assert.assertEquals("/var/app",obj[1]);

    }

    @Test
    public void parse_map_with_command_line_int_array_is_0() {
        String command = "-d";
        Schema [] schemas = new Schema[]{
                new Schema("d",int [].class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Integer [] obj = (Integer[])  map.get("d");

        Assert.assertTrue(obj[0].intValue() == 0);

        command = "-d 0";
        map = ArgsParse.parse(command,schemas);
       obj = (Integer[])  map.get("d");
        Assert.assertTrue(obj[0].intValue() == 0);

    }

    @Test
    public void parse_map_with_command_line_int_array_is_no_empty() {
        String command = "-d 12,-3,45";
        Schema [] schemas = new Schema[]{
                new Schema("d",int [].class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Integer [] obj = (Integer[])  map.get("d");

        Assert.assertTrue(obj[1].intValue() == -3);
        
    }



    //-------------------------------------------------------
    //-------- 多命令测试 -------------------------------------
    //-------------------------------------------------------

    @Test
    public void parse_map_with_command_line_l_p_booelan(){
        String command = "-l -p 8080";
        Schema [] schemas = new Schema[]{
                new Schema("l", Boolean.class),
                new Schema("p",Integer.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("l",false);
        testMap.put("p",8080);
        Assert.assertEquals(testMap,map);
    }

    @Test
    public void parse_map_with_command_line_l_p_boolean2(){
        String command = "-l-p8080";
        Schema [] schemas = new Schema[]{
                new Schema("l", Boolean.class),
                new Schema("p",Integer.class)};
        Map<String,Object> map = ArgsParse.parse(command,schemas);
        Map<String,Object> testMap = new HashMap<String, Object>();
        testMap.put("l",false);
        testMap.put("p",8080);
        Assert.assertEquals(testMap,map);
    }
}

package com.zn.tdd;

import org.junit.Assert;
import org.junit.Test;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/10/12-16:19:02
 */
public class FizzBuzzTest {
    @Test
    public void should_1_talk_1() {
        FizzBuzz fizzBuzz = new FizzBuzz(1);
        Assert.assertEquals("1",fizzBuzz.talk());
    }
    @Test
    public void should_3_talk_fizz() {
        FizzBuzz fizzBuzz = new FizzBuzz(3);
        Assert.assertEquals("fizz",fizzBuzz.talk());
    }

    @Test
    public void should_5_talk_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz(5);
        Assert.assertEquals("buzz",fizzBuzz.talk());
    }
    @Test
    public void should_15_talk_fizz_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Assert.assertEquals("fizzbuzz",fizzBuzz.talk());
    }
    @Test
    public void should_35_talk_buzz() {
        FizzBuzz fizzBuzz = new FizzBuzz(35);
        Assert.assertEquals("fizzbuzz",fizzBuzz.talk());
    }
    @Test
    public void should_53_talk_fizz_buzz(){
        FizzBuzz fizzBuzz = new FizzBuzz(53);
        Assert.assertEquals("fizzbuzz",fizzBuzz.talk());
    }
}

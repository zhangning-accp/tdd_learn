package com.zn.tdd;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/10/12-16:20:06
 */
public class FizzBuzz {
    private int number;

    public FizzBuzz(int number) {
        this.number = number;
    }

    public String  talk() {
        String message = "";
        if(number % 3 == 0 || number % 5 == 0){

        }
        if (divisibilityTest(3) || containTest(3)) {
            message += "fizz";
        }
        if (divisibilityTest(5) || containTest(5)) {
            message += "buzz";
        }

        if((!containTest(3) || !containTest(5))) {
            message = number + "";
        }

        return message;
    }

    private boolean divisibilityTest(int divisor) {
        return this.number % divisor == 0;
    }

    private boolean containTest(int divisor) {
        String divisorString = number + "";
        return divisorString.contains(divisor + "");
    }

}

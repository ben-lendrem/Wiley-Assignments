package com.testing;

import com.exceptions.CustomException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;


public class TestMain {
    public static void main(String[] args) {
        int x = GenNewAnswer();
        System.out.println(x);
    }


    public static int GenNewAnswer() {
        //construct list of digits 1 - 9;
        Character[] intValCharArr = new Character[] {'1','2','3','4','5','6','7','8','9'};
        List<Character> intValsList = Arrays.asList(intValCharArr);
        ArrayList<Character> intValsAsChars = new ArrayList<>(intValsList);
        String outStr = new String();
        //for each digit, pick a random value from the list, remove that value, and add as character
        for (int i = 0; i < 4; ++i) {
            int range = intValsAsChars.size() - 1;
            /*random number 1 to 100 then remainder becomes random number in range
            Doing it this way because generating random number between 0 and range then casting to int, very low chance
            of getting the max value, this way is more even distribution
             */
            int randInt = (int)(Math.random() * 100);
            int randIndex = randInt % range;
            String currentChar = (intValsAsChars.remove(randIndex)).toString();
            outStr = outStr.concat(currentChar);
        }
        int out = Integer.parseInt(outStr);
        return out;
    }


}

package com.elevatelab.ontimepro.BackgroundServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Services {

    public static boolean mailIdValidation(String emailId)
    {
        return emailId.endsWith("@iiita.ac.in");
    }

    public static String codeGenerator(ArrayList<String> inUseCodes)
    {
        String generatedCode = "";
        List<String> alphaNumeric = new ArrayList<>();
        alphaNumeric = Arrays.asList(
                "1", "2","3","4","5","6","7","8","9","0",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v",
                "w","x","y","z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V",
                "W","X","Y","Z"
        );

        for(int i = 0 ; i < 6 ; i++)
        {
            generatedCode += alphaNumeric.get(i);
        }

        while(isDuplicateCode(inUseCodes,generatedCode))
        {
            generatedCode = "";
            Collections.shuffle(alphaNumeric);
            for(int i = 0 ; i < 6 ; i++)
            {
                generatedCode += alphaNumeric.get(i);
            }
        }

        return generatedCode;
    }

    private static boolean isDuplicateCode(ArrayList<String> inUseCodes,String generatedCode)
    {
        for(String code : inUseCodes)
        {
            if(code.equals(generatedCode))
                return true;
        }
        return false;
    }


}

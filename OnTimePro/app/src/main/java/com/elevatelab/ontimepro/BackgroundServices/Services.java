package com.elevatelab.ontimepro.BackgroundServices;

public class Services {

    public static boolean mailIdValidation(String emailId)
    {
        return emailId.endsWith("@iiita.ac.in");
    }


}

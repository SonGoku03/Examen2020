package com.emontec.examen.ArchJson;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
    public static String leerJson(Context context, String filename) throws IOException
    {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        String content ="";
        String line;
        while((line= reader.readLine())!= null)
        {
            content= context + line;
        }
        return content;
    }
}

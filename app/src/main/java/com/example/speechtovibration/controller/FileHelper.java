package com.example.speechtovibration.controller;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.example.speechtovibration.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileHelper {

    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/speechtovibration/dati/" ;
    final static String TAG = FileHelper.class.getName();

    public static String searchInFiles(String code, Context context){
        final HashMap<Integer, String> filesName = new HashMap<Integer, String>();
        filesName.put(R.raw.codici1,"1");
        filesName.put(R.raw.codici2,"2");
        filesName.put(R.raw.codici3,"3");
        filesName.put(R.raw.codici4,"4");
        filesName.put(R.raw.codici5,"5");

        code = code.replaceAll("\\s","");


        for (HashMap.Entry<Integer, String> idFile : filesName.entrySet())
        {
            Resources res = context.getResources();
            InputStream inputStreamReader = res.openRawResource(idFile.getKey());

            if(ReadFile(code, inputStreamReader, context))
                return idFile.getValue();
        }

        return "CODICE NON IDENTIFICATO";
    }

    private static boolean ReadFile(String code, InputStream inputStreamReader, Context context){
        String line = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader));
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                if(line.equals(code)) return true;
            }
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return false;
    }
}

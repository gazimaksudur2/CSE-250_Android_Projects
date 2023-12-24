package com.example.sticky_notes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class StickyNote {
    private String cur_note;

    String getstick(Context context){
        File file = new File(context.getFilesDir().getPath()+"gfg.txt");
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine())!= null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return cur_note;
    }
    void setStick(String textTobeSaved, Context context){
        String text = textTobeSaved;
        cur_note = text;
        FileOutputStream fos = null;
        try{
            fos = context.getApplicationContext().openFileOutput("gfg.txt",Context.MODE_PRIVATE);
            fos.write(text.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fos != null){
                try{
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
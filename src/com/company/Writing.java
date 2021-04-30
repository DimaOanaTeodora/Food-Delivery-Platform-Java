package com.company;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;

public class Writing {//singleton
    private static Writing single_instance = null;
    private BufferedWriter buffer;
    private Writing() {
        try {
            String path = "./Proiect PAO/src/com/company/Audit.csv";
            //sterge continut dinaintea pornirii programului
            new FileWriter(path, false).close();

            buffer = new BufferedWriter(new FileWriter(path, true));
        }catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static Writing getInstance()
    {
        if (single_instance == null)
            single_instance = new Writing();
        return single_instance;
    }
    public void WriteTimestamp(String text){

        try {
            Timestamp instant= Timestamp.from(Instant.now());
            buffer.write(text+","+instant+"\n");
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

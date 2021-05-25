package com.company.service;

import java.io.*;

import java.sql.Timestamp;
import java.time.Instant;

public class AuditService {//singleton

    private static AuditService single_instance = null;
    private BufferedWriter buffer;

    private AuditService() {
        try {
            String path = "Files/Audit.csv";

            //sterge continut dinaintea pornirii programului
            new FileWriter(path, false).close();

            buffer = new BufferedWriter(new FileWriter(path, true));
        }catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static synchronized AuditService getInstance() {
        if (single_instance == null)
            single_instance = new AuditService();
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

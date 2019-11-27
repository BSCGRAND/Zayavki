package ru.bscgrand.Zayavka.services.ExcelReadScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.services.exelHandling.CopyFromExel;
import ru.bscgrand.Zayavka.services.exelHandling.ReadExel;
import ru.bscgrand.Zayavka.services.exelHandling.UpdateDateOfReceiving;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ScheduledTasks {
    private final String path = "E:\\TestZayavki";


    @Scheduled(fixedRate = 50000)
    public void readExcel() {

        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File excelFile : dir.listFiles()) {
                try {
                    String dateString = "10 11 2019";
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
                    LocalDate dateFormatted = LocalDate.parse(dateString,dtf);
//                    System.out.println("date from Scheduled tasts: " + dateFormatted);
                    List<GoodsRequest> newGoodsRequests;
                    try {
                        newGoodsRequests = ReadExel.read(excelFile);
                        CopyFromExel cp = new CopyFromExel();
//                        System.out.println(newGoodsRequests);
                        cp.copyNew(dateFormatted, newGoodsRequests);
                        //UpdateDateOfReceiving updateDateOfReceiving = new UpdateDateOfReceiving();
                        //updateDateOfReceiving.update(dateFormatted, newGoodsRequests);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

//                    for (GoodsRequest gr : newGoodsRequests) {
//                        System.out.println(gr.toString());
//                    }

                } catch (NullPointerException npe) {
                    npe.printStackTrace();


                }
            }
        }
    }
}

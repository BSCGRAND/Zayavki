package ru.bscgrand.Zayavka.services.ExcelReadScheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.services.exelHandling.CopyFromExcel;
import ru.bscgrand.Zayavka.services.exelHandling.ReadExcel;
import ru.bscgrand.Zayavka.services.exelHandling.UpdateDateOfReceiving;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
//@PropertySource("classpath:app.properties")
public class ScheduledTasks {
//    @Autowired
//    Environment env;

    //---------------------DI----------------------------------//
    private final CopyFromExcel copyFromExcel;
    private final UpdateDateOfReceiving updateDateOfReceiving;
    private final ReadExcel readExcel;
    @Autowired
    public ScheduledTasks(CopyFromExcel copyFromExcel, UpdateDateOfReceiving updateDateOfReceiving, ReadExcel readExcel){
        this.copyFromExcel = copyFromExcel;
        this.updateDateOfReceiving = updateDateOfReceiving;
        this.readExcel = readExcel;
    }
    //--------------------------------------------------------//

    @Scheduled(fixedRate = 50000)
    public void readExcel() {
        String path = "E:\\TestZayavki";
        String dateString = "11 11 2019";

        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File excelFile : dir.listFiles()) {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
                    LocalDate dateFormatted = LocalDate.parse(dateString,dtf);
                    List<GoodsRequest> newGoodsRequests;
                    newGoodsRequests = readExcel.read(excelFile);
                    copyFromExcel.copyNew(dateFormatted, newGoodsRequests);
                    updateDateOfReceiving.update(dateFormatted, newGoodsRequests);
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        }
    }
}

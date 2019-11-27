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
import java.util.*;

@Component
public class ScheduledTasks {
    private final String path = "E:\\TestZayavki";
    private final String dateString = "18 11 2019";
    @Scheduled(fixedRate = 50000)
    public void readExcel(){

        File dir = new File(path);
        if(dir.isDirectory()){
            for (File excelFile: dir.listFiles()){

                String dateString = "10 11 2019";
                SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
                Date date = null;
                try {
                    date = df.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                List<GoodsRequest> newGoodsRequests = new ArrayList<GoodsRequest>();
                try{
                    newGoodsRequests = ReadExel.read(excelFile);
                    CopyFromExel cp = new CopyFromExel();
                    cp.copyNew(calendar,newGoodsRequests);
                    UpdateDateOfReceiving updateDateOfReceiving = new UpdateDateOfReceiving();
                    updateDateOfReceiving.update(calendar,newGoodsRequests);
                } catch (IOException ex){
                    ex.printStackTrace();
                }

                for(GoodsRequest gr: newGoodsRequests){
                    System.out.println(gr.toString());
                }

            }
        }
    }

}

package ru.bscgrand.Zayavka.services.exelHandling;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ReadExel {
    public static List<GoodsRequest> read(File file) throws IOException {

        String dateString = "01 01 2000";
        SimpleDateFormat ndf = new SimpleDateFormat("dd MM yyyy");
        Date nullDate = null;
        try {
            nullDate = ndf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar nullCalendar = Calendar.getInstance();
        nullCalendar.setTime(nullDate);

        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sh = wb.getSheetAt(0);
        List<XSSFRow> goodsRequestsRows = new ArrayList<>();
        int i=1;
        while (true) {
            XSSFRow row = sh.getRow(i);
            Date date = row.getCell(0).getDateCellValue();
            if (date == null) break;
            goodsRequestsRows.add(row);
            i++;
        }
        List<GoodsRequest> goodsRequests = new ArrayList<>();
        for (XSSFRow row : goodsRequestsRows) {
            GoodsRequest currentGoodsRequest = new GoodsRequest();
            Date date = row.getCell(0).getDateCellValue();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            currentGoodsRequest.setDateOfPurchaseRequest(calendar);
            currentGoodsRequest.setSubdivision(row.getCell(1).getStringCellValue());
            currentGoodsRequest.setFullName(row.getCell(2).getStringCellValue());
            currentGoodsRequest.setOilfieldName(row.getCell(3).getStringCellValue());
            currentGoodsRequest.setGoodsName(row.getCell(4).getStringCellValue());
            currentGoodsRequest.setAmount(row.getCell(5).getNumericCellValue());
            currentGoodsRequest.setUnit(row.getCell(6).getStringCellValue());
            try{
                date = row.getCell(7).getDateCellValue();
                calendar.setTime(date);
                currentGoodsRequest.setDateOfReceiving(calendar);
            } catch (NullPointerException npe){
                currentGoodsRequest.setDateOfReceiving(nullCalendar);
            }
            currentGoodsRequest.setNote(row.getCell(8).getStringCellValue());
            currentGoodsRequest.setResponsibleUnit("");
            currentGoodsRequest.setDateOfGeneralRequest(nullCalendar);
            currentGoodsRequest.setSupply(false);
            currentGoodsRequest.setSent(false);
            currentGoodsRequest.setProgressMark(false);
            currentGoodsRequest.setComments("");
            goodsRequests.add(currentGoodsRequest);
        }
        return goodsRequests;
        }
}

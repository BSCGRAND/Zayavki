package ru.bscgrand.Zayavka.services.exelHandling;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadExel {
    public static List<GoodsRequest> read(File file) throws IOException {
        final LocalDate nullDate = LocalDate.of(2000,01,01);
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sh = wb.getSheetAt(0);
        List<XSSFRow> goodsRequestsRows = new ArrayList<>();
        int i=1;
        while (true) {
            XSSFRow row = sh.getRow(i);
            String tmp = row.getCell(1).getStringCellValue();
            if (tmp.equals("")) break;
            goodsRequestsRows.add(row);
            i++;
        }
        List<GoodsRequest> goodsRequests = new ArrayList<>();
        for (XSSFRow row : goodsRequestsRows) {
            GoodsRequest currentGoodsRequest = new GoodsRequest();
//            Date date = row.getCell(0).getDateCellValue();
            LocalDate date = row.getCell(0).getLocalDateTimeCellValue().toLocalDate();
//            System.out.println("date from readexcel " + date);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
            currentGoodsRequest.setDateOfPurchaseRequest(date);
            currentGoodsRequest.setSubdivision(row.getCell(1).getStringCellValue());
            currentGoodsRequest.setFullName(row.getCell(2).getStringCellValue());
            currentGoodsRequest.setOilfieldName(row.getCell(3).getStringCellValue());
            currentGoodsRequest.setGoodsName(row.getCell(4).getStringCellValue());
            currentGoodsRequest.setAmount(row.getCell(5).getNumericCellValue());
            currentGoodsRequest.setUnit(row.getCell(6).getStringCellValue());
            try{
//                date = row.getCell(7).getDateCellValue();
//                calendar.setTime(date);
                currentGoodsRequest.setDateOfReceiving(date);
            } catch (NullPointerException npe){
                currentGoodsRequest.setDateOfReceiving(nullDate);
            }
            currentGoodsRequest.setNote(row.getCell(8).getStringCellValue());
            currentGoodsRequest.setResponsibleUnit("");
            currentGoodsRequest.setDateOfGeneralRequest(nullDate);
            currentGoodsRequest.setSupply(false);
            currentGoodsRequest.setSent(false);
            currentGoodsRequest.setProgressMark(false);
            currentGoodsRequest.setComments("");
//            System.out.println(currentGoodsRequest.toString());
            goodsRequests.add(currentGoodsRequest);
        }

        return goodsRequests;
        }
}

package ru.bscgrand.Zayavka.services.exelHandling;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.bscgrand.Zayavka.Models.GoodsRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CopyFromExel {

    private static List<XSSFRow> readFromExcel(Date previousDate) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\Java\\Заявка Бурение 1.xlsx"));
        XSSFSheet sh = wb.getSheetAt(0);
        List<XSSFRow> newGoodsRequests = new ArrayList<>();
        int i=1;
        while (true) {
            XSSFRow row = sh.getRow(i);
            Date date = row.getCell(0).getDateCellValue();
            if (date == null) break;
            if (date.getTime() > previousDate.getTime()) {
                newGoodsRequests.add(row);
            }
            i++;
        }
        return newGoodsRequests;
    }

    public List<GoodsRequest> addNewGoodsRequests(Date previousDate) {
        List<GoodsRequest> addNewGoodsRequests = new ArrayList<>();
        try {
            List<XSSFRow> newGoodsRequests = readFromExcel(previousDate);
            for (XSSFRow goodsRequest : newGoodsRequests) {
                GoodsRequest currentGoodsRequest = new GoodsRequest();
                currentGoodsRequest.setDateOfPurchaseRequest(goodsRequest.getCell(0).getDateCellValue());
                String info = goodsRequest.getCell(1).getStringCellValue() +
                        goodsRequest.getCell(2).getStringCellValue() +
                        goodsRequest.getCell(3).getStringCellValue();
                currentGoodsRequest.setInfo(info);
                currentGoodsRequest.setGoodsName(goodsRequest.getCell(4).getStringCellValue());
                currentGoodsRequest.setAmount(goodsRequest.getCell(5).getNumericCellValue());
                currentGoodsRequest.setUnit(goodsRequest.getCell(6).getStringCellValue());
                currentGoodsRequest.setDateOfReceiving(goodsRequest.getCell(7).getDateCellValue());
                currentGoodsRequest.setNote(goodsRequest.getCell(8).getStringCellValue());
                currentGoodsRequest.setResponsibleUnit("");
                currentGoodsRequest.setDateOfGeneralRequest(null);
                currentGoodsRequest.setSupply(false);
                currentGoodsRequest.setSent(false);
                currentGoodsRequest.setProgressMark(false);
                currentGoodsRequest.setComments("");
                addNewGoodsRequests.add(currentGoodsRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addNewGoodsRequests;
    }
}

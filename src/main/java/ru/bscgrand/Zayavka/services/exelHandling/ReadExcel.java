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
public class ReadExcel {

    public List<GoodsRequest> read(File file) {
//        final LocalDate nullDate = LocalDate.of(2000,1,1);
        List<GoodsRequest> goodsRequests = new ArrayList<>();

        //try-with-resources
        try(XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file))){
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
            for (XSSFRow row : goodsRequestsRows) {
                LocalDate date = row.getCell(0).getLocalDateTimeCellValue().toLocalDate();
                GoodsRequest currentGoodsRequest = new GoodsRequest(date,row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue(),
                        row.getCell(4).getStringCellValue(),row.getCell(5).getNumericCellValue(),
                        row.getCell(6).getStringCellValue(),null,row.getCell(8).getStringCellValue(),
                        "",null,false,false,false,"");
                goodsRequests.add(currentGoodsRequest);

//                currentGoodsRequest.setDateOfPurchaseRequest(date);
//                currentGoodsRequest.setSubdivision(row.getCell(1).getStringCellValue());
//                currentGoodsRequest.setFullName(row.getCell(2).getStringCellValue());
//                currentGoodsRequest.setOilfieldName(row.getCell(3).getStringCellValue());
//                currentGoodsRequest.setGoodsName(row.getCell(4).getStringCellValue());
//                currentGoodsRequest.setAmount(row.getCell(5).getNumericCellValue());
//                currentGoodsRequest.setUnit(row.getCell(6).getStringCellValue());
//                currentGoodsRequest.setDateOfReceiving(date);
//                currentGoodsRequest.setNote(row.getCell(8).getStringCellValue());
//                currentGoodsRequest.setResponsibleUnit("");
//                currentGoodsRequest.setDateOfGeneralRequest(nullDate);
//                currentGoodsRequest.setSupply(false);
//                currentGoodsRequest.setSent(false);
//                currentGoodsRequest.setProgressMark(false);
//                currentGoodsRequest.setComments("");
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return goodsRequests;
        }
}

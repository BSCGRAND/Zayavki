package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;
import ru.bscgrand.Zayavka.Models.Specification.GoodsRequestSpecifications;
import ru.bscgrand.Zayavka.Models.Specification.SearchCriteria;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/goods")
public class RequestController {


    private final GoodsRequestRepository goodsRequestRepository;
    @Autowired
    public RequestController(GoodsRequestRepository goodsRequestRepository){
        this.goodsRequestRepository = goodsRequestRepository;
    }

    @GetMapping("/all")
    public List<GoodsRequest> getAllGoodsRequest(){
        return goodsRequestRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<GoodsRequest> getGoodsRequestById(@PathVariable Long id){
        return goodsRequestRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteGoodsRequestById(@PathVariable Long id){
        goodsRequestRepository.deleteById(id);
        return "Delete SUCCESS";
    }
    //Добавить один товар
    @PutMapping("/add")
    public String addGoodsRequest(@RequestBody GoodsRequest goodsRequest){
        GoodsRequest newGoodsRequest = new GoodsRequest();
        newGoodsRequest.setAmount(goodsRequest.getAmount());
        newGoodsRequest.setDateOfPurchaseRequest(goodsRequest.getDateOfPurchaseRequest());
        newGoodsRequest.setSubdivision(goodsRequest.getSubdivision());
        newGoodsRequest.setFullName(goodsRequest.getFullName());
        newGoodsRequest.setOilfieldName(goodsRequest.getOilfieldName());
        newGoodsRequest.setNote(goodsRequest.getNote());
        newGoodsRequest.setGoodsName(goodsRequest.getGoodsName());
        newGoodsRequest.setUnit(goodsRequest.getUnit());
        newGoodsRequest.setDateOfReceiving(goodsRequest.getDateOfReceiving());
        goodsRequestRepository.saveAndFlush(newGoodsRequest);
        return "SAVE SUCCESS";
    }

    @GetMapping("api/find")
    public List<GoodsRequest> getAllTest(@RequestParam Map <String, String> allParams)
     {
        String subdivision = allParams.get("subdivision");
        String dateOfRequestFromStr = allParams.get("dateOfRequestFrom");
        String dateOfRequestToStr = allParams.get("dateOfRequestTo");
        String dateOfReceivingFromStr = allParams.get("dateOfReceivingFrom");
        String dateOfReceivingToStr = allParams.get("dateOfReceivingTo");
        String dateOfGeneralRequestFromStr = allParams.get("dateOfGeneralRequestFrom");
        String dateOfGeneralRequestToStr = allParams.get("dateOfGeneralRequestTo");
        String supplyStr = allParams.get("supply");
        String sentStr = allParams.get("sent");
        String progressMarkStr = allParams.get("progressMark");
        if (subdivision == null) subdivision = "Бригада бурения 1";
        LocalDate dateOfRequestFrom,dateOfRequestTo,dateOfReceivingFrom,dateOfReceivingTo,dateOfGeneralRequestFrom,dateOfGeneralRequestTo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            dateOfRequestFrom = LocalDate.parse(dateOfRequestFromStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfRequestFrom = LocalDate.MIN;
        }
        try{
            dateOfRequestTo = LocalDate.parse(dateOfRequestToStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfRequestTo = LocalDate.MAX;
        }
        try{
            dateOfReceivingFrom = LocalDate.parse(dateOfReceivingFromStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfReceivingFrom = LocalDate.MIN;
        }
        try{
            dateOfReceivingTo = LocalDate.parse(dateOfReceivingToStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfReceivingTo = LocalDate.MAX;
        }
        try{
            dateOfGeneralRequestFrom = LocalDate.parse(dateOfGeneralRequestFromStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfGeneralRequestFrom = LocalDate.MIN;
        }
        try{
            dateOfGeneralRequestTo = LocalDate.parse(dateOfGeneralRequestToStr, formatter);
        } catch (DateTimeParseException ew){
            dateOfGeneralRequestTo = LocalDate.MAX;
        }
        boolean supply = false;
        if (supplyStr.equals("true")) supply = true;
        else if (supplyStr.equals("any")) {}
        boolean sent = false;
        if (sentStr.equals("true")) sent = true;
        else if (supplyStr.equals("any")) {}
        boolean progressMark = false;
        if (progressMarkStr.equals("true")) progressMark = true;
        else if (supplyStr.equals("any")) {}

//        return goodsRequestRepository.getAllByDateOfPurchaseRequestAfterAndDateOfPurchaseRequestBefore(dateOfRequestFrom, dateOfRequestTo);

//        return goodsRequestRepository.getAllBySubdivisionAndDateOfPurchaseRequestAfterAndDateOfPurchaseRequestBeforeAndDateOfReceivingAfterAndDateOfReceivingBeforeAndDateOfGeneralRequestAfterAndDateOfGeneralRequestBeforeAndSupplyAndSentAndProgressMark(
//                subdivision, dateOfRequestFrom, dateOfRequestTo, dateOfReceivingFrom, dateOfReceivingTo,
//                dateOfGeneralRequestFrom, dateOfGeneralRequestTo, supply, sent, progressMark);

        GoodsRequestSpecifications spec1 = new GoodsRequestSpecifications(new SearchCriteria("subdivision", ":", subdivision));
        GoodsRequestSpecifications spec2 = new GoodsRequestSpecifications(new SearchCriteria("dateOfPurchaseRequest", ">", dateOfRequestFrom));
        List<GoodsRequest> result = goodsRequestRepository.findAll(spec1);
        return result;
    }

    // Добавить заявку из экселя
//    @PutMapping("/add")
//    public String putGoodsRequest()

//    @PutMapping("/scan")
//    public String scanForUpdate() {
////        String dateString = "18 11 2019";
////        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
////        Date date = null;
////        try {
////            date = df.parse(dateString);
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(date);
////        путь к файлу (потом  будет в RequestBody)
//        File file = new File("");
//        List<GoodsRequest> allInFile = new ArrayList<>();
//        try {
//            allInFile = new ReadExel().read(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        new CopyFromExcel().copyNew(calendar, allInFile);
//        new UpdateDateOfReceiving().update(calendar, allInFile);
//        return "REQUESTS UPDATE SUCCESS";
//    }
}

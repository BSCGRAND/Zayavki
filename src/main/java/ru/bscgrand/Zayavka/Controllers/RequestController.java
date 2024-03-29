package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.time.format.DateTimeParseException;
import java.util.*;

import static ru.bscgrand.Zayavka.Models.Specification.GoodsRequestSpecifications.*;
import static ru.bscgrand.Zayavka.Models.Specification.GoodsRequestSpecifications.dateOfPurchaseTo;

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
        Specification<GoodsRequest> byParamsSpec = null;
        if (!subdivision.equals("")) byParamsSpec = subdivisionIs(subdivision);
        LocalDate dateOfRequestFrom,dateOfRequestTo,dateOfReceivingFrom,dateOfReceivingTo,dateOfGeneralRequestFrom,dateOfGeneralRequestTo;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            dateOfRequestFrom = LocalDate.parse(dateOfRequestFromStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfPurchaseFrom(dateOfRequestFrom))  : dateOfPurchaseFrom(dateOfRequestFrom);
        } catch (DateTimeParseException ignored){}
        try{
            dateOfRequestTo = LocalDate.parse(dateOfRequestToStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfPurchaseTo(dateOfRequestTo))  : dateOfPurchaseTo(dateOfRequestTo);
        } catch (DateTimeParseException ignored){}
        try{
            dateOfReceivingFrom = LocalDate.parse(dateOfReceivingFromStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfReceivingFrom(dateOfReceivingFrom))  : dateOfReceivingFrom(dateOfReceivingFrom);
        } catch (DateTimeParseException ignored){}
        try{
            dateOfReceivingTo = LocalDate.parse(dateOfReceivingToStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfReceivingTo(dateOfReceivingTo))  : dateOfReceivingTo(dateOfReceivingTo);
        } catch (DateTimeParseException ignored){}
        try{
            dateOfGeneralRequestFrom = LocalDate.parse(dateOfGeneralRequestFromStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfGeneralRequestFrom(dateOfGeneralRequestFrom))  : dateOfGeneralRequestFrom(dateOfGeneralRequestFrom);
        } catch (DateTimeParseException ignored){}
        try{
            dateOfGeneralRequestTo = LocalDate.parse(dateOfGeneralRequestToStr, formatter);
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(dateOfGeneralRequestTo(dateOfGeneralRequestTo))  : dateOfGeneralRequestTo(dateOfGeneralRequestTo);
        } catch (DateTimeParseException ignored){}

        if (!supplyStr.equals("any")) {
         boolean supply = false;
         if (supplyStr.equals("true")) supply = true;
         byParamsSpec = byParamsSpec != null ? byParamsSpec.and(supplyIs(supply))  : supplyIs(supply);
        }

        if (!sentStr.equals("any")) {
            boolean sent = false;
            if (sentStr.equals("true")) sent = true;
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(sentIs(sent))  : sentIs(sent);
        }

        if (!supplyStr.equals("any")) {
            boolean progressMark = false;
            if (progressMarkStr.equals("true")) progressMark = true;
            byParamsSpec = byParamsSpec != null ? byParamsSpec.and(progressMarkIs(progressMark))  : progressMarkIs(progressMark);
        }

        return goodsRequestRepository.findAll(byParamsSpec);
    }

    @PostMapping("/api/update")
    public ResponseEntity updateGoods(@RequestBody GoodsRequest updateGoodsRequest){
        if(goodsRequestRepository.findById(updateGoodsRequest.getId()).isPresent()){
            goodsRequestRepository.save(updateGoodsRequest);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
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

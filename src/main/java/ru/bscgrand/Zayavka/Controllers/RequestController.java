package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;

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

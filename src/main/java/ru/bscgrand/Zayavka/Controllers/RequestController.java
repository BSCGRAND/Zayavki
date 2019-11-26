package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequestRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
public class RequestController {

    @Autowired
    GoodsRequestRepository goodsRequestRepository;

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
        newGoodsRequest.setInfo(goodsRequest.getInfo());
        newGoodsRequest.setNote(goodsRequest.getNote());
        newGoodsRequest.setGoodsName(goodsRequest.getGoodsName());
        newGoodsRequest.setUnit(goodsRequest.getUnit());
        newGoodsRequest.setDateOfReceiving(goodsRequest.getDateOfReceiving());
        goodsRequestRepository.save(newGoodsRequest);
        return "SAVE SUCCESS";
    }
    // Добавить заявку из экселя
//    @PutMapping("/add")
//    public String putGoodsRequest()
}

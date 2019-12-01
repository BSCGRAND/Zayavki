package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/goods")
public class GoodsRequestController {
    private final GoodsRequestRepository goodsRequestRepository;
    @Autowired
    public GoodsRequestController(GoodsRequestRepository goodsRequestRepository){
        this.goodsRequestRepository = goodsRequestRepository;
    }

    @GetMapping("/{id}")
    public Optional<GoodsRequest> getGoodRequestById(Long id){
        return goodsRequestRepository.findById(id);
    }

    @GetMapping("/all")
    public List<GoodsRequest> getAllGoodRequest(){
        return goodsRequestRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteGoodRequestById(@PathVariable Long id){
        goodsRequestRepository.deleteById(id);
        return "Delete SUCCESS";
    }
//
//    @GetMapping("/filter")
//    public List<GoodsRequest> getFilteredGoodRequest(@RequestParam Map<String, String> requestParams){
//        List<GoodsRequest> newRequest;
//
//        return newRequest;
//    }
}

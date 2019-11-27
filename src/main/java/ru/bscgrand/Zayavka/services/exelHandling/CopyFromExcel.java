package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequestRepository;

import java.time.LocalDate;
import java.util.*;
@Component
public class CopyFromExcel {

    //Inject repository constructor-base
    private final GoodsRequestRepository goodsRequestRepository;
    @Autowired
    public CopyFromExcel(GoodsRequestRepository goodsRequestRepository){
        this.goodsRequestRepository = goodsRequestRepository;
    }

    public void copyNew(LocalDate date, List<GoodsRequest> goodsRequests) {
//        System.out.println(goodsRequests);
        for (GoodsRequest goodsRequest : goodsRequests) {
            if (date.isBefore(goodsRequest.getDateOfPurchaseRequest())) {

//                System.out.println("date from copyfromexcel:  " + date);
//                System.out.println(goodsRequest.toString());
//                try {
                System.out.println(goodsRequest.toString());
                    goodsRequestRepository.save(goodsRequest);
//                } catch (NullPointerException npe){
//                    System.out.println(npe.getMessage());
  //              }

                //System.out.println(goodsRequest.toString());
            }
        }
    }

}

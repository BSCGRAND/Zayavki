package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequestRepository;

import java.time.LocalDate;
import java.util.*;

public class CopyFromExel {
    @Autowired
    GoodsRequestRepository goodsRequestRepository;

    public void copyNew(LocalDate date, List<GoodsRequest> goodsRequests) {
//        System.out.println(goodsRequests);
        for (GoodsRequest goodsRequest : goodsRequests) {
            if (date.isBefore(goodsRequest.getDateOfPurchaseRequest())) {

//                System.out.println("date from copyfromexel:  " + date);
//                System.out.println(goodsRequest.toString());
//                try {
                    goodsRequestRepository.save(goodsRequest);
//                } catch (NullPointerException npe){
//                    System.out.println(npe.getMessage());
  //              }

                //System.out.println(goodsRequest.toString());
            }
        }
    }

}

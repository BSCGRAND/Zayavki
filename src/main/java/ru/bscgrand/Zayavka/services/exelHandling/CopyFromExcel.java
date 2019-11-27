package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;

import java.time.LocalDate;
import java.util.*;

@Component
public class CopyFromExcel {

    //---------------------------DI---------------------------------------
    private final GoodsRequestRepository goodsRequestRepository;
    @Autowired
    public CopyFromExcel(GoodsRequestRepository goodsRequestRepository){
        this.goodsRequestRepository = goodsRequestRepository;
    }
    //--------------------------------------------------------------------
    public void copyNew(LocalDate date, List<GoodsRequest> goodsRequests) {

        for (GoodsRequest goodsRequest : goodsRequests) {
            if (date.isBefore(goodsRequest.getDateOfPurchaseRequest())) goodsRequestRepository.saveAndFlush(goodsRequest);
        }
    }

}

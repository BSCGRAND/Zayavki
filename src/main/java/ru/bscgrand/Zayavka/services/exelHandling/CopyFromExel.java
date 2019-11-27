package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequestRepository;

import java.util.*;

public class CopyFromExel {
    @Autowired
    GoodsRequestRepository goodsRequestRepository;

    public void copyNew(Calendar calendar, List<GoodsRequest> goodsRequests) {
        for (GoodsRequest goodsRequest : goodsRequests) {
            if (calendar.before(goodsRequest.getDateOfPurchaseRequest())) {
                goodsRequestRepository.save(goodsRequest);
            }
        }
    }

}

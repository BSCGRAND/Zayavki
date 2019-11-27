package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.GoodsRequestRepository;


import java.time.LocalDate;
import java.util.List;

public class UpdateDateOfReceiving {
    @Autowired
    GoodsRequestRepository goodsRequestRepository;

    public void update(LocalDate date, List<GoodsRequest> goodsRequests) {

        for (GoodsRequest goodsRequest : goodsRequests) {
            if (!date.isBefore(goodsRequest.getDateOfPurchaseRequest())
                    && goodsRequest.getDateOfReceiving() != null) {
                GoodsRequest requestFromDB =
                        goodsRequestRepository.getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
                                goodsRequest.getDateOfPurchaseRequest(),
                                goodsRequest.getSubdivision(),
                                goodsRequest.getGoodsName()
                        );
                if (!requestFromDB.isSupply()) {
                    requestFromDB.setSupply(true);
                    requestFromDB.setDateOfReceiving(goodsRequest.getDateOfReceiving());
                }
                goodsRequestRepository.deleteById(requestFromDB.getId());
                goodsRequestRepository.saveAndFlush(requestFromDB);
            }
        }
    }
}

package ru.bscgrand.Zayavka.services.exelHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bscgrand.Zayavka.Models.GoodsRequest;
import ru.bscgrand.Zayavka.Models.Repositories.GoodsRequestRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class UpdateDateOfReceiving {
    //-----------------------------------DI--------------------------------------
    private final GoodsRequestRepository goodsRequestRepository;
    @Autowired
    public UpdateDateOfReceiving(GoodsRequestRepository goodsRequestRepository){
        this.goodsRequestRepository = goodsRequestRepository;
    }
    //----------------------------------------------------------------------------
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

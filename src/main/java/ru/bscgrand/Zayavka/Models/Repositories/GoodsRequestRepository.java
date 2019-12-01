package ru.bscgrand.Zayavka.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.bscgrand.Zayavka.Models.GoodsRequest;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoodsRequestRepository extends JpaRepository<GoodsRequest,Long>, JpaSpecificationExecutor<GoodsRequest> {
    GoodsRequest getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
            LocalDate date,
            String subDivision,
            String goodsName);

    List<GoodsRequest> getAllBySubdivision(String subdivision);

    default LocalDate getMaxDateBySubdivision(String subdivision) {
        List<GoodsRequest> goodsRequests = getAllBySubdivision(subdivision);
        LocalDate max = LocalDate.MIN;
        for (GoodsRequest goodsRequest : goodsRequests) {
            max = goodsRequest.getDateOfPurchaseRequest().isAfter(max) ? goodsRequest.getDateOfPurchaseRequest() : max;
        }
        return max;
    }

    List<GoodsRequest> getAllBySubdivisionAndDateOfPurchaseRequestAfterAndDateOfPurchaseRequestBeforeAndDateOfReceivingAfterAndDateOfReceivingBeforeAndDateOfGeneralRequestAfterAndDateOfGeneralRequestBeforeAndSupplyAndSentAndProgressMark(
            String subdivision,
            LocalDate dateOfPurchaseRequestFrom, LocalDate dateOfPurchaseRequestTo,
            LocalDate dateOfReceivingFrom, LocalDate dateOfReceivingTo,
            LocalDate dateOfGeneralRequestFrom, LocalDate dateOfGeneralRequestTo,
            boolean supply,boolean sent,boolean progressMark
    );

}
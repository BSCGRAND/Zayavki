package ru.bscgrand.Zayavka.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface GoodsRequestRepository extends JpaRepository<GoodsRequest,Long> {
    GoodsRequest getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
            Calendar date,
            String subDivision,
            String goodsName);
}

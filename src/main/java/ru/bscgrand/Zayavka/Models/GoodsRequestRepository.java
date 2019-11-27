package ru.bscgrand.Zayavka.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface GoodsRequestRepository extends JpaRepository<GoodsRequest,Long> {
    GoodsRequest getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
            LocalDate date,
            String subDivision,
            String goodsName);
}

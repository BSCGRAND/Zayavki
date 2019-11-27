package ru.bscgrand.Zayavka.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GoodsRequestRepository extends JpaRepository<GoodsRequest,Long> {
    GoodsRequest getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
            LocalDate date,
            String subDivision,
            String goodsName);
}

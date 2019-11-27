package ru.bscgrand.Zayavka.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bscgrand.Zayavka.Models.GoodsRequest;

import java.time.LocalDate;

@Repository
public interface GoodsRequestRepository extends JpaRepository<GoodsRequest,Long> {
    GoodsRequest getByDateOfPurchaseRequestAndSubdivisionAndGoodsName(
            LocalDate date,
            String subDivision,
            String goodsName);
}

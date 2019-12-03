package ru.bscgrand.Zayavka.Models;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;


@StaticMetamodel(GoodsRequest.class)
public class GoodsRequestMeta {
    public static volatile SingularAttribute<GoodsRequest, Long> id;
    public static volatile SingularAttribute<GoodsRequest, LocalDate>  dateOfPurchaseRequest;
    public static volatile SingularAttribute<GoodsRequest, String>  subdivision;
    public static volatile SingularAttribute<GoodsRequest, String>  fullName;
    public static volatile SingularAttribute<GoodsRequest, String>  oilfieldName;
    public static volatile SingularAttribute<GoodsRequest, String>  goodsName;
    public static volatile SingularAttribute<GoodsRequest, Double>  amount;
    public static volatile SingularAttribute<GoodsRequest, String>  unit;
    public static volatile SingularAttribute<GoodsRequest, LocalDate>  dateOfReceiving;
    public static volatile SingularAttribute<GoodsRequest, String>  note;
    public static volatile SingularAttribute<GoodsRequest, String>  responsibleUnit;
    public static volatile SingularAttribute<GoodsRequest, LocalDate>  dateOfGeneralRequest;
    public static volatile SingularAttribute<GoodsRequest, Boolean>  supply;
    public static volatile SingularAttribute<GoodsRequest, Boolean>  sent;
    public static volatile SingularAttribute<GoodsRequest, Boolean>  progressMark;
    public static volatile SingularAttribute<GoodsRequest, String>  comments;

}

package ru.bscgrand.Zayavka.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goodsRequest")
public class GoodsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    Дата подачи заявка
    private Date dateOfPurchaseRequest;

//    Подразделение подающее заявку + ФИО + Объект
    private String info;

//    Наименование
    private String goodsName;

//    Количество
    private double amount;

//    Ед. Измерения
    private String unit;

//    Дата получения на объекте
    private Date dateOfReceiving;

//    Примечание
    private String note;

//    Ответсвенное подразделение
    private String responsibleUnit;

//    Дата формирования общей заявки на закуп
    private Date dateOfGeneralRequest;

//    Поставка
    private boolean supply;

//    Отправлено
    private boolean sent;

//    Отметка о выполнении
    private boolean progressMark;

//    Комментарии
    private String comments;

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public void setDateOfGeneralRequest(Date dateOfGeneralRequest) {
        this.dateOfGeneralRequest = dateOfGeneralRequest;
    }

    public void setSupply(boolean supply) {
        this.supply = supply;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setProgressMark(boolean progressMark) {
        this.progressMark = progressMark;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDateOfPurchaseRequest(Date dateOfPurchaseRequest) {
        this.dateOfPurchaseRequest = dateOfPurchaseRequest;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDateOfReceiving(Date dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public Date getDateOfGeneralRequest() {
        return dateOfGeneralRequest;
    }

    public boolean isSupply() {
        return supply;
    }

    public boolean isSent() {
        return sent;
    }

    public boolean isProgressMark() {
        return progressMark;
    }

    public String getComments() {
        return comments;
    }

    public Date getDateOfPurchaseRequest() {
        return dateOfPurchaseRequest;
    }

    public String getInfo() {
        return info;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public Date getDateOfReceiving() {
        return dateOfReceiving;
    }

    public String getNote() {
        return note;
    }

    public GoodsRequest(Date dateOfPurchaseRequest, String info, String goodsName, int amount, String unit, Date dateOfReceiving, String note) {
        this.dateOfPurchaseRequest = dateOfPurchaseRequest;
        this.info = info;
        this.goodsName = goodsName;
        this.amount = amount;
        this.unit = unit;
        this.dateOfReceiving = dateOfReceiving;
        this.note = note;
    }

    public GoodsRequest() {
    }
}

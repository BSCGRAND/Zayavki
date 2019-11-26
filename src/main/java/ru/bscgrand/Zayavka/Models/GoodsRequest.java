package ru.bscgrand.Zayavka.Models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "goodsRequest")
public class GoodsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    Дата подачи заявка
    private Calendar dateOfPurchaseRequest;

//    Подразделение подающее заявку + ФИО + Объект
    private String info;

//    Наименование
    private String goodsName;

//    Количество
    private double amount;

//    Ед. Измерения
    private String unit;

//    Дата получения на объекте
    private Calendar dateOfReceiving;

//    Примечание
    private String note;

//    Ответсвенное подразделение
    private String responsibleUnit;

//    Дата формирования общей заявки на закуп
    private Calendar dateOfGeneralRequest;

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

    public void setDateOfGeneralRequest(Calendar dateOfGeneralRequest) {
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

    public void setDateOfPurchaseRequest(Calendar dateOfPurchaseRequest) {
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

    public void setDateOfReceiving(Calendar dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public Calendar getDateOfGeneralRequest() {
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

    public Calendar getDateOfPurchaseRequest() {
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

    public Calendar getDateOfReceiving() {
        return dateOfReceiving;
    }

    public String getNote() {
        return note;
    }

    public GoodsRequest(Calendar dateOfPurchaseRequest, String info, String goodsName, double amount, String unit, Calendar dateOfReceiving, String note, String responsibleUnit, Calendar dateOfGeneralRequest, boolean supply, boolean sent, boolean progressMark, String comments) {
        this.dateOfPurchaseRequest = dateOfPurchaseRequest;
        this.info = info;
        this.goodsName = goodsName;
        this.amount = amount;
        this.unit = unit;
        this.dateOfReceiving = dateOfReceiving;
        this.note = note;
        this.responsibleUnit = responsibleUnit;
        this.dateOfGeneralRequest = dateOfGeneralRequest;
        this.supply = supply;
        this.sent = sent;
        this.progressMark = progressMark;
        this.comments = comments;
    }

    public GoodsRequest() {
    }
}

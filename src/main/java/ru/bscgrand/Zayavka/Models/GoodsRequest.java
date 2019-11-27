package ru.bscgrand.Zayavka.Models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "goodsRequest")
public class GoodsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    Дата подачи заявка
    private Calendar dateOfPurchaseRequest;

//   Подразделение подающее заявку
    private String subdivision;

//   ФИО
    private String fullName;

//  Объект
    private String oilfieldName;

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

    public long getId() {
        return id;
    }

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

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setOilfieldName(String oilfieldName) {
        this.oilfieldName = oilfieldName;
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

    public String getSubdivision() {
        return subdivision;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOilfieldName() {
        return oilfieldName;
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

    public GoodsRequest(Calendar dateOfPurchaseRequest, String subdivision, String fullName, String oilfieldName, String goodsName, double amount, String unit, Calendar dateOfReceiving, String note, String responsibleUnit, Calendar dateOfGeneralRequest, boolean supply, boolean sent, boolean progressMark, String comments) {
        this.dateOfPurchaseRequest = dateOfPurchaseRequest;
        this.subdivision = subdivision;
        this.fullName = fullName;
        this.oilfieldName = oilfieldName;
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

    @Override
    public String toString() {
        return "GoodsRequest{" +
                "id=" + id +
                ", dateOfPurchaseRequest=" + dateOfPurchaseRequest +
                ", subdivision='" + subdivision + '\'' +
                ", fullName='" + fullName + '\'' +
                ", oilfieldName='" + oilfieldName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", dateOfReceiving=" + dateOfReceiving +
                ", note='" + note + '\'' +
                ", responsibleUnit='" + responsibleUnit + '\'' +
                ", dateOfGeneralRequest=" + dateOfGeneralRequest +
                ", supply=" + supply +
                ", sent=" + sent +
                ", progressMark=" + progressMark +
                ", comments='" + comments + '\'' +
                '}';
    }
}

package ru.bscgrand.Zayavka.Models;

import javax.persistence.*;

@Entity
@Table(name = "oilfield")
public class Oilfield {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Название месторождения
    private String name;

    //Куст
    private int fieldBush;

    //Скважина
    private String well;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFieldBush() {
        return fieldBush;
    }

    public void setFieldBush(int fieldBush) {
        this.fieldBush = fieldBush;
    }

    public String getWell() {
        return well;
    }

    public void setWell(String well) {
        this.well = well;
    }

    public int getId() {
        return id;
    }

    public Oilfield() {
    }

    public Oilfield(String name, int fieldBush, String well) {
        this.name = name;
        this.fieldBush = fieldBush;
        this.well = well;
    }
}

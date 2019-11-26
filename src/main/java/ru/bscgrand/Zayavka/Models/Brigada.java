package ru.bscgrand.Zayavka.Models;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Brigada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int teamNumber;
    private String teamEmail;

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public Brigada(int teamNumber, String teamEmail) {
        this.teamNumber = teamNumber;
        this.teamEmail = teamEmail;
    }

    public Brigada() {
    }
}

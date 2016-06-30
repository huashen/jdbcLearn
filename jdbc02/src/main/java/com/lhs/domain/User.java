package com.lhs.domain;

import java.util.Date;

/**
 * User
 *
 * @author longhuashen
 * @since 16/6/30
 */
public class User {

    private int id;

    private String name;

    private Date birthDay;

    private float money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}

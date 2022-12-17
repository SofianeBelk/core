package com.mycompany.tennis.core.entity;

public class Score {

    private Long id;
    private Byte set1;
    private Byte set2;
    private Byte set3;
    private Byte set4;
    private Byte set5;
    private Match match;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getSet1() {
        return this.set1;
    }

    public void setSet1(Byte set1) {
        this.set1 = set1;
    }

    public Byte getSet2() {
        return this.set2;
    }

    public void setSet2(Byte set2) {
        this.set2 = set2;
    }

    public Byte getSet3() {
        return this.set3;
    }

    public void setSet3(Byte set3) {
        this.set3 = set3;
    }

    public Byte getSet4() {
        return this.set4;
    }

    public void setSet4(Byte set4) {
        this.set4 = set4;
    }

    public Byte getSet5() {
        return this.set5;
    }

    public void setSet5(Byte set5) {
        this.set5 = set5;
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

}

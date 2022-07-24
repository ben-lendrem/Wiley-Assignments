package com.app.dto;

public class Hero {
    private Integer heroID = null;
    private String heroName = null;
    private String heroPower = null;
    private String heroDesc = null;
    private Boolean isHero = null;

    public Integer getHeroID() {
        return heroID;
    }

    public void setHeroID(Integer heroID) {
        this.heroID = heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(String heroPower) {
        this.heroPower = heroPower;
    }

    public String getHeroDesc() {
        return heroDesc;
    }

    public void setHeroDesc(String heroDesc) {
        this.heroDesc = heroDesc;
    }

    public Boolean getHero() {
        return isHero;
    }

    public void setHero(Boolean hero) {
        isHero = hero;
    }
}

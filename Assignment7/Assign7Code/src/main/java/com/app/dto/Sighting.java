package com.app.dto;

import java.sql.Timestamp;

public class Sighting {
    private Integer sightingID = null;
    private Integer heroID = null;
    private Integer locationID = null;
    private Timestamp dateOfSighting = null;

    public Integer getSightingID() {
        return sightingID;
    }

    public void setSightingID(Integer sightingID) {
        this.sightingID = sightingID;
    }

    public Integer getHeroID() {
        return heroID;
    }

    public void setHeroID(Integer heroID) {
        this.heroID = heroID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Timestamp getDateOfSighting() {
        return dateOfSighting;
    }

    public void setDateOfSighting(Timestamp dateOfSighting) {
        this.dateOfSighting = dateOfSighting;
    }
}

package com.app.dao.impl;

import com.app.dao.interfaces.SightingDAO;
import com.app.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SightingDAOImpl implements SightingDAO {

    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingID(rs.getInt("sightingID"));
            s.setHeroID(rs.getInt("heroID"));
            s.setLocationID(rs.getInt("locationID"));
            s.setDateOfSighting(rs.getTimestamp("dateOfSighting"));

            return s;
        }
    }


    private JdbcTemplate jdbc;

    @Autowired
    public SightingDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Sighting> GetAll() {
        return jdbc.query("SELECT * FROM sightings", new SightingMapper());
    }

    @Override
    public Sighting GetByID(int id) throws IndexOutOfBoundsException {
        return jdbc.query("SELECT * FROM sightings WHERE sightingID=" + id, new SightingMapper()).get(0);
    }

    @Override
    public boolean Add(Sighting objIn) {
        int r = jdbc.update(
                "INSERT INTO sightings (heroID, locationID, dateOfSighting) " +
                        "VALUES (?, ?, ?)",
                objIn.getHeroID(),
                objIn.getLocationID(),
                objIn.getDateOfSighting());
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update(Sighting objIn) {
        int r = jdbc.update(
                "UPDATE sightings SET heroID=?, locationID=?, dateOfSighting=? " +
                        "WHERE sightingID=?",
                objIn.getHeroID(), objIn.getLocationID(),
                objIn.getDateOfSighting(),
                objIn.getSightingID()
        );
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean DeleteByID(int id) {
        int r = jdbc.update("DELETE FROM sightings WHERE sightingID=?", id);
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }
}

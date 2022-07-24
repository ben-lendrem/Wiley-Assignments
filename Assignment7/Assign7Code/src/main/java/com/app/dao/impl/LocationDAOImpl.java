package com.app.dao.impl;

import com.app.dao.interfaces.LocationDAO;
import com.app.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {


    private static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location l = new Location();
            l.setLocationID(rs.getInt("locationID"));
            l.setLocationName(rs.getString("locationName"));
            //nullable
            l.setLocationDesc(rs.getString("locationDesc"));
            if (rs.wasNull()) {
                l.setLocationDesc(null);
            }
            l.setLocationAddress(rs.getString("locationAddress"));
            if (rs.wasNull()) {
                l.setLocationAddress(null);
            }
            l.setLocationCoordinates(rs.getString("locationCoordinates"));
            if (rs.wasNull()) {
                l.setLocationCoordinates(null);
            }

            return l;
        }
    }

    private JdbcTemplate jdbc;

    @Autowired
    public LocationDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Location> GetAll() {
        return jdbc.query("SELECT * FROM location", new LocationMapper());
    }

    @Override
    public Location GetByID(int id) throws IndexOutOfBoundsException {
        return jdbc.query("SELECT * FROM location WHERE locationID=" + id, new LocationMapper()).get(0);
    }

    @Override
    public boolean Add(Location objIn) {
        int r = jdbc.update(
                "INSERT INTO location (locationName, locationDesc, locationAddress, locationCoordinates) " +
                        "VALUES (?, ?, ?, ?)",
                objIn.getLocationName(),
                objIn.getLocationDesc(),
                objIn.getLocationAddress(),
                objIn.getLocationCoordinates());
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update(Location objIn) {
        int r = jdbc.update(
                "UPDATE location SET locationName=?, locationDesc=?, locationAddress=?, locationCoordinates=? " +
                        "WHERE locationID=?",
                objIn.getLocationName(), objIn.getLocationDesc(),
                objIn.getLocationAddress(), objIn.getLocationCoordinates(),
                objIn.getLocationID()
        );
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean DeleteByID(int id) {
        int r = jdbc.update("DELETE FROM location WHERE locationID=?", id);
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

}

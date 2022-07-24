package com.app.dao.impl;

import com.app.dao.interfaces.HeroDAO;
import com.app.dto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroDAOImpl implements HeroDAO {

    private static final class HeroMapper implements RowMapper<Hero> {
        @Override
        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero h = new Hero();
            h.setHeroID(rs.getInt("heroID"));
            h.setHeroName(rs.getString("heroName"));
            //nullable
            h.setHeroPower(rs.getString("heroPower"));
            if (rs.wasNull()) {
                h.setHeroPower(null);
            }
            h.setHeroDesc(rs.getString("heroDesc"));
            if (rs.wasNull()) {
                h.setHeroDesc(null);
            }
            h.setHero(rs.getBoolean("isHero"));
            if (rs.wasNull()) {
                h.setHero(null);
            }

            return h;
        }
    }

    private JdbcTemplate jdbc;

    @Autowired
    public HeroDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Hero> GetAll() {
        return jdbc.query("SELECT * FROM hero", new HeroMapper());
    }

    @Override
    public Hero GetByID(int id) throws IndexOutOfBoundsException {
        return jdbc.query("SELECT * FROM hero WHERE heroID=?" + id, new HeroMapper()).get(0);
    }

    @Override
    public boolean Add(Hero objIn) {
        int r = jdbc.update(
                "INSERT INTO hero (heroName, heroPower, heroDesc, isHero) " +
                        "VALUES (?, ?, ?, ?)",
                objIn.getHeroName(),
                objIn.getHeroPower(),
                objIn.getHeroDesc(),
                objIn.getHero());
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Update(Hero objIn) {
        int r = jdbc.update(
                "UPDATE hero SET heroName=?, heroPower=?, heroDesc=?, isHero=? " +
                        "WHERE heroID=?",
                objIn.getHeroName(), objIn.getHeroPower(),
                objIn.getHeroDesc(), objIn.getHero(),
                objIn.getHeroID()
        );
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean DeleteByID(int id) {
        int r = jdbc.update("DELETE FROM hero WHERE heroID=?", id);
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }
}

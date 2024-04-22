package com.mccullough.animalshelter.dao;

import com.mccullough.animalshelter.exception.DaoException;
import com.mccullough.animalshelter.model.Guest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcGuestDao implements GuestDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGuestDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Guest getGuestById(int guestId) {
        Guest guest = null;
        String sql = "SELECT g.id, g.type_id, name, gender, background\n" +
                "FROM guest g\n" +
                "WHERE g.id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, guestId);
            if (results.next()) {
                guest = mapRowToGuest(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return guest;

    }

    @Override
    public List<Guest> getGuests() {

        List<Guest> guests = new ArrayList<>();

        String sql = "SELECT g.id, g.type_id, name, gender, background\n" +
                "FROM guest g\n";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Guest guest = mapRowToGuest(results);
                guests.add(guest);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return guests;
    }

    @Override
    public Guest createGuest(Guest newGuest) {
        Guest guest = null;
        String sql = "INSERT INTO public.guest(\n" +
                "\ttype_id, name, gender, background)\n" +
                "\tVALUES (?, ?, ?, ?) \n" +
                "\tRETURNING id;";
        try {
            int guestId = jdbcTemplate.queryForObject(sql, int.class, newGuest.getTypeId(), newGuest.getName(),
                    newGuest.getGender(), newGuest.getBackground());
            guest = getGuestById(guestId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return guest;
    }

    private Guest mapRowToGuest(SqlRowSet rs) {
        Guest guest = new Guest();

        guest.setId(rs.getInt("id"));
        guest.setTypeId(rs.getInt("type_id"));
        guest.setName(rs.getString("name"));
        guest.setGender(rs.getString("gender"));
        guest.setBackground(rs.getString("background"));

        return guest;
    }
}

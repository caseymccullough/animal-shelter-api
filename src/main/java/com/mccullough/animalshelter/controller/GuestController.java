package com.mccullough.animalshelter.controller;

import com.mccullough.animalshelter.dao.GuestDao;
import com.mccullough.animalshelter.exception.DaoException;
import com.mccullough.animalshelter.model.Guest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/guests")
public class GuestController {
    private GuestDao guestDao;

    public GuestController (GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Guest> list (){
        try {
            return guestDao.getGuests();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path= "/{id}", method = RequestMethod.GET)
    public Guest getById(@PathVariable int id) {
        try {
            Guest guest = guestDao.getGuestById(id);
            if (guest == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The guest with id=%d was not found.", id));
            }
            return guest;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Guest add (@RequestBody Guest guest) {
        return guestDao.createGuest(guest);
    }


}

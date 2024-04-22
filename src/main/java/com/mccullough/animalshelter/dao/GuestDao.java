package com.mccullough.animalshelter.dao;

import com.mccullough.animalshelter.model.Guest;

import java.util.List;

public interface GuestDao {

    /**
     * Get a guest from the datastore with the specified id.
     * If the id is not found, return null.
     *
     * @param guestId The id of the guest to return.
     *
     * @return The matching Guest object, or null if the guest is not found
     */
    Guest getGuestById(int guestId);


    /**
     * Guest a list of all guests in the datastore ordered by id
     *
     * @return List of all Guest objects, or an empty list if no Guests are found.
     */
    List<Guest> getGuests();

    /**
     * Adds a new guest to the datastore.
     *
     * @param guest the Guest object to add.
     * @return The added Guest object with its new id and any default values filled in.
     */
    Guest createGuest (Guest guest);
}

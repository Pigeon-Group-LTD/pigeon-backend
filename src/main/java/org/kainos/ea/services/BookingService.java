package org.kainos.ea.services;

import org.kainos.ea.daos.BookingDao;
import org.kainos.ea.exceptions.Entity;
import org.kainos.ea.exceptions.FailedToCreateException;
import org.kainos.ea.exceptions.FailedToDeleteException;
import org.kainos.ea.exceptions.FailedToUpdateException;
import org.kainos.ea.exceptions.NotFoundException;
import org.kainos.ea.models.Booking;
import org.kainos.ea.models.BookingRequest;
import org.kainos.ea.models.BookingUpdateRequest;

import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private final BookingDao dao;

    public BookingService(final BookingDao dao) {
        this.dao = dao;
    }

    public List<Booking> getBookings() throws SQLException {
        return dao.getBookings();
    }

    public Booking getBooking(final int id)
            throws SQLException, NotFoundException {
        Booking booking = dao.getBooking(id);

        if (booking == null) {
            throw new NotFoundException(Entity.BOOKING);
        }

        return booking;
    }

    public int createBooking(final BookingRequest bookingRequest)
            throws SQLException, FailedToCreateException {
        int bookingId = dao.createBooking(bookingRequest);

        if (bookingId == -1) {
            throw new FailedToCreateException(Entity.BOOKING);
        }

        return bookingId;
    }

    public void updateBooking(
            final int id, final BookingUpdateRequest bookingRequest)
            throws SQLException, FailedToUpdateException, NotFoundException {
        Booking bookingToUpdate = dao.getBooking(id);

        if (bookingToUpdate == null) {
            throw new NotFoundException(Entity.BOOKING);
        }

        boolean updateSuccessful = dao.updateBooking(id, bookingRequest);

        if (!updateSuccessful) {
            throw new FailedToUpdateException(Entity.BOOKING);
        }
    }

    public void deleteBooking(final int id)
            throws SQLException, FailedToDeleteException, NotFoundException {
        Booking bookingToDelete = dao.getBooking(id);

        if (bookingToDelete == null) {
            throw new NotFoundException(Entity.BOOKING);
        }

        boolean deleteSuccessful = dao.deleteBooking(id);

        if (!deleteSuccessful) {
            throw new FailedToDeleteException(Entity.BOOKING);
        }
    }
}

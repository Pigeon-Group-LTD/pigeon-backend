package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.FailedToCreateException;
import org.kainos.ea.exceptions.FailedToDeleteException;
import org.kainos.ea.exceptions.FailedToUpdateException;
import org.kainos.ea.exceptions.NotFoundException;
import org.kainos.ea.models.BookingRequest;
import org.kainos.ea.models.BookingUpdateRequest;
import org.kainos.ea.services.BookingService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Booking API")
@Path("/api/booking")
public class BookingController {
    BookingService bookingService;

    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookings() {
        try {
            return Response.ok().entity(bookingService.getBookings()).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooking(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(bookingService.getBooking(id)).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(final BookingRequest bookingRequest) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(bookingService.createBooking(bookingRequest))
                    .build();
        } catch (SQLException | FailedToCreateException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBooking(
            @PathParam("id") final int id,
            final BookingUpdateRequest bookingRequest
    ) {
        try {
            bookingService.updateBooking(id, bookingRequest);
            return Response.noContent().build();
        } catch (SQLException | FailedToUpdateException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBooking(
            @PathParam("id") final int id
    ) {
        try {
            bookingService.deleteBooking(id);
            return Response.noContent().build();
        } catch (SQLException | FailedToDeleteException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}

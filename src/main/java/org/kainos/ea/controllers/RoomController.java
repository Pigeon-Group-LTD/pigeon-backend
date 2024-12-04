package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.FailedToCreateException;
import org.kainos.ea.exceptions.NotFoundException;
import org.kainos.ea.models.RoomRequest;
import org.kainos.ea.services.RoomService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Room API")
@Path("/api/room")
public class RoomController {
    RoomService roomService;

    public RoomController(final RoomService roomService) {
        this.roomService = roomService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRooms() {
        try {
            return Response.ok().entity(roomService.getRooms()).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(roomService.getRoom(id)).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(final RoomRequest roomRequest) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(roomService.createRoom(roomRequest))
                    .build();
        } catch (SQLException | FailedToCreateException e) {
            return Response.serverError().build();
        }
    }
}

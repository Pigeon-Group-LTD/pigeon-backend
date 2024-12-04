package org.kainos.ea.services;

import org.kainos.ea.daos.RoomDao;
import org.kainos.ea.exceptions.Entity;
import org.kainos.ea.exceptions.FailedToCreateException;
import org.kainos.ea.exceptions.NotFoundException;
import org.kainos.ea.models.Room;
import org.kainos.ea.models.RoomRequest;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private final RoomDao dao;

    public RoomService(final RoomDao dao) {
        this.dao = dao;
    }

    public List<Room> getRooms() throws SQLException {
        return dao.getRooms();
    }

    public Room getRoom(final int id) throws SQLException, NotFoundException {
        Room room = dao.getRoom(id);

        if (room == null) {
            throw new NotFoundException(Entity.ROOM);
        }

        return room;
    }

    public int createRoom(final RoomRequest roomRequest)
            throws SQLException, FailedToCreateException {
        int roomId = dao.createRoom(roomRequest);

        if (roomId == -1) {
            throw new FailedToCreateException(Entity.ROOM);
        }

        return roomId;
    }
}

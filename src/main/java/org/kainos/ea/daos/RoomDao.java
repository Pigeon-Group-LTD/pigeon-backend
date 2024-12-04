package org.kainos.ea.daos;

import org.kainos.ea.models.Room;
import org.kainos.ea.models.RoomRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    public List<Room> getRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT RoomId, RoomName "
                    + "FROM Room;"
            );

            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("RoomId"),
                        resultSet.getString("RoomName")
                ));
            }
        }

        return rooms;
    }

    public Room getRoom(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "SELECT RoomId, RoomName "
                    + "FROM Room "
                    + "WHERE RoomId = ?;";

            PreparedStatement statement = connection
                    .prepareStatement(queryString);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Room(
                        resultSet.getInt("RoomId"),
                        resultSet.getString("RoomName")
                );
            }
        }

        return null;
    }

    public int createRoom(final RoomRequest roomRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "INSERT INTO Room (RoomName) "
                    + "VALUES (?);";

            PreparedStatement statement = connection
                    .prepareStatement(
                            queryString, Statement.RETURN_GENERATED_KEYS
                    );

            statement.setString(1, roomRequest.getRoomName());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (!resultSet.next()) {
                return -1;
            }

            return resultSet.getInt(1);
        }
    }
}

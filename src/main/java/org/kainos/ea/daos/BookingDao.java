package org.kainos.ea.daos;

import org.kainos.ea.models.Booking;
import org.kainos.ea.models.BookingRequest;
import org.kainos.ea.models.BookingUpdateRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private static final int USER_ID_PARAM = 1;
    private static final int ROOM_ID_PARAM = 2;
    private static final int GROUP_SIZE_PARAM = 3;
    private static final int BOOKING_DATE_PARAM = 4;
    private static final int START_TIME_PARAM = 5;
    private static final int END_TIME_PARAM = 6;
    private static final int BOOKING_ID_PARAM = 7;

    private static final int GROUP_SIZE_UPDATE_PARAM = 1;
    private static final int BOOKING_DATE_UPDATE_PARAM = 2;
    private static final int START_TIME_UPDATE_PARAM = 3;
    private static final int END_TIME_UPDATE_PARAM = 4;
    private static final int BOOKING_ID_UPDATE_PARAM = 5;

    public List<Booking> getBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT UserId, RoomId, GroupSize, "
                    + "BookingDate, StartTime, EndTime, BookingId "
                    + "FROM Booking;"
            );

            while (resultSet.next()) {
                bookings.add(new Booking(
                        resultSet.getInt("BookingId"),
                        resultSet.getInt("UserId"),
                        resultSet.getInt("RoomId"),
                        resultSet.getInt("GroupSize"),
                        resultSet.getDate("BookingDate"),
                        resultSet.getTime("StartTime"),
                        resultSet.getTime("EndTime")
                ));
            }
        }

        return bookings;
    }

    public Booking getBooking(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "SELECT UserId, RoomId, GroupSize, "
                    + "BookingDate, StartTime, EndTime, BookingId "
                    + "FROM Booking "
                    + "WHERE BookingId = ?;";

            PreparedStatement statement = connection
                    .prepareStatement(queryString);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Booking(
                        resultSet.getInt("BookingId"),
                        resultSet.getInt("UserId"),
                        resultSet.getInt("RoomId"),
                        resultSet.getInt("GroupSize"),
                        resultSet.getDate("BookingDate"),
                        resultSet.getTime("StartTime"),
                        resultSet.getTime("EndTime")
                );
            }
        }

        return null;
    }

    public int createBooking(final BookingRequest bookingRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "INSERT INTO Booking (UserId, RoomId, "
                    + "GroupSize, BookingDate, StartTime, EndTime) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection
                    .prepareStatement(
                            queryString, Statement.RETURN_GENERATED_KEYS
                    );

            statement.setInt(USER_ID_PARAM, bookingRequest.getUserId());
            statement.setInt(ROOM_ID_PARAM, bookingRequest.getRoomId());
            statement.setInt(GROUP_SIZE_PARAM, bookingRequest.getGroupSize());
            statement.setDate(
                    BOOKING_DATE_PARAM, bookingRequest.getBookingDate());
            statement.setTime(START_TIME_PARAM, bookingRequest.getStartTime());
            statement.setTime(END_TIME_PARAM, bookingRequest.getEndTime());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (!resultSet.next()) {
                return -1;
            }

            return resultSet.getInt(1);
        }
    }

    public boolean updateBooking(
            final int id, final BookingUpdateRequest bookingRequest
    ) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "UPDATE Booking SET "
                    + "GroupSize = ?, "
                    + "BookingDate = ?, "
                    + "StartTime = ?, "
                    + "EndTime = ? "
                    + "WHERE BookingId = ?;";

            PreparedStatement statement = connection
                    .prepareStatement(queryString);

            statement.setInt(
                    GROUP_SIZE_UPDATE_PARAM, bookingRequest.getGroupSize());
            statement.setDate(
                    BOOKING_DATE_UPDATE_PARAM, bookingRequest.getBookingDate());
            statement.setTime(
                    START_TIME_UPDATE_PARAM, bookingRequest.getStartTime());
            statement.setTime(
                    END_TIME_UPDATE_PARAM, bookingRequest.getEndTime());
            statement.setInt(BOOKING_ID_UPDATE_PARAM, id);

            return statement.executeUpdate() == 1;
        }
    }

    public boolean deleteBooking(final int id)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString =  "DELETE FROM Booking "
                    + "WHERE BookingId = ?;";

            PreparedStatement statement = connection
                    .prepareStatement(queryString);

            statement.setInt(1, id);

            return statement.executeUpdate() == 1;
        }
    }
}

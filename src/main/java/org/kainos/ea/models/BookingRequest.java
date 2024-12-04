package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class BookingRequest {
    private int userId;
    private int roomId;
    private int groupSize;
    private Date bookingDate;
    private Time startTime;
    private Time endTime;

    @JsonCreator
    public BookingRequest(final @JsonProperty("userId") int userId,
                          final @JsonProperty("roomId") int roomId,
                          final @JsonProperty("groupSize") int groupSize,
                          final @JsonProperty("bookingDate") Date bookingDate,
                          final @JsonProperty("startTime") Time startTime,
                          final @JsonProperty("endTime") Time endTime) {
        this.userId = userId;
        this.roomId = roomId;
        this.groupSize = groupSize;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(final int roomId) {
        this.roomId = roomId;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(final int groupSize) {
        this.groupSize = groupSize;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(final Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(final Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(final Time endTime) {
        this.endTime = endTime;
    }
}

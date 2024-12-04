package org.kainos.ea.models;

import java.sql.Date;
import java.sql.Time;

public class Booking {
    private int id;
    private int userId;
    private int roomId;
    private int groupSize;
    private Date bookingDate;
    private Time startTime;
    private Time endTime;

    public Booking(final int id,
                   final int userId,
                   final int roomId,
                   final int groupSize,
                   final Date bookingDate,
                   final Time startTime,
                   final Time endTime) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.groupSize = groupSize;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

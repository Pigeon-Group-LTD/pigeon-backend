package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class BookingUpdateRequest {
    private int groupSize;
    private Date bookingDate;
    private Time startTime;
    private Time endTime;

    @JsonCreator
    public BookingUpdateRequest(
            final @JsonProperty("groupSize") int groupSize,
            final @JsonProperty("bookingDate") Date bookingDate,
            final @JsonProperty("startTime") Time startTime,
            final @JsonProperty("endTime") Time endTime) {
        this.groupSize = groupSize;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
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

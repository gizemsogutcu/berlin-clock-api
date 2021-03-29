package com.api.berlinclock.service.impl;

import com.api.berlinclock.core.APIResponse;
import com.api.berlinclock.model.BerlinClockConstants;
import com.api.berlinclock.service.BerlinClockService;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class BerlinClockServiceImpl implements BerlinClockService {

    public APIResponse getBerlinClock(String time) throws Exception {
        if (time != null && !time.isEmpty())
            validateTime(time);
        else {
            LocalTime localTime = LocalTime.now();
            time = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }

        String[] times = time.split(":");
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        int seconds = Integer.parseInt(times[2]);

        String berlinClock = getSeconds(seconds) + " " + getHours(hours) + " " + getMinutes(minutes);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setBody(berlinClock);

        return apiResponse;
    }

    public void validateTime(String time) throws Exception {
        String[] times = time.split(":");
        if(times.length != 3)
            throw new Exception("Date is not correct format.");
        int hours, minutes, seconds;
        try{
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        }
        catch(Exception ex){
            throw new ParseException(ex.getMessage(), 0);
        }
        if (hours < 0 || hours > 24)
            throw new IllegalArgumentException("Hours is not correct format.");
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Minutes is not correct format.");
        if (seconds < 0 || seconds > 59)
            throw new IllegalArgumentException("Seconds is not correct format.");
    }

    public String getSeconds(int seconds) {
        return seconds % 2 == 0 ? BerlinClockConstants.YELLOW_LAMP : BerlinClockConstants.OFF_LAMP;
    }

    public String getHours(int hours) {
        int onLampsNumberFirstRow = hours / 5;
        int onLampsNumberSecondRow = hours % 5;

        StringBuilder hoursLamp = new StringBuilder();

        hoursLamp.append(getHoursLampsRow(onLampsNumberFirstRow))
                 .append(" ")
                 .append(getHoursLampsRow(onLampsNumberSecondRow));

        return hoursLamp.toString();
    }

    public String getHoursLampsRow(int numberOfLampsOn) {
        StringBuilder lampsRow = new StringBuilder();

        for(int i = 1; i <= 4; i++) {
            lampsRow.append(i <= numberOfLampsOn ? BerlinClockConstants.RED_LAMP : BerlinClockConstants.OFF_LAMP);
        }

        return lampsRow.toString();
    }

    public String getMinutes(int minutes) {
        int onLampsNumberFirstRow = minutes / 5;
        int onLampsNumberSecondRow = minutes % 5;

        StringBuilder minutesLamp = new StringBuilder();

        minutesLamp.append(getMinutesLampsRow(onLampsNumberFirstRow, onLampsNumberSecondRow));

        return minutesLamp.toString();
    }

    public String getMinutesLampsRow(int onLampsNumberFirstRow, int onLampsNumberSecondRow) {
        StringBuilder lampsRow = new StringBuilder();

        for(int i = 1; i <= 11; i++) {
            lampsRow.append(i <= onLampsNumberFirstRow ? getMinutesLampColor(i) : BerlinClockConstants.OFF_LAMP);
        }

        lampsRow.append(" ");

        for(int i = 1; i <= 4; i++) {
            lampsRow.append(i <= onLampsNumberSecondRow ? BerlinClockConstants.YELLOW_LAMP : BerlinClockConstants.OFF_LAMP);
        }

        return lampsRow.toString();
    }

    public String getMinutesLampColor(int index) {
        return index % 3 == 0 ? BerlinClockConstants.RED_LAMP : BerlinClockConstants.YELLOW_LAMP;
    }
}

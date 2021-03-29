package com.api.berlinclock;

import com.api.berlinclock.service.impl.BerlinClockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BerlinClockServiceImplTest {

    private BerlinClockServiceImpl berlinClockService = new BerlinClockServiceImpl();

    @Test
    public void getBerlinClock_returnsBerlinClock() throws Exception {
        String time = "21:15:41";

        assertEquals("O RRRR ROOO YYROOOOOOOO OOOO", berlinClockService.getBerlinClock(time).getBody());
    }

    @Test
    public void validateTime_throwsException_when_timeIsNotCorrectFormat() {
        String time = "17::26:23";

        Exception exception = assertThrows(Exception.class, () -> {
            berlinClockService.validateTime(time);
        });

        assertEquals("Date is not correct format.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa:25:10", "13:bb:20", "22:08:cc"})
    public void validateTime_throwsException_when_timeIsNotParsable(String time) {
        assertThrows(ParseException.class, () -> {
            berlinClockService.validateTime(time);
        });
    }

    @Test
    public void validateTime_throwsException_when_hoursIsNotCorrectFormat() {
        String time = "88:55:15";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            berlinClockService.validateTime(time);
        });

        assertEquals("Hours is not correct format.", exception.getMessage());
    }

    @Test
    public void validateTime_throwsException_when_minutesIsNotCorrectFormat() {
        String time = "21:88:15";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            berlinClockService.validateTime(time);
        });

        assertEquals("Minutes is not correct format.", exception.getMessage());
    }

    @Test
    public void validateTime_throwsException_when_secondsIsNotCorrectFormat() {
        String time = "09:18:77";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            berlinClockService.validateTime(time);
        });

        assertEquals("Seconds is not correct format.", exception.getMessage());
    }

    @Test
    public void getSeconds_returnsYellowLamp_when_secondsIsEven() {
        assertEquals("Y", berlinClockService.getSeconds(10));
    }

    @Test
    public void getSeconds_returnsOffLamp_when_secondsIsOdd() {
        assertEquals("O", berlinClockService.getSeconds(21));
    }

    @Test
    public void getHours_returnsHoursLamps() {
        assertEquals("RROO RROO", berlinClockService.getHours(12));
    }

    @Test
    public void getHoursLampsRow_returnsHoursLampsRow() {
        assertEquals("RRRO", berlinClockService.getHoursLampsRow(3));
    }

    @Test
    public void getMinutes_returnsMinutesLamps() {
        assertEquals("YYRYYROOOOO YYYO", berlinClockService.getMinutes(33));
    }

    @Test
    public void getMinutesLampsRow_returnsMinutesLampsRow() {
        assertEquals("YYOOOOOOOOO YYYY", berlinClockService.getMinutesLampsRow(2, 4));
    }

    @Test
    public void getMinutesLampColor_returnsRedLamp_when_indexDivisibleByThree() {
        assertEquals("R", berlinClockService.getMinutesLampColor(6));
    }

    @Test
    public void getMinutesLampColor_returnsRedLamp_when_indexIsNotDivisibleByThree() {
        assertEquals("Y", berlinClockService.getMinutesLampColor(7));
    }

}

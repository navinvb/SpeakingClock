package com.nktech.speakingclock.controller;

import com.nktech.speakingclock.exception.InvalidInputException;
import com.nktech.speakingclock.service.SpeakingClockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SpeakingClockController {

    @Autowired
    SpeakingClockServiceImpl speakingClockServiceImpl;

    @GetMapping(value = "currentTime/{time}")
    public String getCurrentTimeInWords(@PathVariable String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date dateTime = formatter.parse(time);

            return speakingClockServiceImpl.convertCurrentTimeToWords(time);

        } catch (ParseException exception) {
            throw new InvalidInputException("Invalid Time Input Time Exception");
        }
    }
}

package com.nktech.speakingclock.service;

import com.nktech.speakingclock.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpeakingClockServiceImpl implements SpeakingClockService {

    private static final String[] twoDigits = {"zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty"};

    private static final String[] oneDigit = {"Zero", "One", " Two", " Three", " Four", " Five", " Six",
            " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen",
            " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen", "Twenty", "TwentyOne",
            "TwentyTwo", "TwentyThree"};

    @Override
    public String convertCurrentTimeToWords(String stringTime) {

        StringBuilder convertedWords = new StringBuilder("It's ");
        StringBuilder minutesInWorld = new StringBuilder();

        String[] input = stringTime.split(":");

        int hours = Integer.parseInt(input[0]);
        int minutes = Integer.parseInt(input[1]);

        validateTime(hours, minutes);
        convertHoursInWords(convertedWords, hours);
        convertMinutesInWords(convertedWords, minutesInWorld, minutes);
        checkForMiddayOrMidNight(convertedWords, hours);

        return convertedWords.toString();
    }

    private static void checkForMiddayOrMidNight(StringBuilder convertedWords, int hours) {
        if (hours == 12) {
            convertedWords.append(" and It's Midday");
        } else if (hours == 23) {
            convertedWords.append(" and It's MidNight");
        }
    }

    private static void convertMinutesInWords(StringBuilder convertedWords, StringBuilder minutesInWorld, int minutes) {
        if (minutes < 23) {
            minutesInWorld.append(" ").append(oneDigit[minutes]);
            convertedWords.append(" ").append(minutesInWorld).append(" ");
        } else {
            int unit = minutes / 10;
            int rem = minutes % 10;
            minutesInWorld.append(twoDigits[unit]).append(" ");
            minutesInWorld.append(oneDigit[rem]).append(" ");
            convertedWords.append(" ").append(minutesInWorld).append(" ");
        }
    }

    private static void convertHoursInWords(StringBuilder convertedWords, int hours) {
        String hoursInWords;
        hoursInWords = oneDigit[hours];
        convertedWords.append(hoursInWords);
    }

    private static void validateTime(int hours, int minutes) {
        if (hours > 23 || hours < 0 || minutes > 59 || minutes < 0) {
            throw new InvalidInputException("Invalid time input");
        }
    }

}

package smevel.services;

import smevel.constants.StringConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String getFormattedStringByDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(StringConstants.DATE_FORMAT);
        return formatter.format(date);
    }

    public static Date getDateByFormattedStringBy(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(StringConstants.DATE_FORMAT);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse date: " + e.getMessage());
        }

    }
}

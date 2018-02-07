package io.moku.davide.sideproject.utils.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.moku.davide.sideproject.utils.Constants;

/**
 * Created by Davide Castello on 07/02/18.
 * Project: side-project
 * Copyright © 2018 Moku S.r.l. All rights reserved.
 */

public class DateUtils {

    public static final int SECONDS = 1000;
    public static final int SECONDS_IN_MONTH = 60 * 60 * 24 * 28;

    public static String getDateInTheCorrectFormat(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.POST_PREFERRED_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return formatter.format(calendar.getTime());
    }

    public static long getCurrentTimestamp() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static long getRandomTimestampInTheLastMonth() {
        long currentSeconds = Calendar.getInstance().getTimeInMillis() / 1000;
        long randomTimestamp = currentSeconds - (long) (Math.random() * SECONDS_IN_MONTH);
        return randomTimestamp * 1000;
    }
}

/*
 * Copyright 2017 Riigi Infosüsteemide Amet
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package ee.ria.EstEIDUtility.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String TAG = DateUtils.class.getName();
    public static final SimpleDateFormat YYYY_FORMAT = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat MMDD_FORMAT = new SimpleDateFormat("ddMM");
    public static final SimpleDateFormat DDMM_FORMAT = new SimpleDateFormat("MMdd");

    public static final SimpleDateFormat TODAY_FORMAT = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat CURRENT_YEAR_FORMAT = new SimpleDateFormat("dd.MMM");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private static final SimpleDateFormat SIGNATURE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat APP_FORMAT = new SimpleDateFormat("dd MMM yyyy HH:mm");

    public static String formatSignedDate(String trustedSigningTime) {
        try {
            Date signedDate = SIGNATURE_FORMAT.parse(trustedSigningTime);
            return APP_FORMAT.format(signedDate);
        } catch (ParseException e) {
            Log.e(TAG, "formatSignedDate: ", e);
        }
        return null;
    }

    public static boolean isToday(Date date1) {
        if (date1 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        return isSameDay(cal1);
    }

    private static boolean isSameDay(Calendar cal1) {
        Date today = Calendar.getInstance().getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);
        if (cal1 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isYesterday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isCurrentYear(Date date) {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        return year == c1.get(Calendar.YEAR);
    }
}
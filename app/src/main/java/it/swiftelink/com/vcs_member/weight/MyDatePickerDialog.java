package it.swiftelink.com.vcs_member.weight;

import android.util.Log;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDatePickerDialog extends DatePickerDialogFragment {
    @Override
    protected void initChild() {
        super.initChild();
       mDatePicker.setMaxDate(new Date().getTime());
    }

    public void setBirthDay(long birthDay){
        Date date = new Date(birthDay);
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        setSelectedDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
    }
}

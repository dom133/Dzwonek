package pl.dom133.dzwonek;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        EditText et;
        Times tim;

        public TimePickerFragment(EditText et, Times tim)
        {
            this.et = et;
            this.tim = tim;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String minutes = Integer.toString(minute);
            String hourOfDays = Integer.toString(hourOfDay);
            if(minute>=0 && minute<10){minutes = "0"+minutes;}
            if(hourOfDay>=0 && hourOfDay<10){hourOfDays = "0"+hourOfDays;}
            et.setText(hourOfDays + ":" + minutes);
        }
    }
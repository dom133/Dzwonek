package pl.dom133.dzwonek;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dom133 on 24.03.2016.
 */
public class Times {
    public List<String> od_time_list = new ArrayList<>();
    public List<String> do_time_list = new ArrayList<>();
    public String od_time;
    public String do_time;

    public ArrayList<Integer> getTime()
    {
        ArrayList<Integer> list = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        list.add(Integer.valueOf(hour));
        list.add(Integer.valueOf(minute));
        return list;
    }

}

package pl.dom133.dzwonek;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings{

    //Private variables
    private Application app;

    public Settings(Application app) {
        this.app = app;
    }

    public void saveString(String name, String text) {
        SharedPreferences settings = app.getSharedPreferences("Dzwonek", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name,text);
        editor.commit();
    }

    public void saveArray(String type, ArrayList<String> list) {
        SharedPreferences settings = app.getSharedPreferences("Dzwonek", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("List_long", list.size());
        for(int i=0; i<=list.size()-1; i++)
        {
            editor.putString("List["+type+"]_"+i, list.get(i));
        }

        editor.commit();
    }

    public ArrayList<String> getArray(String type) {
        SharedPreferences settings;
        ArrayList<String> list = new ArrayList<>();
        settings = app.getSharedPreferences("Dzwonek", 0);
        int size = settings.getInt("List_long", 0);

        for(int i=0; i<=size-1; i++)
        {
            list.add(settings.getString("List["+type+"]_"+i, null));
        }

        return list;
    }

    public String getString(String name){
        SharedPreferences settings = app.getSharedPreferences("Dzwonek", 0);
        return settings.getString(name, null);
    }

    public void clearArray(String type) {
        SharedPreferences settings = app.getSharedPreferences("Dzwonek", 0);
        SharedPreferences.Editor editor = settings.edit();
        int size = settings.getInt("List_long", 0);
        for(int i=0; i<=size-1; i++) {
            editor.remove("List["+type+"]_"+i);
        }
        editor.remove("List_long");
        editor.commit();
    }

    public boolean checkExist(String name) {
        SharedPreferences settings = app.getSharedPreferences("Dzwonek", 0);
        return settings.contains(name);
    }
}

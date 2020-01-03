package com.bcc.cyost;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.room.TypeConverter;

public class TConverter {

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(List<String> list) {
        Gson gson = new Gson();

        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static List<Long> AfromString(String value) {
        Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String AfromArrayList(List<Long> list) {
        Gson gson = new Gson();

        String json = gson.toJson(list);
        return json;
    }
  /*  @TypeConverter
    public static Boolean checktoken(int charid, String tokenname, Context c){
        CDatabase cd =CDatabase.getDatabase(c);
        if(cd.counter().countToken()<=0)return  false; else
        return true;
    }
    @TypeConverter
    public static Boolean checktokenlessthan(int charid, String tokenname, int value, Context c){
        CDatabase cd =CDatabase.getDatabase(c);
        if(cd.counter().countToken()<value)return  false; else
            return true;
    }
    @TypeConverter
    public static Boolean checktokennotlessthan(int charid, String tokenname, int value, Context c){
        CDatabase cd =CDatabase.getDatabase(c);
        if(cd.counter().countToken()>=value)return  false; else
            return true;
    }*/
    }




package com.skyreds.truyentranh.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class History {
    public static History instance;
    private Set<String> setA = new HashSet<String>();

    public static History getInstance() {
        if (instance == null) {
            instance = new History();
        }
        return instance;
    }

    public interface IHistory {
        void onSuccess(String success);

        void onFail(String fail);

    }

    public void HistoryComic(Context context, String url, IHistory iHistory) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.getStringSet("history", new HashSet<String>()) == null) {
            setA.add(url);
            editor.putStringSet("history", setA);
            editor.apply();
        } else {
            setA.clear();
            setA.add(url);
            List<String> list = new ArrayList<>(sharedPreferences.getStringSet("history", new HashSet<String>()));
            for (int i = 0; i < list.size(); i++) {
                setA.add(list.get(i));
            }
            editor.putStringSet("history", setA);
            editor.commit();
        }
    }
}

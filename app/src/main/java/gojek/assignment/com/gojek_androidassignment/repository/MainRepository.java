package gojek.assignment.com.gojek_androidassignment.repository;

import android.content.Context;

import java.util.*;

/**
 * Created by Akul Aggarwal on 26/07/19.
 */
public class MainRepository {

    private static Context mContext;
    private static MainRepository mInstance;

    private static HashSet<Character> alphabetSet = new HashSet<>();

    public static MainRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (mInstance == null) {
            mInstance = new MainRepository();
        }
        return mInstance;
    }


    private MainRepository() {

    }

}

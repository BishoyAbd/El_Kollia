package com.projects.cactus.el_kollia.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.projects.cactus.el_kollia.util.AppConstants;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Bishoy Abd on 3/22/2018.
 */

public class LocalAuthStore {

    private SharedPreferences pref;

    @Inject
    public LocalAuthStore(Context context) {
        pref = context.getApplicationContext().getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(AppConstants.KEY_USER_ID, "uid1").apply();

    }

    public Observable<String> getUserId(String key) {
        String userId = pref.getString(key, AppConstants.ERROR_ID_INVALIDE);
        Timber.d("user_id from pref ---> " + userId);
        return Observable.just(userId);
    }
}

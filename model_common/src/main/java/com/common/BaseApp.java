package com.common;import android.app.Activity;import android.support.multidex.MultiDexApplication;import com.common.util.ListUtiles;import com.common.util.LogUtil;import com.common.util.SharePersistent;import com.common.util.Tool;import com.domain.BaseUser;import com.google.android.gms.common.api.GoogleApiClient;import java.util.HashMap;import java.util.LinkedList;import java.util.List;public abstract class BaseApp extends MultiDexApplication {    protected LinkedList<Activity> activitys = null;    private HashMap<String, Object> tempParamap;    private static final String apiTokenKey = "adfalsdfkalsdfkjaldksfjal";    private BaseUser user;    public static final String DirFileName = "cretve_cache";    public static String CACHE_DIR;    public static String CACHE_DIR_PRODUCTS;    public static volatile int resumed;    public static volatile int stopped;    @Override    public void onCreate() {        super.onCreate();        activitys = new LinkedList<Activity>();        this.tempParamap = new HashMap();    }    public void clearToken() {        SharePersistent.savePreference(this, apiTokenKey, "");    }    public void saveApiToken(String token) {        SharePersistent.savePreferenceEn(this, apiTokenKey, token);    }    public String getApiToken() {        try {            return SharePersistent.getPreferenceDe(this, apiTokenKey);        } catch (Exception e) {        }        return "";    }    public BaseUser getUser() {        return user;    }    public void setUser(BaseUser user) {        this.user = user;    }    public void addActivity(Activity activity) {        if (activitys != null) {            if (!activitys.contains(activity)) {                activitys.add(activity);            }        }    }    public void exit() {        if (!ListUtiles.isEmpty(activitys)) {            for (Activity activity : activitys) {                if (null != activity && !activity.isFinishing()) {                    activity.finish();                    LogUtil.i("teacher", "xx" + activity.hashCode());                }            }        }        Tool.exitApp();    }    public LinkedList<Activity> getAllActivitys() {        return activitys;    }    public void putTemPObject(String key, Object object) {        this.tempParamap.put(key, object);    }    public boolean hasTempKey(String key) {        return this.tempParamap.containsKey(key);    }    public Object getTempObject(String key) {        return this.tempParamap.remove(key);    }    public boolean isAppVisible() {        return appVisible;    }    public void activityResumed() {        appVisible = true;    }    public void activityPaused() {        appVisible = false;    }    public boolean appVisible;}
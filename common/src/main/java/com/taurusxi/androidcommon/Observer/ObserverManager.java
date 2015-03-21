package com.taurusxi.androidcommon.Observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ObserverManager {


    public static final String LOCATION = "Location";
    public static final String MSG_NUMB = "MSG_NUMB";
    public static final String MSG_NUMB_MAIN = "MSG_NUMB_MAIN"; // MainActivity主页
    public static final String MSG_NUMB_MESSAGE = "MSG_NUMB_MESSAGE"; //MessageActivity主页
    public static final String LOCATION_MAIN = "LOCATION_MAIN";
    public static final String LOCATION_SMS = "LOCATION_SMS";
    public static final String CLEAR = "CLEAR";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String ANIMATION_MAIN = "ANIMATION_MAIN";
    public static final String FRIEND_CHANGE = "FRIEND_CHANGE";
    /**
     * String 为生产者 为当前生产者的所有观察者
     */
    public static ConcurrentHashMap<String, ObserverListener> observer = new ConcurrentHashMap<String, ObserverListener>();

    public static void addObserver(String name, ObserverListener o) {
        if (!observer.containsKey(name)) {
            observer.put(name, o);
        }
    }

    public static ObserverListener getObserver(String name) {
//        System.out.println("=name="+name+"   has? + "+observer.containsKey(name));
        if (!observer.containsKey(name)) {
//            observer.put(name, temp);
            return null;
        }
        return observer.get(name);
    }

    public static void removeObserver(String name) {
        if (observer.containsKey(name)) {
            observer.remove(name);
        }
    }

    private static ObserverListener temp = new ObserverListener() {
        @Override
        public void observer(String from, Object msg) {

        }
    };

    /**
     * 主题 遍历 推送消息给 所有观察者
     */
    public static void observerAll(String from, Object msg) {
        for (Map.Entry<String, ObserverListener> listen : observer.entrySet()) {
            listen.getValue().observer(from, msg);
        }
    }
}

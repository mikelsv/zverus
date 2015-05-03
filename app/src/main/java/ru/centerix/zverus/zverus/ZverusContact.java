package ru.centerix.zverus.zverus;

import java.lang.String;
import android.util.Log;
/**
 * Created by MikelSV on 02.05.2015.
 */
public class ZverusContact {
    int uid, ltime;
    public String login, name;

    public ZverusContact(int uid, int ltime, String login, String name){
        //Log.i("skylee", uid+" "+login+" "+name);
        this.uid=uid;
        this.ltime=ltime;
        this.login=login;
        this.name=name;
    }
}

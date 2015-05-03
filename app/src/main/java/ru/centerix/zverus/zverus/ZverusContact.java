package ru.centerix.zverus.zverus;

import java.lang.String;
import android.util.Log;
/**
 * Created by MikelSV on 02.05.2015.
 */
public class ZverusContact implements Comparable{
    int uid, ltime;
    public String login, name;

    public ZverusContact(int uid, int ltime, String login, String name){
        //Log.i("skylee", uid+" "+login+" "+name);
        this.uid=uid;
        this.ltime=ltime;
        this.login=login;
        this.name=name;
    }

    public int compareTo(Object o){
        if(this.ltime<((ZverusContact)o).ltime)
            return 1;
        else if(this.ltime>((ZverusContact)o).ltime)
            return -1;
        return 0;
    }
}

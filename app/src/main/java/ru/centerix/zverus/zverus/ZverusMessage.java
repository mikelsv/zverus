package ru.centerix.zverus.zverus;

/**
 * Created by MikelSV on 02.05.2015.
 */
public class ZverusMessage {
    int mid, uid, tm;
    public String msg;

    public ZverusMessage(int mid, int uid, int tm, String msg){
        this.mid=mid;
        this.uid=uid;
        this.tm=tm;
        this.msg=msg;
    }
}

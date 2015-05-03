package ru.centerix.zverus.zverus;
/*
import android.telephony.TelephonyManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.os.Build;
import android.database.Cursor;
import android.content.Context;


public class ZverusNative {
    static {
        System.loadLibrary("nativezverus");
    }

    // native functions
    static public native int Login(String login, String pass);
    static public native int Register(String login, String email, String pass);
    static public native int Login();
    public native int LogOut();
    static public native int GetLoginId();
    public native String GetLoginName();
    static public native String GetLoginError();

    //contacts
    //public native int LoadContacts();
    public native int AddContact(String login);
    public native ZverusContact[] GetContacts();
    public native ZverusContact[] GetContact(String login);
    // chats
    public native ZverusContact[] GetChats();
    public native int GetChatsUpd();
    // messages
    public native ZverusMessage[] GetMessages(int cid);
    public native int GetMessagesUpd(int cid);
    public native boolean SendMessage(int cid, String msg);

    public native void NativeNmea(String nmea);


    public native void NativeInitAndroidId(String path, String id, String uid, String mid);

    // MSV Android Core
    public void InitAndroidId(Context context){
        //Context context;

        TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String aid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String market_id="";

        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length()%10+ Build.BRAND.length()%10 +
                Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
                Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
                Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
                Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
                Build.TAGS.length()%10 + Build.TYPE.length()%10 +
                Build.USER.length()%10 ; //13 digits

        //String[] arrayOfString = { "android_id" };
        //    Cursor localCursor = getContentResolver().query(a, null, null, arrayOfString, null);
        //	if((localCursor.moveToFirst()) && (localCursor.getColumnCount() >= 2)){
        //		  market_id = Long.toHexString(Long.parseLong(localCursor.getString(1))).toUpperCase();
        //    }

        NativeInitAndroidId(context.getFilesDir().toString(), aid, m_szDevIDShort, market_id);

        return;
    }


}

*/
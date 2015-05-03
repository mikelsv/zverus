package ru.centerix.zverus.zverus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import android.telephony.TelephonyManager;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.os.Build;
import android.database.Cursor;
import android.content.Context;


public class ZverusActivity extends Activity{
    static {
        System.loadLibrary("nativezverus");
    }

    //ZverusNative _native = new ZverusNative();

    // layout functions
    int this_layout;
    public boolean SetLayout(int id){
        if(GetLoginId()==0 && id!=R.layout.load && id!=R.layout.login && id!=R.layout.register){
            super.setContentView(R.layout.login);
            return false;
        }
        else{
            this_layout=id;
            super.setContentView(id);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitAndroidId(this);
        OnLoadLayout();
    }

    void OnLoadLayout(){
        ZverusLoginActivity loginregister= new ZverusLoginActivity(this, this);
        loginregister.OnLoadLayout();
    }

    void OnLoginLayout(){
        ZverusLoginActivity loginregister= new ZverusLoginActivity(this, this);
        loginregister.OnLoginLayout();
        }

    void OnRegisterLayout(){
        ZverusLoginActivity loginregister= new ZverusLoginActivity(this, this);
        loginregister.OnRegisterLayout();
    }


    // Chats Activity
    ZverusChatsActivity chat= new ZverusChatsActivity(this, this);
    void OnChatsLayout(){
        //ZverusChatsActivity chat= new ZverusChatsActivity(this, this);
        chat.OnChatsLayout();
    }

    void OnContactsLayout(){
        //          contacts.OnContactsLayout();
    }

    void OnChatLayout(ZverusContact con){
        chat.OnChatLayout(con);
    }

    @Override
    public void onBackPressed(){
        if(this_layout==R.layout.chat || this_layout==R.layout.contacts)
            OnChatsLayout();
        else
            super.onBackPressed();
    }



    @Override
    public void onStop(){
        super.onStop();
        //unregisterReceiver(receiver);
    }

/*
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    void onCreateZ(Bundle savedInstanceState) {



        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  //* host Activity * /
                mDrawerLayout,         //* DrawerLayout object * /
          //      R.drawable.ic_drawer,  //* nav drawer image to replace 'Up' caret * /
                R.string.drawer_open,  //* "open drawer" description for accessibility * /
                R.string.drawer_close  //* "close drawer" description for accessibility * /
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    //* The click listner for ListView in the navigation drawer * /
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }*/

    private void selectItem(int position) {
        // update the main content by replacing fragments
   //    Fragment fragment = new PlanetFragment();
   //    Bundle args = new Bundle();
   //     args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
   //     fragment.setArguments(args);

    //    FragmentManager fragmentManager = getFragmentManager();
   //     fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
    //    mDrawerList.setItemChecked(position, true);
   //     setTitle(mPlanetTitles[position]);
   //     mDrawerLayout.closeDrawer(mDrawerList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_resource, menu);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.openDrawer(mDrawerList);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // native functions
    public native int Login(String login, String pass);
    public native int Register(String login, String email, String pass);
    public native int Login();
    public native int LogOut();
    public native int GetLoginId();
    public native String GetLoginName();
    public native String GetLoginError();

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

    public native int Test();
    public native int Test2();

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
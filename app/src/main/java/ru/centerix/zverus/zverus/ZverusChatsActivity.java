package ru.centerix.zverus.zverus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MikelSV on 03.05.2015.
 */
public class ZverusChatsActivity {
    private final Context context;
    ZverusActivity zparent;

    public ZverusChatsActivity(Context scontext, ZverusActivity skl){
        this.context = scontext;
        this.zparent = skl;
    }

    boolean SetLayout(int id){
        return zparent.SetLayout(id);
    }
    View findViewById(int id){
        return zparent.findViewById(id);
    }
    // end of class functions

    ZverusContact tcon;
    int mupd, mupdrun;
    Timer timer;

    private ArrayList<HashMap<String, Object>> chat_list;
    private static final String UID = "uid";
    private static final String LTIME = "ltime";
    private static final String TITLE = "login";
    private static final String DESCRIPTION = "name";
    private static final String ICON = "icon";


    void DrawChats(){
        ListView listView = (ListView) findViewById(R.id.ChatsLayout_List);

        chat_list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;

        mupd=zparent.GetChatsUpd();
        ZverusContact[] con = zparent.GetChats();

        for(int i=0; i<con.length; i++){
            hm = new HashMap<String, Object>();
            hm.put(UID, con[i].uid);
            hm.put(LTIME, con[i].ltime);
            hm.put(TITLE, con[i].login);
            hm.put(DESCRIPTION, con[i].name);
            hm.put(ICON, R.drawable.shop);
            chat_list.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(zparent, chat_list,
                R.layout.list_item, new String[]{TITLE, DESCRIPTION, ICON},
                new int[]{R.id.text1, R.id.text2, R.id.img});

        Log.i("skylee", "SETADAPTER");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListener);

        return ;
    }





    void OnChatsLayout(){
        if(!SetLayout(R.layout.chats))
            return ;

        Button btn;

        DrawChats();

        btn = (Button) findViewById(R.id.ChatsLayout_Map);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //timer.cancel();
                //timer.purge();
                mupdrun=0;
                //skl.OnMainLayout();
                zparent.LogOut();
                zparent.OnLoginLayout();
                return ;
            }
        });

        btn = (Button) findViewById(R.id.ChatsLayout_Contacts);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //timer.cancel();
                //timer.purge();
                mupdrun=0;
                zparent.OnContactsLayout();
                return ;
            }
        });


        MAutoAsync mt = new MAutoAsync(); mupdrun=1;
        mt.setBg(new MAutoAsyncFunc(){ @Override public void execute(){
            while(mupdrun==1){
                //Log.i("skylee", "maa");
                int upd=zparent.GetChatsUpd();
                if(mupd!=upd){
                    //mupd=upd;
                    ma.setProgress(0);
                }
                try { Thread.sleep(100); } catch (InterruptedException ie) { }
            }
        }
        });

        mt.setProg(new MAutoAsyncFunc(){ @Override public void execute(){
            DrawChats();
        }
        });

        mt.execute();

        return ;
    }



    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, Object> itemHashMap = (HashMap <String, Object>) parent.getItemAtPosition(position);

            ZverusContact con = new ZverusContact(Integer.parseInt(itemHashMap.get(UID).toString()), Integer.parseInt(itemHashMap.get(LTIME).toString()), itemHashMap.get(TITLE).toString(), itemHashMap.get(DESCRIPTION).toString());
            zparent.OnChatLayout(con);

            //String titleItem = itemHashMap.get(TITLE).toString();
            //String descriptionItem = itemHashMap.get(DESCRIPTION).toString();
            //int imageItem = 0; //(int)itemHashMap.get(ICON);
            //Toast.makeText(skl.getApplicationContext(),
            //        "Selected " + titleItem + ".  " + descriptionItem, Toast.LENGTH_SHORT).show();
        }
    };

    void DrawChatMessages(){
        ListView listView = (ListView) findViewById(R.id.ChatLayout_List);

        chat_list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;

        mupd=zparent.GetMessagesUpd(tcon.uid);
        ZverusMessage[] msgs = zparent.GetMessages(tcon.uid);


        for(int i=0; i<msgs.length; i++){
            hm = new HashMap<String, Object>();
            hm.put(UID, msgs[i].mid);
            hm.put(TITLE, msgs[i].msg);
            hm.put(DESCRIPTION, "no desc");
            hm.put(ICON, R.drawable.shop);
            chat_list.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(zparent, chat_list,
                R.layout.list_item, new String[]{TITLE, DESCRIPTION, ICON},
                new int[]{R.id.text1, R.id.text2, R.id.img});

        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(itemClickListener);

        return ;
    }


    void OnChatLayout(final ZverusContact con){
        if(!SetLayout(R.layout.chat))
            return ;

        Button btn;
        tcon=con;

        TextView view = (TextView) findViewById(R.id.ChatLayout_ChatName);
        view.setText(con.login);

        DrawChatMessages();

        btn = (Button) findViewById(R.id.ChatLayout_Chat);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //timer.cancel();
                //timer.purge();
                mupdrun=0;
                OnChatsLayout();
                return ;
            }
        });

        btn = (Button) findViewById(R.id.ChatLayout_Send);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText text = (EditText) findViewById(R.id.ChatLayout_Message);

                zparent.SendMessage(con.uid, text.getText().toString());
                text.setText("");
                DrawChatMessages();
                return ;
            }
        });

        //timer = new Timer();
        //timer.schedule(new MessagesTask(con.uid), 0, 1000);

        MAutoAsync mt = new MAutoAsync(); mupdrun=2;
        final int cid[] = new int[1]; cid[0]=con.uid;
        mt.setBg(new MAutoAsyncFunc(){ @Override public void execute(){
            while(mupdrun==2){
                //Log.i("skylee", "maa");
                int upd=zparent.GetMessagesUpd(cid[0]);
                if(mupd!=upd){
                    //Log.i("skylee", "maa: "+upd+", "+mupd);
                    //mupd=upd;
                    //Log.i("skylee", "maa: "+upd+", "+mupd);
                    ma.setProgress(0);
                }
                try { Thread.sleep(100); } catch (InterruptedException ie) { }
            }
        }
        });

        mt.setProg(new MAutoAsyncFunc(){ @Override public void execute(){
            //Log.i("skylee", "upd");
            DrawChatMessages();
        }
        });

        mt.execute();

        return ;
    }


/*	// Contacts
	int OnContactsLayoutRun = 0;

	private ArrayList<HashMap<String, Object>> mCatList;
	private static final String UID = "uid";
    private static final String TITLE = "login";
    private static final String DESCRIPTION = "name";
    private static final String ICON = "icon";

	void DrawContacts(SkyleeContact[] con){
		ListView listView = (ListView) skl.findViewById(R.id.ContactsLayout_List);

		mCatList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;

		for(int i=0; i<con.length; i++){
			//Log.i("skylee", con[i].uid+" "+con[i].login+" "+con[i].name);

			hm = new HashMap<String, Object>();
			hm.put(UID, con[i].uid);
			hm.put(TITLE, con[i].login);
			hm.put(DESCRIPTION, con[i].name);
			hm.put(ICON, R.drawable.shop);
			mCatList.add(hm);
		}

        SimpleAdapter adapter = new SimpleAdapter(skl, mCatList,
                R.layout.list_item, new String[]{TITLE, DESCRIPTION, ICON},
                new int[]{R.id.text1, R.id.text2, R.id.img});

        listView.setAdapter(adapter);
		listView.setOnItemClickListener(itemClickListener);

		return ;
	}

	void OnContactsLayout(){
		skl.setContentView(R.layout.contacts);
		Button btn;

		SkyleeContact[] con = skl.GetContacts();
		DrawContacts(con);

		btn = (Button) skl.findViewById(R.id.ContactsLayout_Chat);
		btn.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v){
			skl.OnChatsLayout();
			return ;
		}
		});

		btn = (Button) skl.findViewById(R.id.ContactsLayout_Add);
		btn.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v){

			EditText login = (EditText) skl.findViewById(R.id.ContactsLayout_Search);

			if(OnContactsLayoutRun!=0)
				return;

			OnContactsLayoutRun=1;

			MAutoAsync mt = new MAutoAsync();
			final int result[] = new int[1];
			final SkyleeContact[] con2 = new SkyleeContact[1];
			final String slogin=login.getText().toString();

			mt.setPre(new MAutoAsyncFunc(){ @Override public void execute(){
				TextView error = (TextView) skl.findViewById(R.id.ContactsLayout_Error);
				error.setText("Search...");
				}
			});

			mt.setBg(new MAutoAsyncFunc(){ @Override public void execute(){
				SkyleeContact[] con=skl.SearchContact(slogin);
				if(con.length>0){
					result[0]=1;
					con2[0]=con[0];
				}else
					result[0]=0;
			}
			});

			mt.setPost(new MAutoAsyncFunc(){ @Override public void execute(){
				if(result[0]!=0){
					//OnContactsLayout();
					DrawContacts(con2);
				}
				else{
					String err=skl.GetLoginError();
					TextView error = (TextView) skl.findViewById(R.id.ContactsLayout_Error);
					error.setText(err);
				}
				OnContactsLayoutRun=0;
				}
			});

			mt.execute();
			return ;
		}
		});
	}


    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, Object> itemHashMap = (HashMap <String, Object>) parent.getItemAtPosition(position);

			SkyleeContact con = new SkyleeContact(itemHashMap.get(UID), itemHashMap.get(TITLE).toString(), itemHashMap.get(DESCRIPTION).toString());

			OnChatLayout(con);

			//String titleItem = itemHashMap.get(TITLE).toString();
            //String descriptionItem = itemHashMap.get(DESCRIPTION).toString();
            //int imageItem = 0; //(int)itemHashMap.get(ICON);
            //Toast.makeText(skl.getApplicationContext(),
            //        "Selected " + titleItem + ".  " + descriptionItem, Toast.LENGTH_SHORT).show();
        }
    };*/


    public class Contact extends HashMap<String, String> {
        public static final String NAME = "name";
        public static final String PHONE = "phone";

        public Contact(String name, String phone) {
            super();
            super.put(NAME, name);
            super.put(PHONE, phone);
        }
    };

    //tells handler to send a message
    class ChatsTask extends TimerTask {
        @Override
        public void run(){
            Log.i("WPS", "TimerTask:run");
            int upd=zparent.GetChatsUpd();
            if(mupd!=upd){
                mupd=upd;
                DrawChats();
            }
        }
    };

    class MessagesTask extends TimerTask{
        int cid;

        MessagesTask(int i){
            cid=i;
        }

        @Override
        public void run(){
            int upd=zparent.GetMessagesUpd(cid);
            if(mupd!=upd){
                mupd=upd;
                DrawChatMessages();
            }
        }
    };


}

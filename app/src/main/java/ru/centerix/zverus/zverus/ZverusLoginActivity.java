package ru.centerix.zverus.zverus;

/**
 * Created by MikelSV on 02.05.2015.
 */

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.view.View.OnClickListener;
import android.util.Log;


public class ZverusLoginActivity {
    private final Context context;
    ZverusActivity zparent;

    public ZverusLoginActivity(Context scontext, ZverusActivity skl){
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

    // load
    void OnLoadLayout(){
        SetLayout(R.layout.load);

        MAutoAsync mt = new MAutoAsync();

        mt.setBg(new MAutoAsyncFunc(){ @Override public void execute(){
            ma.result=zparent.Login();
        }});

        mt.setPost(new MAutoAsyncFunc(){ @Override public void execute(){
            if(ma.result==0)
                zparent.OnLoginLayout();
            else
                zparent.OnChatsLayout();
        }});

        mt.execute();
        return ;
    }

    // login
    int OnLoginLayoutRun = 0;
    void OnLoginLayout(){
        SetLayout(R.layout.login);

        Button auth = (Button) findViewById(R.id.LoginLayout_Auth);
        auth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText login = (EditText) findViewById(R.id.LoginLayout_Login);
                EditText pass = (EditText) findViewById(R.id.LoginLayout_Password);

                if (OnLoginLayoutRun != 0)
                    return;

                OnLoginLayoutRun = 1;

                MAutoAsync mt = new MAutoAsync();
                final int result[] = new int[1];
                final String slogin = login.getText().toString();
                final String spass = pass.getText().toString();

                mt.setPre(new MAutoAsyncFunc() {
                    @Override
                    public void execute() {
                        TextView error = (TextView) findViewById(R.id.LoginLayout_Error);
                        error.setText("Login...");
                    }
                });

                mt.setBg(new MAutoAsyncFunc() {
                    @Override
                    public void execute() {
                        result[0] = zparent.Login(slogin, spass);
                    }
                });

                mt.setPost(new MAutoAsyncFunc() {
                    @Override
                    public void execute() {
                        if (result[0] != 0)
                            zparent.OnChatsLayout();
                        else {
                            String err = zparent.GetLoginError();
                            TextView error = (TextView) findViewById(R.id.LoginLayout_Error);
                            error.setText(err);
                        }
                        OnLoginLayoutRun = 0;
                    }
                });

                mt.execute();
            }
        });

        auth = (Button) findViewById(R.id.LoginLayout_Reg);
        auth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zparent.OnRegisterLayout();
            }
        });

        return;
    }

    // register
    int OnRegisterLayoutRun = 0;

    void OnRegisterLayout(){
        SetLayout(R.layout.register);

        Button btn = (Button) findViewById(R.id.RegisterLayout_Reg);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText login = (EditText) findViewById(R.id.RegisterLayout_Login);
                EditText email = (EditText) findViewById(R.id.RegisterLayout_Email);
                EditText pass = (EditText) findViewById(R.id.RegisterLayout_Password);
                EditText pass2 = (EditText) findViewById(R.id.RegisterLayout_Password2);

                TextView error = (TextView) findViewById(R.id.RegisterLayout_Error);

                if(!pass.getText().toString().equals(pass2.getText().toString())){
                    error.setText("Error: password1 != password2.");
                    return ;
                }

                if(OnRegisterLayoutRun!=0)
                    return;

                OnRegisterLayoutRun=1;

                MAutoAsync mt = new MAutoAsync();
                final int result[] = new int[1];
                final String slogin=login.getText().toString();
                final String semail=email.getText().toString();
                final String spass=pass.getText().toString();

                mt.setPre(new MAutoAsyncFunc(){ @Override public void execute(){
                    TextView error = (TextView) findViewById(R.id.RegisterLayout_Error);
                    error.setText("Register...");
                }
                });

                mt.setBg(new MAutoAsyncFunc(){ @Override public void execute(){
                    result[0]=zparent.Register(slogin, semail, spass);
                }
                });

                mt.setPost(new MAutoAsyncFunc(){ @Override public void execute(){
                    if(result[0]!=0)
                        zparent.OnLoginLayout();
                    else{
                        String err=zparent.GetLoginError();
                        TextView error = (TextView) findViewById(R.id.RegisterLayout_Error);
                        error.setText(err);
                    }
                    OnRegisterLayoutRun=0;
                }
                });

                mt.execute();
            }
        });

        btn = (Button) findViewById(R.id.RegisterLayout_Auth);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                zparent.OnLoginLayout();
            }
        });

        return ;
    }




}

package com.jccy.twoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jccy.aidldemo.IMyAidlInterface;
import com.jccy.aidldemo.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IMyAidlInterface iMyAidlInterface;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(new Intent("com.jccy.aidldemo.service"), mServiceConnection, BIND_AUTO_CREATE);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.d("TwoActviity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
            Log.d("TwoActviity", "onServiceDisconnected");

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                try {
                    if (iMyAidlInterface != null){
                        Toast.makeText(this, iMyAidlInterface.getUser().getName(), Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.add:
                try {
                    if (iMyAidlInterface != null){
                       iMyAidlInterface.addUser(new User("Hyy" + count));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

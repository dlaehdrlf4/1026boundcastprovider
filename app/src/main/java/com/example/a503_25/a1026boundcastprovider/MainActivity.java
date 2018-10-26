package com.example.a503_25.a1026boundcastprovider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //서비스 객체 생성
        myService = new MyService();
        //인텐트 생성 // 뒤에 서비스가 들어가면 일을 하고 브로드 캐스트를 하면 방송을 하는거다.
        //서비스를 쓸수 있는거는 bindService
        //액티비티 호출은 액티비티를 뒤에 쓴다.
        Intent intent = new Intent(this,MyService.class);
        //서비스와 바인드 된 경우와 바인드가 해제 된 경우에
        //호출되는 메소드를 소유한 ServiceConnection 객체 생성
        ServiceConnection myConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyLocalBinder binder = (MyService.MyLocalBinder)service;
                myService = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        //바운드 서비스로 등록
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

      final  TextView textView = (TextView)findViewById(R.id.result);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = myService.remoteMethod();
                textView.setText(result);
            }
        });

    }
}

package com.example.sky.whosfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupPage extends AppCompatActivity implements Writable{
    private InternetConnection asyncTask;
    private GroupPage contesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);
        contesto=this;
        //prendo i vari membri e li butto in un fragment ognuno
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button b=(Button)findViewById(R.id.open_add_member);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddMember.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void writeText(String s) {

    }

    @Override
    public void writeError() {

    }
}

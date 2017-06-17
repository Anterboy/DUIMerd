package com.example.sky.whosfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMember extends AppCompatActivity  implements Writable{
    String groupID;
    private InternetConnection asyncTask;
    private AddMember contesto;
    EditText member_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        contesto = this;

        Bundle extras=getIntent().getExtras();
        groupID = extras.getString("groupID");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Button b=(Button) findViewById(R.id.member_done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member_name=(EditText) findViewById(R.id.member_name);
                asyncTask = new InternetConnection(contesto);
                asyncTask.execute("instruction=add&name="+member_name.getText().toString()+"&gruppo="+groupID);
            }
        });

    }

    @Override
    public void writeText(String s) {
        Log.d("AddMember",s);
        if(s.equals("1")) {  //se i dati sono corretti
            Toast toast = Toast.makeText(getApplicationContext(), "Membro aggiunto", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
            finish();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Errore di inserimento, riprova", Toast.LENGTH_SHORT);
            toast.show();
            member_name.setText("");
            asyncTask.cancel(true);
        }
    }

    @Override
    public void writeError() {

    }
}

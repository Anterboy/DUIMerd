package com.example.sky.whosfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroup extends AppCompatActivity implements Writable{

    String email;
    private InternetConnection asyncTask;
    private AddGroup contesto;
    EditText group_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        contesto = this;

        Bundle extras=getIntent().getExtras();
        email = extras.getString("email");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button b=(Button) findViewById(R.id.group_done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group_name=(EditText) findViewById(R.id.group_name);
                asyncTask = new InternetConnection(contesto);
                asyncTask.execute("instruction=create&name="+group_name.getText().toString()+"&email="+email);
            }
        });

    }

    @Override
    public void writeText(String s) {
        Log.d("AddGroup",s);
        if(s.equals("1")) {  //se i dati sono corretti
            Toast toast = Toast.makeText(getApplicationContext(), "Gruppo creato", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
            finish();
        }
        if(s.equals("2")||s.equals("3")){ //errore
            Toast toast = Toast.makeText(getApplicationContext(), "Errore di creazione, riprova", Toast.LENGTH_SHORT);
            toast.show();
            group_name.setText("");
            asyncTask.cancel(true);
        }
    }

    @Override
    public void writeError() {

    }
}


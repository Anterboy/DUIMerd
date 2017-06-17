package com.example.sky.whosfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements Writable{

    private InternetConnection asyncTask;
    private Login contesto;
    String mailr;
    String passr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contesto = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText et=(EditText) findViewById(R.id.password);
        passr=et.getText().toString();
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                Log.d("my id is",""+actionID);
                if(actionID==6){
                    EditText et2=(EditText) findViewById(R.id.email);
                    mailr=et2.getText().toString();
                    EditText et=(EditText) findViewById(R.id.password);
                    passr=et.getText().toString();
                    asyncTask = new InternetConnection(contesto);
                    asyncTask.execute("instruction=login"+"&email="+mailr+"&password="+passr);
                }
                return false;
            }
        });

        Button b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void writeText(String s) {
        Log.d("LoginTask",s);
        if(s.equals("1")) {  //se i dati sono corretti accedo
            Toast toast = Toast.makeText(getApplicationContext(), "Accesso effettuato", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
            EditText et1=(EditText)findViewById(R.id.email);
            String email=et1.getText().toString();
            Intent intent=new Intent(getApplicationContext(),Home.class);
            intent.putExtra("email",email);
            startActivity(intent);

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Username o password errati", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
        }
    }

    @Override
    public void writeError() {

    }
}

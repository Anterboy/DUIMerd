package com.example.sky.whosfree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements Writable{

    private InternetConnection asyncTask;
    private Register contesto;

    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        contesto = this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        et3=(EditText) findViewById(R.id.editText3);
        et4=(EditText) findViewById(R.id.editText4);
        et5=(EditText) findViewById(R.id.editText5);
        et6=(EditText) findViewById(R.id.editText6);
        et6.setOnKeyListener(new EditText.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER) {
                    if(     (!et3.getText().toString().equals("")&&et3.getText().toString().length()>5)
                            &&(!et4.getText().toString().equals("")&&et4.getText().toString().length()>7
                            &&(et4.getText().toString().equals(et5.getText().toString())))
                            &&(!et6.getText().toString().equals("")&&et6.getText().toString().contains("@"))
                            ) {
                        //inserisco i dati nel DB
                        Log.d("reg","reg");
                        String userr=et3.getText().toString();
                        String emailr=et6.getText().toString();
                        String passr=et4.getText().toString();
                        asyncTask = new InternetConnection(contesto);
                        asyncTask.execute("instruction=register&username="+userr+"&email="+emailr+"&password="+passr);
                    }
                    else{

                        et3.setText("");
                        et4.setText("");
                        et5.setText("");
                        et6.setText("");
                        Toast toast = Toast.makeText(getApplicationContext(), "Dati mancanti o errati", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void writeText(String s) {
        Log.d("RegisterTask",s);
        if(s.equals("1")) {  //se i dati sono corretti
            Toast toast = Toast.makeText(getApplicationContext(), "Registrazione completata", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
            finish();
        }
        if(s.equals("2")||s.equals("3")) {  //se i dati sono errati
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Errore di inserimento", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
        }
        if(s.equals("4")) {  //se l'username è gia presente
            et3.setText("");
            et4.setText("");
            et5.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Username gia in uso", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
        }
        if(s.equals("5")) {  //se l'email è gia presente
            et4.setText("");
            et5.setText("");
            et6.setText("");
            Toast toast = Toast.makeText(getApplicationContext(), "Email gia in uso", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
        }

    }

    @Override
    public void writeError() {

    }
}

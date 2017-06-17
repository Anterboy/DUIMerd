package com.example.sky.whosfree;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyTable extends AppCompatActivity implements Writable{

    private InternetConnection asyncTask;
    private ModifyTable contesto;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_table);

        Bundle extras=getIntent().getExtras();
        email = extras.getString("email");
        contesto=this;

        Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.spinner6);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.spinner7);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.spinner8);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.spinner9);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.user_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button b=(Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner morning=(Spinner)findViewById(R.id.spinner5);
                Spinner lunch=(Spinner)findViewById(R.id.spinner6);
                Spinner afternoon=(Spinner)findViewById(R.id.spinner7);
                Spinner dinner=(Spinner)findViewById(R.id.spinner8);
                Spinner evening=(Spinner)findViewById(R.id.spinner9);


                asyncTask = new InternetConnection(contesto);
                asyncTask.execute("instruction=alter&email="+email+"&morning="+morning.getSelectedItem().toString()+"&lunch="+lunch.getSelectedItem().toString()+"&afternoon="+afternoon.getSelectedItem().toString()+"&dinner="+dinner.getSelectedItem().toString()+"&evening="+evening.getSelectedItem().toString());

            }
        });
    }

    @Override
    public void writeText(String s) {
        Log.d("ModifyTable",s);
        if(s.equals("1")){
            Toast toast = Toast.makeText(getApplicationContext(), "Tabella aggiornata", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
            this.finish();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Errore di aggiornamento, riprovare", Toast.LENGTH_SHORT);
            toast.show();
            asyncTask.cancel(true);
        }
    }

    @Override
    public void writeError() {

    }
}

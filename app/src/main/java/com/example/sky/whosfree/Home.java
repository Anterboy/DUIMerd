package com.example.sky.whosfree;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Writable {

    private InternetConnection asyncTask;
    private Home contesto;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras=getIntent().getExtras();
        email = extras.getString("email");

        contesto = this;



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        asyncTask = new InternetConnection(contesto);
        asyncTask.execute("instruction=get_table"+"&email="+email);
        Button b=(Button)findViewById(R.id.open_modify_table);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ModifyTable.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        Button b1=(Button) findViewById(R.id.create_group);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddGroup.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        TextView tv=(TextView) findViewById(R.id.antonio);
        tv.setText(email);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            finish();
            startActivity(getIntent());
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void writeText(String s) {
        Log.d("addTask",s);
        if(s.contains("MemberTable")){
            String[] momenti=s.split(":");
            TextView t;
            //mattina
            if(momenti[1].equals("0")){ //occupato X
                t=(TextView) findViewById(R.id.textView15);
                t.setText("X");
            }
            if(momenti[1].equals("1")){ //libero V
                t=(TextView) findViewById(R.id.textView15);
                t.setText("V");
            }
            if(momenti[1].equals("2")){ //non so U
                t=(TextView) findViewById(R.id.textView15);
                t.setText("U");
            }
            //pranzo
            if(momenti[2].equals("0")){ //occupato X
                t=(TextView) findViewById(R.id.textView16);
                t.setText("X");
            }
            if(momenti[2].equals("1")){ //libero V
                t=(TextView) findViewById(R.id.textView16);
                t.setText("V");
            }
            if(momenti[2].equals("2")){ //non so U
                t=(TextView) findViewById(R.id.textView16);
                t.setText("U");
            }
            //pomeriggio
            if(momenti[3].equals("0")){ //occupato X
                t=(TextView) findViewById(R.id.textView17);
                t.setText("X");
            }
            if(momenti[3].equals("1")){ //libero V
                t=(TextView) findViewById(R.id.textView17);
                t.setText("V");
            }
            if(momenti[3].equals("2")){ //non so U
                t=(TextView) findViewById(R.id.textView17);
                t.setText("U");
            }
            //cena
            if(momenti[4].equals("0")){ //occupato X
                t=(TextView) findViewById(R.id.textView18);
                t.setText("X");
            }
            if(momenti[4].equals("1")){ //libero V
                t=(TextView) findViewById(R.id.textView18);
                t.setText("V");
            }
            if(momenti[4].equals("2")){ //non so U
                t=(TextView) findViewById(R.id.textView18);
                t.setText("U");
            }
            //sera
            if(momenti[5].equals("0")){ //occupato X
                t=(TextView) findViewById(R.id.textView19);
                t.setText("X");
            }
            if(momenti[5].equals("1")){ //libero V
                t=(TextView) findViewById(R.id.textView19);
                t.setText("V");
            }
            if(momenti[5].equals("2")){ //non so U
                t=(TextView) findViewById(R.id.textView19);
                t.setText("U");
            }

        }
        if(s.contains("Groups")){
            String gruppi[]=s.split(":");
            //assegno a vari fragment i diversi nomi e ID
        }
    }

    @Override
    public void writeError() {

    }
}

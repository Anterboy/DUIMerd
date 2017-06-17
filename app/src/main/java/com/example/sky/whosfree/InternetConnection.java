package com.example.sky.whosfree;

/**
 * Created by CX4_s on 15/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


    public class InternetConnection  extends AsyncTask<String, Void, String> {
        private final Writable activity;

        public InternetConnection(Writable activity) {
            this.activity = activity;
        }

        protected String doInBackground(String... params) {
            String ritorno = null;
            while (!this.isCancelled()) {
                ArrayList<String> strings = new ArrayList<String>();
                ritorno = "";
                try {
                    String u = "http://cassiani.altervista.org/server.php?";
                    Log.i("nemo", "doInBackground: " + u + params[0]);
                    URL url = new URL(u);
                    URLConnection connection = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;


        /*
            Invio Dati tramite POST
        */
                    byte[] postData = params[0].getBytes();
                    httpConnection.setDoOutput(true);
                    httpConnection.setInstanceFollowRedirects(false);
                    httpConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
                    httpConnection.setUseCaches(false);
                    OutputStream wr = httpConnection.getOutputStream();
                    wr.write(postData);


                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream in = httpConnection.getInputStream();
                        BufferedReader d = new BufferedReader(new InputStreamReader(in));
                        String res = d.readLine();
                        while (res != null) {
                            strings.add(res);

                            res = d.readLine();
                        }
                    } else strings.add("NO HTTP_OK");
                } catch (MalformedURLException e) {
                    strings.add(e.getMessage());
                } catch (IOException e) {
                    strings.add("NO INTERNET");
                }
                if (strings.size() > 5) strings.remove(0);
                for (String temp : strings) {
                    ritorno += temp;
                }
                return ritorno;
            }
            return ritorno;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String s) {
            if (s.equals("NO INTERNET"))
                activity.writeError();
            else
                activity.writeText(s);
        }

    }


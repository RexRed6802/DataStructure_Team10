package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button button;
    EditText editText;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText("你好");
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                this.onTap(String.valueOf(editText.getText()));
            }
            public void onTap(String s) {
                Runnable r = new Search(s);
                new Thread(r).start();
                textView = findViewById(R.id.textView);
                textView.setText(((Search) r).result);
                /*
                Thread thread = new Thread(new Runnable(){

                    @override
                    public void run() {
                        s = Search.searchKeyword(s);
                        textView = findViewById(R.id.textView);
                        textView.setText(s);
                    }
                });

                //s = Search.searchKeyword(s);
            }
        });
*/
    }
    public void onClick(View view){
        editText = findViewById(R.id.editTextTextPersonName);
        new Search().execute("https://mao-code.github.io", String.valueOf(editText.getText()));

    }

    private class Search extends AsyncTask<String,Integer,String>{

       // private String content;
      //  private String keyword = "fang";

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
                URLConnection conn = null;
                conn = url.openConnection();
                InputStream in = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String retVal = new String("");

                String line = null;

                while ((line = br.readLine()) != null) {
                    retVal = retVal + line + "\n";
                }

                String content = retVal.toUpperCase();
                String keyword = strings[1].toUpperCase();

                int retVal2 = 0;

                while(content.indexOf(keyword) != -1) {
                    content = content.substring(content.indexOf(keyword) + keyword.length() -1);
                    retVal2++;
                }

                return String.valueOf(retVal2);
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
                textView = findViewById(R.id.textView);
                textView.setText(s);
        }
    }

}
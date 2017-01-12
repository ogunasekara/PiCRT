package com.ogdev.picrt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null){
            if ("text/plain".equals(type)){
                handleSendText(intent);
            }
        }
    }

    void handleSendText(Intent intent){
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);

        // find portion after http
        int position = sharedText.indexOf("http");

        // determine whether youtube/twitch/openrec
        if (sharedText.substring(position + 8,position + 16).equals("youtu.be")) {                 // https://youtu.be/#######
            // do request
            sendYoutubeRequest(sharedText.substring(position + 17));
        } else if (sharedText.substring(position + 7,position + 20).equals("www.twitch.tv")) {      // http://www.twitch.tv/id?sr=a
            // twitch request
            sendTwitchRequest(sharedText.substring(position + 21, sharedText.length() - 5));
        }
    }

    void sendYoutubeRequest(String sentText){

        final TextView mTextView = (TextView) findViewById(R.id.boop);
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://www.google.com";

        String url = "http://192.168.1.16:3000/youtube/"+sentText;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText(R.string.response_rec);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void sendTwitchRequest (String sentText){
        final TextView mTextView = (TextView) findViewById(R.id.boop);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.16:3000/twitch/"+sentText;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText(R.string.response_rec);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

package com.example.tkkil.demojson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String sinhvien_URL = "http://192.168.100.4:81/demojson/sinhvien.php";
    private static final String themsinhvien_URL = "http://192.168.100.4:81/demojson/themsinhvien.php";
    private static final String TAG = "Volley";
    String name = "";
    RequestQueue queue;
    StringRequest stringRequest;
    int age = 0;

    /*   class demoJSON extends AsyncTask<String,Integer,String>{
           @Override
           protected void onPreExecute() {
               super.onPreExecute();
           }

           @Override
           protected String doInBackground(String... strings) {
               return docnoidung(strings[0]);
           }

           @Override
           protected void onPostExecute(String response) {
               super.onPostExecute(response);
   //            Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
   //            textView.append(s + "\n");
               int id = 0;
               String name = "";
               int age = 0;
               String gender = "";
               try {
                   JSONArray jsonArray = new JSONArray(response);
                   for(int i=0; i<jsonArray.length(); i++){
                       JSONObject sinhvien = jsonArray.getJSONObject(i);
                       id = sinhvien.getInt("id");
                       name = sinhvien.getString("name");
                       age = sinhvien.getInt("age");
                       gender = sinhvien.getString("gender");
                       textViewId.append(String.valueOf(id) + "\n");
                       textViewName.append(name + "\n");
                       textViewAge.append(String.valueOf(age) + "\n");
                       textViewGender.append(gender + "\n");
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }

       private String docnoidung(String string) {
           StringBuilder sb = new StringBuilder();
           try {
               URL url = new URL(string);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               InputStream is = connection.getInputStream();
               InputStreamReader isr = new InputStreamReader(is);
               BufferedReader br = new BufferedReader(isr);
               String line = "";
               while ((line = br.readLine()) != null) {
                   sb.append(line);
               }
               br.close();
           } catch (java.io.IOException e) {
               e.printStackTrace();
           }
           return sb.toString();
       }*/
    String gender = "";
    private TextView textViewId, textViewName, textViewAge, textViewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* textViewId = (TextView) findViewById(R.id.textViewId);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewGender = (TextView) findViewById(R.id.textViewGender);*/
        getData();
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new demoJSON().execute("http://192.168.100.4:81/demojson/sinhvien.php");
            }
        });*/

        stringRequest.setTag(TAG);
    }
    //Send data thi ghi Request.Method.POST
    //Get data thi ghi Request.Method.GET
    private void getData() {
        queue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.POST, themsinhvien_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(MainActivity.this, "Post data successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Post fail!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", "Be Linh");
                map.put("age", String.valueOf(21));
                map.put("gender", "Female");
                return map;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(queue != null){
            queue.cancelAll(TAG);
        }
    }
}

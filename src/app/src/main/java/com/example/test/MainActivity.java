package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tab={"0","1","2","3","4","5","6","7","8","9","10","11","12","13"};
    private TextView mTextViewResult;
    private TextView TextViewTest;
    private TextView IbanText;
    private RequestQueue mQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_view_result);
        TextViewTest=findViewById(R.id.text_view2);
        IbanText=findViewById(R.id.ibanText);
        Button buttonParse = findViewById(R.id.button_parse);
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, tab);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonParse();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if (ConnectivityHelper.isConnectedToNetwork(getApplicationContext())) {
            mTextViewResult.setText("");
            TextViewTest.setText("");
            IbanText.setText("");
            jsonParse(position);
        }else {
            Toast.makeText(getApplicationContext(),
                    "No connection, retry later", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void jsonParse(int position) {
        String position2=  String.valueOf(position);
        final String API_BASE = "" + getSecureBaseUrl();
        String url = API_BASE+position2;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id");
                            String accountName = response.getString("account_name");
                            String amount = response.getString("amount");
                            String iban = response.getString("iban");
                            String currency = response.getString("currency");
                            mTextViewResult.append(accountName);
                            TextViewTest.append(amount+" "+ currency);
                            IbanText.append(iban);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    // load c++ library
    static {
        System.loadLibrary("native-lib");
    }

    public static native String baseUrlFromJNI();

    // decode base64 to a string and get normal url
    public static String getSecureBaseUrl() {
        String mUrl = baseUrlFromJNI();
        try {
            String text = new String(Base64.decode(mUrl, Base64.DEFAULT), "UTF-8");
            return text;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mUrl;
    }

    public static class ConnectivityHelper {
        public static boolean isConnectedToNetwork(Context context) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            boolean isConnected = false;
            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
            }
            return isConnected;
        }
    }
}


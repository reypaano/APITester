package com.example.reyanthonypaano.apitester.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reyanthonypaano.apitester.R;
import com.example.reyanthonypaano.apitester.model.Data;
import com.example.reyanthonypaano.apitester.model.User;
import com.example.reyanthonypaano.apitester.model.UserAuth;
import com.example.reyanthonypaano.apitester.rest.APIClient;
import com.example.reyanthonypaano.apitester.rest.config.Constants;
import com.example.reyanthonypaano.apitester.rest.endpoint.AuthService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String API_KEY = "";

    private String toastMessage = "";
    private String username;
    private String password
            ;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        pref = getSharedPreferences("user_details", MODE_PRIVATE);


        if(API_KEY.isEmpty()) {
            toastMessage = "Obtaining API key...";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

            AuthService authService = APIClient.createService(AuthService.class, Constants.API_CLIENT_ID, Constants.API_CLIENT_SECRET);

            Call<Data> call = authService.postAuthentication(username, password);

            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    Log.d(TAG, response.toString());
                    TextView test = (TextView) findViewById(R.id.helloWorld);
                    test.setText(response.body().getAccess_token().toString());
//
//                    if (!response.body().getAccess_token().isEmpty()){
//
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.putString("access_token",response.body().getAccess_token());
//                        editor.commit();
//                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(MainActivity.this, DetailsActivity.class);
//                        startActivity(intent);
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                    toastMessage = "Successfully obtained API key...";
//                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.e(TAG, t.toString());

                    toastMessage = "Failed to obtain API key...";
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(this, new  Data().getAccess_token(), Toast.LENGTH_SHORT).show();

            return;
        }
    }
}

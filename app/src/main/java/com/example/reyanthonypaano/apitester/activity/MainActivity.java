package com.example.reyanthonypaano.apitester.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reyanthonypaano.apitester.R;
import com.example.reyanthonypaano.apitester.model.UserAuth;
import com.example.reyanthonypaano.apitester.rest.APIClient;
import com.example.reyanthonypaano.apitester.rest.AuthInterceptor;
import com.example.reyanthonypaano.apitester.rest.endpoint.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String API_KEY = "";

    private String toastMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY.isEmpty()) {
            toastMessage = "Obtaining API key...";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

            AuthService authService = APIClient.createService(AuthService.class, "ec_client", "ec_secret");

            Call<UserAuth> call = authService.postAuthentication("arponce", "123");
            call.enqueue(new Callback<UserAuth>() {
                @Override
                public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                    Log.d(TAG, response.body().toString());
                    TextView test = (TextView) findViewById(R.id.helloWorld);
                    test.setText(response.body().toString());
                }

                @Override
                public void onFailure(Call<UserAuth> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });

            return;
        }
    }
}

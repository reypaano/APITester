package com.example.reyanthonypaano.apitester.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reyanthonypaano.apitester.R;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity{

    SharedPreferences prf;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        TextView result = (TextView) findViewById(R.id.resultView);
        Button btnLogOut = (Button) findViewById(R.id.btnLogOut);
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(DetailsActivity.this, MainActivity.class);
        result.setText("Your access token is: " + prf.getString("access_token", null));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
    }
}

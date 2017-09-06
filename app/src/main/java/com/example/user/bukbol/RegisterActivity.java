package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.upload.PersonAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;

    @BindView(R.id.register_fullname) EditText fullnameET;
    @BindView(R.id.register_username) EditText emailET;
    @BindView(R.id.register_password) EditText passwordET;
    @BindView(R.id.register_confirm) EditText confirmET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        registerButton = (Button) findViewById(R.id.register_button);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);

                if (isregisterValid()) {
                    uploadPerson(fullnameET,emailET,passwordET);
                    Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });
    }

    private void uploadPerson(EditText fullname, EditText username, EditText password) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ApiClient.BASE_URL)
                .build();
        PersonAPI api = adapter.create(PersonAPI.class);
        api.insert(username.getText().toString(), fullname.getText().toString() , "", "", "", password.getText().toString(), "",
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader;
                        String output = "";
                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(),"GAGAL", Toast.LENGTH_LONG).show();
                        Log.e("TEST", error.toString());
                    }
                }
        );
    }

    private boolean isregisterValid(){

        boolean isFilled;
        boolean isMatch;

        isFilled= (!fullnameET.getText().toString().isEmpty())&&(!emailET.getText().toString().isEmpty())&&(!passwordET.getText().toString().isEmpty())&&(!confirmET.getText().toString().isEmpty());
        isMatch = (passwordET.getText().toString().equals(confirmET.getText().toString()));

        return (isFilled&&isMatch);
    }

}

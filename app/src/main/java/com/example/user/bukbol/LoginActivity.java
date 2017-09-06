package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.API.ApiInterface;
import com.example.user.bukbol.API.Session;
import com.example.user.bukbol.data.PersonDataset;
import com.example.user.bukbol.data.PersonModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private TextView createAccountTV;
    private Button loginButton;

     EditText usernameField;
    EditText passwordField;
    private LinearLayout createAccountLayout;
    private List<PersonDataset> persons;
    private PersonDataset currentSession;
    private boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        isSuccess=false;

        usernameField = (EditText) findViewById(R.id.login_email_edit_text);
        passwordField = (EditText) findViewById(R.id.login_password_edit_text);
        createAccountTV = (TextView) findViewById(R.id.login_create_account_tv);
        loginButton = (Button) findViewById(R.id.login_button);

        createAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUsernameData();

            }

        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        isSuccess=false;
    }

    private void loadUsernameData(){
        ApiClient apiClient = new ApiClient();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Call<PersonModel> personCall = apiInterface.getUsernames("");
        personCall.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                persons = response.body().getPersonDataset();
                isSuccess = validateLogin(usernameField,passwordField,persons);
                if (isSuccess) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    Session.user = currentSession;
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Username and Password doesn't match.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {

            }
        });
    }

    private boolean validateLogin(EditText usernameField, EditText passwordField,List<PersonDataset> persons){
        String usernameText = usernameField.getText().toString();
        String passwordText = passwordField.getText().toString();

        for (int i=0;i<persons.size();i++){
            if ((usernameText.equals(persons.get(i).getUsername()))&&(passwordText.equals(persons.get(i).getPassword()))){
                currentSession=persons.get(i);
                return true;
            }
        }
     return false;
    }

}

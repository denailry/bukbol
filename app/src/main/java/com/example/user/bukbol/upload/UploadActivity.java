package com.example.user.bukbol.upload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.bukbol.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UploadActivity extends AppCompatActivity {

    private final String URL_ROOT = "http://rupy.levyson.com/dummy/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void uploadBook() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(URL_ROOT)
                .build();
        BookAPI api = adapter.create(BookAPI.class);
        api.insert(140L, 200L, "400L", "101010", "1900", 25000, 1234, 400, 500, 0,
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

    private void uploadPlace() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(URL_ROOT)
                .build();
        PlaceAPI api = adapter.create(PlaceAPI.class);
        api.insert(100L, "NAMA", "TUBIS", 12, 23, "DESKRIPSI",
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

    private void uploadField() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(URL_ROOT)
                .build();
        FieldAPI api = adapter.create(FieldAPI.class);
        api.insert(100L, 200L, "NAMA", "DESKRIPSI", 3,
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

    private void uploadPerson() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(URL_ROOT)
                .build();
        PersonAPI api = adapter.create(PersonAPI.class);
        api.insert("abc123", "NAMA", "USER@EMAIL.COM", "0808", "DAGO", "PASS", "060101",
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

    public void onBook(View view) {
        uploadBook();
    }
    public void onPlace(View view) {
        uploadPlace();
    }
    public void onField(View view) {
        uploadField();
    }
    public void onPerson(View view) {
        uploadPerson();
    }

}

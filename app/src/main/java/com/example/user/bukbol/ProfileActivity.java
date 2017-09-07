package com.example.user.bukbol;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.user.bukbol.API.ApiConfig;
import com.example.user.bukbol.API.AppConfig;
import com.example.user.bukbol.API.ServerResponse;
import com.example.user.bukbol.API.Session;
import com.example.user.bukbol.adapter.ProfileAdapter;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ProfileItem> profileItems;
    private ProfileAdapter adapter;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_email)
    TextView profileEmail;
    @BindView(R.id.profile_notelp)
    TextView profilePhone;
    @BindView(R.id.profile_avatar)
    ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        profileName.setText(Session.user.getName());
        profileEmail.setText(Session.user.getEmail());
        profilePhone.setText(Session.user.getPhone());


       initProfileList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProfileItem currentItem = profileItems.get(i);
                Intent intent = new Intent(ProfileActivity.this, DetailProfileActivity.class);
                intent.putExtra("profile", currentItem.getItemName());
                startActivity(intent);
            }
        });
    }

    private void initProfileList(){
        profileItems = new ArrayList<>();
        profileItems.add(new ProfileItem("Account Settings", R.drawable.ic_account));
        profileItems.add(new ProfileItem("Favorites", R.drawable.ic_favorite));
        profileItems.add(new ProfileItem("FAQ", R.drawable.ic_help));
        profileItems.add(new ProfileItem("Notifications", R.drawable.ic_notification));

        adapter = new ProfileAdapter(this, profileItems);

        listView = (ListView) findViewById(R.id.profile_lv);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.profile_avatar)
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ivProfile.setImageURI(uri);
            try {
                String path = UriManip.getFilePath(getApplicationContext(), uri);
                uploadFile(path);
            } catch (URISyntaxException e) {
                Log.e("TEST", "ERROR 1");
            }

        }
    }

    private void uploadFile(String path) {
        // Map is used to multipart the file using okhttp3.RequestBody

        File file = new File(path);
        String newName = Session.user.getUsername() + getFileExtension(file.getName());

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), newName);

        ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
        Call<ServerResponse> call = getResponse.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse != null) {
                    if(serverResponse.getMessage() != null) {
                        Log.d("TEST", serverResponse.getMessage());
                    } else {
                        Log.d("TEST", "NO MESSAGE");
                    }

                    if (serverResponse.getSuccess()) {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"NULL",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(),"FAIL",Toast.LENGTH_SHORT).show();
                Log.e("TEST", t.toString());
            }
        });
    }

    private String getFileExtension(String fileName) {
        String extension = "";
        boolean found = false;
        int i = fileName.length()-1;
        while(!found && i >= 0) {
            found = (fileName.charAt(i) == '.');
            extension = String.valueOf(fileName.charAt(i) + extension);
            i--;
        }

        return extension;
    }

    @Override
    protected void onResume() {

        super.onResume();

        profileName.setText(Session.user.getName());
        profileEmail.setText(Session.user.getEmail());
        profilePhone.setText(Session.user.getPhone());


    }
}
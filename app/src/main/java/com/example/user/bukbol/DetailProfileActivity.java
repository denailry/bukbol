package com.example.user.bukbol;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.bukbol.adapter.BookingCardAdapter;
import com.example.user.bukbol.data.TempatFutsal;
import com.example.user.bukbol.listener.BookingListener;

import java.util.ArrayList;

public class DetailProfileActivity extends AppCompatActivity implements BookingListener{

    private ArrayList<String> notifications;
    private ListView notificationLV;
    private ArrayAdapter<String> notificationAdapter;
    private android.widget.Toolbar toolbar;

    //Favorite
    BookingCardAdapter adapter;
    RecyclerView favoriteRV;
    ArrayList<TempatFutsal> listTempatFutsal = new ArrayList<>();

    //Account setting
    private Button changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent prevIntent = getIntent();
        String profileType = prevIntent.getStringExtra("profile");


        notifications = new ArrayList<>();

        switch (profileType){
            case "FAQ":{
                setContentView(R.layout.profile_faq);
                break;
            }
            case "Account Settings":{
                setContentView(R.layout.profile_account);
                initChangePassword();
                break;
            }
            case "Notifications":{
                setContentView(R.layout.profile_notification);
                notificationLV = (ListView) findViewById(R.id.notification_lv);
                initNotifications();
                break;
            }
            case "Favorites":{
                setContentView(R.layout.profile_favorite);
                favoriteRV = (RecyclerView) findViewById(R.id.favorite_rv);
                initFavoriteAdapter();
                break;
            }
        }

        toolbar = (android.widget.Toolbar) findViewById(R.id.profile_toolbar);
        toolbar.requestFocus();
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }


    private void initNotifications(){
        notifications.add("Selamat");
        notifications.add("Selamat");
        notifications.add("Selamat");
        notifications.add("Selamat");

        notificationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notifications);

        notificationLV.setAdapter(notificationAdapter);
    }

    private void initFavoriteAdapter(){
        favoriteRV.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        favoriteRV.setLayoutManager(llm);

        adapter = new BookingCardAdapter(listTempatFutsal, this);
        favoriteRV.setAdapter(adapter);
        callDataFavoriteBooking();
    }

    private void callDataFavoriteBooking() {
        listTempatFutsal.add(new TempatFutsal("Saraga", "jalan ganesha","08.00 - 09.00","Rp 30.000 - 50.000/hour",R.drawable.tes));
        listTempatFutsal.add(new TempatFutsal("Saraga", "jalan ganesha","08.00 - 09.00","Rp 30.000 - 50.000/hour",R.drawable.tes));
        listTempatFutsal.add(new TempatFutsal("Saraga", "jalan ganesha","08.00 - 09.00","Rp 30.000 - 50.000/hour",R.drawable.tes));
        listTempatFutsal.add(new TempatFutsal("Saraga", "jalan ganesha","08.00 - 09.00","Rp 30.000 - 50.000/hour",R.drawable.tes));

        adapter.refreshData(listTempatFutsal);
    }


    @Override
    public void onCardClicked(TempatFutsal tempatFutsal) {

    }

    private void initChangePassword(){
        changePasswordButton = (Button) findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout changePassword = (LinearLayout) findViewById(R.id.change_password);

                if (changePassword.getVisibility()==View.GONE){
                    changePassword.setVisibility(View.VISIBLE);
                }
                else {
                    changePassword.setVisibility(View.GONE);
                }

                Button validateChange = (Button) findViewById(R.id.change_password_dialog_button);
                validateChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final EditText newPassword = (EditText) findViewById(R.id.change_password_new);
                        final EditText confirmPassword = (EditText) findViewById(R.id.change_password_confirm);
                        if ((newPassword.getText().toString().equals(confirmPassword.getText().toString()))){
                            Toast.makeText(DetailProfileActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                            changePassword.setVisibility(View.GONE);
                        }
                        else {
                            Toast.makeText(DetailProfileActivity.this, "Please check again.", Toast.LENGTH_SHORT).show();
                            Toast.makeText(DetailProfileActivity.this, confirmPassword.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });






            }
        });

    }

}

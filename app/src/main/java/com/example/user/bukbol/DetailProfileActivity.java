package com.example.user.bukbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.API.Session;
import com.example.user.bukbol.adapter.BookingCardAdapter;
import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.PersonDataset;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.listener.BookingListener;
import com.example.user.bukbol.upload.PersonAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailProfileActivity extends AppCompatActivity implements BookingListener{

    private ArrayList<String> notifications;
    private ListView notificationLV;
    private ArrayAdapter<String> notificationAdapter;
    private android.widget.Toolbar toolbar;

    //Favorite
    BookingCardAdapter adapter;
    RecyclerView favoriteRV;
    ArrayList<PlaceDataset> listTempatFutsal = new ArrayList<>();

    //Account setting
    @BindView(R.id.account_username) TextView usernameTV;
    @BindView(R.id.account_email) TextView emailTV;
    @BindView(R.id.account_ttl) EditText ttlTV;
    @BindView(R.id.account_telp) EditText telpTV;
    @BindView(R.id.account_alamat) EditText alamatTV;
    @BindView(R.id.account_fullname) EditText fullnameTV;
    @BindView(R.id.account_save_button) Button accountSaveButton;

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
                initHelp();
                break;
            }
            case "Account Settings":{
                setContentView(R.layout.profile_account);
                ButterKnife.bind(this);
                initAccountSettings();
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
        PlaceDataset tim = new PlaceDataset(1,"Futsal Town","Jalan bagusrangin", 8,21,"aplikasi baik",20000, 40000);
        listTempatFutsal.add(tim);
        listTempatFutsal.add(tim);
        listTempatFutsal.add(tim);
        adapter.refreshData(listTempatFutsal);
    }


    @Override
    public void onCardClicked(PlaceDataset tempatFutsal) {
        Intent intent = new Intent(this, TempatFutsalDetailActivity.class);
        intent.putExtra("namaTempat",tempatFutsal.getName());
        intent.putExtra("alamat",tempatFutsal.getAddress());
      //  intent.putExtra("jam",ubahJam(tempatFutsal.getOpenHour())+" - "+ubahJam(tempatFutsal.getCloseHour()));
        intent.putExtra("deskripsi",tempatFutsal.getDescription());
        intent.putExtra("id",tempatFutsal.getId());
        startActivity(intent);
    }

    @Override
    public void onCardClicked(BookDataset bookDataset) {

    }

    private void initAccountSettings(){
        fullnameTV.setText(Session.user.getName());
        ttlTV.setText(Session.user.getDate());
        alamatTV.setText(Session.user.getAddress());
        usernameTV.setText(Session.user.getUsername());
        emailTV.setText(Session.user.getEmail());
        telpTV.setText(Session.user.getPhone());

        accountSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTV.getText().toString();
                String fullname = fullnameTV.getText().toString();
                String email = emailTV.getText().toString();
                String telp = telpTV.getText().toString();
                String alamat = alamatTV.getText().toString();
                String ttl = ttlTV.getText().toString();

                uploadPerson(Session.user.getUsername(), fullname, email, telp, ttl, Session.user.getPassword(), alamat);
                Session.user = new PersonDataset(username, fullname, email, telp, ttl, Session.user.getPassword(), alamat);
                Toast.makeText(DetailProfileActivity.this, "Details changed.", Toast.LENGTH_SHORT).show();
            }
        });

        changePasswordButton = (Button) findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LinearLayout changePassword = (LinearLayout) findViewById(R.id.change_password);

                if (changePassword.getVisibility() == View.GONE) {
                    changePassword.setVisibility(View.VISIBLE);
                    Button validateChange = (Button) findViewById(R.id.change_password_dialog_button);
                    validateChange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final EditText newPassword = (EditText) findViewById(R.id.change_password_new);
                            final EditText confirmPassword = (EditText) findViewById(R.id.change_password_confirm);
                            final EditText oldPassword = (EditText) findViewById(R.id.change_pssword_old);
                            if ((newPassword.getText().toString().equals(confirmPassword.getText().toString())) && (oldPassword.getText().toString().equals(Session.user.getPassword()))) {
                                Toast.makeText(DetailProfileActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                                changePassword.setVisibility(View.GONE);
                                uploadPerson(Session.user.getUsername(), Session.user.getName(), Session.user.getEmail(), Session.user.getPhone(), Session.user.getAddress(), newPassword.getText().toString(), Session.user.getDate());
                            } else {
                                Toast.makeText(DetailProfileActivity.this, "Please check your password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    changePassword.setVisibility(View.GONE);

                }
            }
        });
    }

    private void initHelp(){
        TextView view = (TextView)findViewById(R.id.tata_cara_TV);

        String formattedText = "Setelah pemesanan,  anda dipersilahkan untuk segera membayar ke rekening <b>BANK AAA XXXXXXXX </b> dengan jumlah pembayaran sesuai dengan harga lapangan ditambah dengan kode unik." +
                "<br>" +
                "<br>" +
                "Kami akan memeriksa status pembayaran Anda setiap hari pada pukul:<br>" +
                "1. 06.00 WIB<br>" +
                "2. 09.00 WIB<br>" +
                "3. 12.00 WIB<br>" +
                "4. 15.00 WIB<br>" +
                "5. 18.00 WIB<br>" +
                "6. 21.00 WIB<br>" +
                "7. 24.00 WIB<br>" +
                "<br>" +
                "Jika Anda melakukan pembayaran setelah lewat tenggat waktu pembayaran dan tidak melewati masa pemeriksaan dari kami, maka pesanan anda akan segera <b>dibatalkan</b>.";

        view.setText(Html.fromHtml(formattedText));
    }

    private void uploadPerson(String username, String fullname, String email, String telp, String ttl, String password, String alamat) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ApiClient.BASE_URL)
                .build();
        PersonAPI api = adapter.create(PersonAPI.class);
        api.insert(username, fullname, email, telp, alamat , password, ttl,
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
}

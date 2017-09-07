package com.example.user.bukbol;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bukbol.API.ApiClient;
import com.example.user.bukbol.API.ApiInterface;
import com.example.user.bukbol.API.Session;
import com.example.user.bukbol.adapter.JamAdapter;
import com.example.user.bukbol.data.BookDataset;
import com.example.user.bukbol.data.BookModel;
import com.example.user.bukbol.data.FieldDataset;
import com.example.user.bukbol.data.FieldModel;
import com.example.user.bukbol.data.PlaceDataset;
import com.example.user.bukbol.data.PlaceModel;
import com.example.user.bukbol.listener.JamListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempatFutsalDetailActivity extends AppCompatActivity implements JamListener{

    String TAG = "LOGGING";

    @BindView(R.id.linear_pgn_booking)
    LinearLayout layoutBooking;
    @BindView(R.id.btn_continue_tempat)
    Button btnContinue;
    @BindView(R.id.btn_book_tempat) Button btnBooking;
    @BindView(R.id.txt_nama_tempat)
    TextView txtNamaTempat;
    @BindView(R.id.alamat_tempat) TextView txtAlamat;
    @BindView(R.id.txt_jam_tempat) TextView txtJam;
    @BindView(R.id.txt_deskripsi_tempat) TextView txtDeskripsiTempat;
    @BindView(R.id.txt_detail_lapangan_tempat) TextView txtDetailLapangan;
    @BindView(R.id.txt_tanggal_tempat) TextView txtTanggal;
    @BindView(R.id.location_tempat)
    ImageView imgLocation;
    @BindView(R.id.img_date_tempat) ImageView imgDate;
    @BindView(R.id.spinner_lapangan_tempat)
    Spinner spinnerLapangan;
    @BindView(R.id.rv_jam_tempat)
    RecyclerView rvJam;

    ArrayList<FieldDataset> listLapangan = new ArrayList<>();

    ArrayList<Boolean> listBool = new ArrayList<>();

    int thisJam = -1;

    Calendar calendar = Calendar.getInstance();
    int year= calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    int idxSpinnerLapangan = 0;

    long id;

    String lapangan;

    private List<FieldDataset> list;

    ArrayList<BookDataset> listBook = new ArrayList<>();

    ArrayList<Integer> listJam = new ArrayList<>();

    JamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_futsal_detail);

        ButterKnife.bind(this);

        txtTanggal.setText(day+" - "+(month+1)+" - "+year);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("namaTempat");
        txtNamaTempat.setText(nama);

        String alamat = intent.getStringExtra("alamat");
        txtAlamat.setText(alamat);

        String jamBuka = intent.getStringExtra("jam");
        Log.d(TAG, "onCreate: "+jamBuka);
        txtJam.setText(jamBuka);

        id = intent.getLongExtra("id",0);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<FieldModel> call = service.getFields(id);
        call.enqueue(new Callback<FieldModel>() {
            @Override
            public void onResponse(Call<FieldModel> call, Response<FieldModel> response) {
                list = response.body().getFieldDataset();

                listLapangan = new ArrayList<FieldDataset>();
                if (response.body().getStatus()){
                    for (int i=0; i<list.size(); i++){
                        listLapangan.add(list.get(i));
                    }
                    uploadSpinner();
                }
            }

            @Override
            public void onFailure(Call<FieldModel> call, Throwable t) {
                Toast.makeText(TempatFutsalDetailActivity.this, "can't load data", Toast.LENGTH_SHORT).show();
            }
        });

        rvJam.setHasFixedSize(true);
        rvJam.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        adapter = new JamAdapter(listJam,listBool,this,getApplicationContext(),thisJam);
        rvJam.setAdapter(adapter);
        callJamData();

        String deskripsi = intent.getStringExtra("deskripsi");
        txtDeskripsiTempat.setText(deskripsi);

        layoutBooking.setVisibility(View.GONE);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnContinue.setVisibility(View.GONE);
                layoutBooking.setVisibility(View.VISIBLE);
            }
        });

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog("Futsal Town","6 September 2017");
            }
        });

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });



    }

    private void callJamData() {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PlaceModel> call = service.getPlaces(id);
        call.enqueue(new Callback<PlaceModel>() {
            @Override
            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                List<PlaceDataset> list = response.body().getPlaceDataset();
                int awal = list.get(0).getOpenHour();
                int akhir = list.get(0).getCloseHour();

                for (int i=awal; i<=akhir; i++){
                    listJam.add(i);
                    if (i%2==0){
                        listBool.add(true);
                    }else{
                        listBool.add(false);
                    }
                }
                adapter.refreshData(listJam,listBool);
            }

            @Override
            public void onFailure(Call<PlaceModel> call, Throwable t) {

            }
        });

    }

    private void uploadSpinner() {

        final ArrayList<String> items = new ArrayList<>();
        final ArrayList<String> itemDes = new ArrayList<>();

        for (int i=0; i<listLapangan.size(); i++){
            for (int j=0; j<listLapangan.get(i).getCount(); j++){
                items.add(listLapangan.get(i).getName());
                itemDes.add(listLapangan.get(i).getDescription());
            }
        }

        Log.d(TAG, "uploadSpinner: "+listLapangan.size());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        spinnerLapangan.setAdapter(adapter);

        spinnerLapangan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtDetailLapangan.setText(itemDes.get(i));
                lapangan = items.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    void buildDialog(String namaTempat,String tanggal){
        new AlertDialog.Builder(this)
                .setTitle("Booking Confirmation")
                .setMessage("Do you really want to book "+namaTempat+" at "+tanggal+"?")
                .setIcon(R.drawable.icon)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(getApplicationContext(),ReceiptBookingActivity.class);
                        intent.putExtra("stat",0);
                        startActivity(intent);
                        finish();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    private DatePickerDialog.OnDateSetListener DateTanggalLahirListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            year = i;
            month = i1;
            day = i2;

            txtTanggal.setText(day+" - "+(month+1)+" - "+year);

        }
    };

    //untuk metode memanggil calendar
    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == 999){
            return new DatePickerDialog(this,
                    DateTanggalLahirListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onJamClicked(int x) {
        thisJam = x;
    }
}

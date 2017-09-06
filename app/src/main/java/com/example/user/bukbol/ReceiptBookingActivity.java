 package com.example.user.bukbol;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

 public class ReceiptBookingActivity extends AppCompatActivity {

     int status;

     @BindView(R.id.txt_tenggat_order)
     TextView txtTenggat;
     @BindView(R.id.txt_keterangan_order) TextView txtKeterangan;
     @BindView(R.id.view_tenggat) TextView viewTenggat;
     @BindView(R.id.btn_upload_order)
     Button btnUpload;

     private void setVisibleKeterangan(int v){
         txtTenggat.setVisibility(v);
         txtKeterangan.setVisibility(v);
         viewTenggat.setVisibility(v);
         btnUpload.setVisibility(v);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_booking);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        status = intent.getIntExtra("stat",0);

        if (status == 0){
            new AlertDialog.Builder(this)
                    .setTitle("Pemesanan berhasil")
                    .setMessage("Pemesanan berhasil dilakukan. kamu diharuskan membayar sesuai nominal yang tertera di Detail Orderan dan kamu juga diharuskan membayar sebelum tenggat waktu yang ditentukan. Jika tidak, pemesanan akan otomatis dibatalkan!")
                    .setIcon(R.drawable.icon)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                        }})
                    .show();
            setVisibleKeterangan(View.VISIBLE);
        }else{
            setVisibleKeterangan(View.GONE);
        }
    }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         if (item.getItemId() == android.R.id.home){
             finish();
         }

         return super.onOptionsItemSelected(item);
     }
 }

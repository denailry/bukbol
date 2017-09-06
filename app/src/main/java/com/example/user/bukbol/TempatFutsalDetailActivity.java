package com.example.user.bukbol;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempatFutsalDetailActivity extends AppCompatActivity {

    @BindView(R.id.linear_pgn_booking)
    LinearLayout layoutBooking;
    @BindView(R.id.btn_continue_tempat)
    Button btnContinue;
    @BindView(R.id.btn_book_tempat) Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat_futsal_detail);

        ButterKnife.bind(this);

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
}

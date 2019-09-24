package com.tipcalc.simpletipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    EditText etBill;
    EditText etTip;

    Button btnCalc;

    TextView tvTip;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        etBill = (EditText) findViewById(R.id.billAmount_text);
        etTip = (EditText) findViewById(R.id.tipPercentage_text);

        btnCalc = (Button) findViewById(R.id.buttonCalculate);

        tvTip = (TextView) findViewById(R.id.tv_TipAmount);
        tvTotal = (TextView) findViewById(R.id.tv_Total);

        // Listener
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttononClick(view);
            }
        });

    }
    public void buttononClick(View v) {
        double bill = 0;
        double tipPercent = 0;
        int people = 0;
        double tip = 0;
        double total = 0;

        // check if the fields are empty
        if (TextUtils.isEmpty(etBill.getText().toString())
                || TextUtils.isEmpty(etTip.getText().toString())
        ) {
            return;
        }

        // read EditText and fill variables with numbers
        bill = Float.parseFloat(etBill.getText().toString());
        tipPercent = Float.parseFloat(etTip.getText().toString());

        try {
            tip = getTip(bill, tipPercent);
            total = getTotal(bill, tip);
            total = bill + tip;
            tvTip.setText("Tip Amount: $" + String.format("%.2f", tip));
            tvTotal.setText("Total: $" + String.format("%.2f", total));
        } catch (Exception ex) {  // Catch possible errors
            tvTip.setText("Error: " + ex.getMessage());
            tvTotal.setText("Please try again.");
        }


        tvTip.setText("Tip Amount: $" + String.format("%.2f", tip));
        tvTotal.setText("Total: $" + String.format("%.2f", total));
    }

    double getTip(double bill, double tipPercent) { // Get Tip
        return bill * (tipPercent * .01);
    }
    double getTotal(double bill, double tip) { // Get Total
        return bill + tip;

    }
}

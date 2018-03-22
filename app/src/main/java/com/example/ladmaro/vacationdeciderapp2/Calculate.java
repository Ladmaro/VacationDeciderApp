package com.example.ladmaro.vacationdeciderapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        final TextView landTxt = (TextView)findViewById(R.id.landTxt);
        TextView valutaTxt = (TextView)findViewById(R.id.valutaTxt);
        TextView daysTxt = (TextView)findViewById(R.id.daysTxt);
        final TextView totalTxt = (TextView)findViewById(R.id.totalTxt);
        final NumberPicker daysNum = (NumberPicker)findViewById(R.id.daysNum);


        Intent intent = getIntent();
        final String land = intent.getStringExtra("land");
        final String expend = intent.getStringExtra("expenditure");
        final String fly = intent.getStringExtra("fly");
        final String currency = intent.getStringExtra("currency");

        landTxt.setText(land);
        daysTxt.setText("Number of days(Max 30): ");
        valutaTxt.setText("");


        daysNum.setMinValue(1);
        daysNum.setMaxValue(30);

        daysNum.setWrapSelectorWheel(true);

        if (landTxt.getText().toString().equals("England")) {
            valutaTxt.setText("100 NOK = 9.16 GBP");
        } if (landTxt.getText().toString().equals("Germany")) {
            valutaTxt.setText("100 NOK = 10.35 EUR");
        } if (landTxt.getText().toString().equals("France")) {
            valutaTxt.setText("100 NOK = 10.35 EUR");
        } if (landTxt.getText().toString().equals("Poland")) {
            valutaTxt.setText("100 NOK = 43.33 PLN");
        } if (landTxt.getText().toString().equals("USA")) {
            valutaTxt.setText("100 NOK = 12.70 USD");
        }


        Double expendValue = Double.parseDouble(expend);
        Double flyValue = Double.parseDouble(fly);
        DecimalFormat twoDForm = new DecimalFormat("#.##");


            final Double calculateValue = (expendValue + flyValue);
            final Double calculated = Double.valueOf(twoDForm.format(calculateValue));
            totalTxt.setText("Total cost: " + calculated + " NOK");

            if (currency.equals("EUR")) {


            final Double calculateValueNew = (expendValue + flyValue)/9.63;
            final Double calculatedNew = Double.valueOf(twoDForm.format(calculateValueNew));
            totalTxt.setText("Total cost: " + calculatedNew + " EUR");

        } if (currency.equals("USD")) {

            final Double calculateValueNew = (expendValue + flyValue)/7.85;
            final Double calculatedNew = Double.valueOf(twoDForm.format(calculateValueNew));
            totalTxt.setText("Total cost: " + calculatedNew + " USD");

        }


        daysNum.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int newVal) {
                Double expendValue = Double.parseDouble(expend);
                Double flyValue = Double.parseDouble(fly);
                DecimalFormat twoDecForm = new DecimalFormat("#.##");

                    final Double calculateNewValue = (newVal * expendValue) + flyValue;
                    final Double calculatedTotal = Double.valueOf(twoDecForm.format(calculateNewValue));
                    totalTxt.setText("Total cost: " + calculatedTotal + " NOK");
                  if (currency.equals("EUR")) {
                    final Double calculateNewValueNew = (newVal * expendValue + flyValue)/9.63;
                    final Double calculatedTotalNew = Double.valueOf(twoDecForm.format(calculateNewValueNew));
                    totalTxt.setText("Total cost: " + calculatedTotalNew + " EUR");
                } if (currency.equals("USD")) {
                    final Double calculateNewValueNew = (newVal * expendValue + flyValue)/7.85;
                    final Double calculatedTotalNew = Double.valueOf(twoDecForm.format(calculateNewValueNew));
                    totalTxt.setText("Total cost: " + calculatedTotalNew
                            + " USD");
                }
            }
        });
    }
}

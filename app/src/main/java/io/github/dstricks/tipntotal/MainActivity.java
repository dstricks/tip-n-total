package io.github.dstricks.tipntotal;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int CALCULATION_DELAY_SECS = 1;

    private EditText amount;
    private TextView fifteen;
    private TextView fifteenAmount;
    private TextView eighteen;
    private TextView eighteenAmount;
    private TextView twenty;
    private TextView twentyAmount;

    private Calculator mCalculator = new Calculator();
    private Handler mHandler;

    private Runnable mUpdateCalculations = new Runnable() {
        @Override
        public void run() {
            String amountText = amount.getText().toString();

            try {
                mCalculator.update(Double.parseDouble(amountText));
            } catch (NumberFormatException nfe) {
                mCalculator.update(0);
            }

            fifteen.setText(format(mCalculator.fifteen));
            fifteenAmount.setText(format(mCalculator.fifteenAmount));
            eighteen.setText(format(mCalculator.eighteen));
            eighteenAmount.setText(format(mCalculator.eighteenAmount));
            twenty.setText(format(mCalculator.twenty));
            twentyAmount.setText(format(mCalculator.twentyAmount));
        }
    };

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewHandles();

        mHandler = new Handler();
    }

    private void setViewHandles() {
        amount = (EditText) findViewById(R.id.amount);
        fifteen = (TextView) findViewById(R.id.fifteen);
        fifteenAmount = (TextView) findViewById(R.id.fifteen_amount);
        eighteen = (TextView) findViewById(R.id.eighteen);
        eighteenAmount = (TextView) findViewById(R.id.eighteen_amount);
        twenty = (TextView) findViewById(R.id.twenty);
        twentyAmount = (TextView) findViewById(R.id.twenty_amount);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                mHandler.removeCallbacks(mUpdateCalculations);
                mHandler.postDelayed(mUpdateCalculations, (CALCULATION_DELAY_SECS * 1000));
            }
        });
    }

    private String format(double amount) {
        return String.format("%.2f", amount);
    };
}

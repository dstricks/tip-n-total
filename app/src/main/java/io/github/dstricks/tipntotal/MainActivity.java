package io.github.dstricks.tipntotal;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int CALCULATION_DELAY_SECS = 1;

    @BindView(R.id.amount) EditText amount;
    @BindView(R.id.fifteen) TextView fifteen;
    @BindView(R.id.fifteen_amount) TextView fifteenAmount;
    @BindView(R.id.eighteen) TextView eighteen;
    @BindView(R.id.eighteen_amount) TextView eighteenAmount;
    @BindView(R.id.twenty) TextView twenty;
    @BindView(R.id.twenty_amount) TextView twentyAmount;

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
        ButterKnife.bind(this);

        mHandler = new Handler();
    }

    @OnTextChanged(value = R.id.amount, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED) void onAmountUpdated() {
        mHandler.removeCallbacks(mUpdateCalculations);
        mHandler.postDelayed(mUpdateCalculations, (CALCULATION_DELAY_SECS * 1000));
    }

    private String format(double amount) {
        return String.format("%.2f", amount);
    };
}

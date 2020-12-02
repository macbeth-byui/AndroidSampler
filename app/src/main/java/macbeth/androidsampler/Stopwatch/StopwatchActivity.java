package macbeth.androidsampler.Stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import macbeth.androidsampler.R;

public class StopwatchActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private TextView tvSeconds;
    private long accumulatedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        chronometer = findViewById(R.id.chronometer);
        tvSeconds = findViewById(R.id.tv_seconds_elapsed);

        // It will display current time - base.  By setting base to current time,
        // it will start the display at 0.
        chronometer.setBase(SystemClock.elapsedRealtime());
        tvSeconds.setText("");
        accumulatedTime = 0;

        Button start = findViewById(R.id.b_start);
        Button stop = findViewById(R.id.b_stop);
        Button reset = findViewById(R.id.b_reset);

        start.setOnClickListener((view) -> {
            // Remember, it will display current time - base.  In this case, base is
            // current time - accumulated time.  So the display will be:
            // current time - (current time - accumulated time) = accumulated time (which is what we want)
            chronometer.setBase(SystemClock.elapsedRealtime() - accumulatedTime);
            chronometer.start();
        });

        stop.setOnClickListener((view) -> {
            chronometer.stop();
            // I don't want to deal in fractional seconds because this will mess up the the display
            // versus accumulated time ... it might show 5 seconds on clock but accumulated will be 6 and change.
            accumulatedTime = Math.floorDiv(SystemClock.elapsedRealtime() - chronometer.getBase(), 1000) * 1000;
            tvSeconds.setText(String.valueOf(accumulatedTime/1000.0));  // ms -> sec
        });

        reset.setOnClickListener((view) -> {
            // Start over again
            chronometer.setBase(SystemClock.elapsedRealtime());
            tvSeconds.setText("");
            accumulatedTime = 0;
        });
    }
}
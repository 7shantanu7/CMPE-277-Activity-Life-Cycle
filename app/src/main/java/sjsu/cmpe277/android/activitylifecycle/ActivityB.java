package sjsu.cmpe277.android.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

public class ActivityB extends Activity {

    private TextView threadCounterView;
    private TextView pauseCounterView;
    private TextView destroyCounterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        ActivityA.threadCounter += 5;
        Log.v(TAG, "Activity B: onCreate. Thread Counter: " + ActivityA.threadCounter);
        
        threadCounterView = findViewById(R.id.threadCounterB);
        pauseCounterView = findViewById(R.id.pauseCounterB);
        destroyCounterView = findViewById(R.id.destroyCounterB);
        
        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Activity B: onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityA.threadCounter += 5;
        Log.v(TAG, "Activity B: onRestart.");
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Activity B: onResume.");
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityA.pauseCounter++;
        Log.v(TAG, "Activity B: onPause. Global Pause Counter: " + ActivityA.pauseCounter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Activity B: onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityA.destroyCounter++;
        Log.v(TAG, "Activity B: onDestroy. Global Destroy Counter: " + ActivityA.destroyCounter);
    }

    private void updateUI() {
        if (threadCounterView != null) {
            threadCounterView.setText("Thread Counter: " + String.format("%04d", ActivityA.threadCounter));
        }
        if (pauseCounterView != null) {
            pauseCounterView.setText("OnPause Counter: " + String.format("%04d", ActivityA.pauseCounter));
        }
        if (destroyCounterView != null) {
            destroyCounterView.setText("OnDestroy Counter: " + String.format("%04d", ActivityA.destroyCounter));
        }
    }

    public void finishActivityB(View v) {
        finish();
    }
}

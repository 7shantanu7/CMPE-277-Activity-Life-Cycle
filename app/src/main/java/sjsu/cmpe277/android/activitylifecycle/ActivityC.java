package sjsu.cmpe277.android.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

public class ActivityC extends Activity {

    private TextView threadCounterView;
    private TextView pauseCounterView;
    private TextView destroyCounterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        ActivityA.threadCounter += 10;
        Log.v(TAG, "Activity C: onCreate. Thread Counter: " + ActivityA.threadCounter);
        
        threadCounterView = findViewById(R.id.threadCounterC);
        pauseCounterView = findViewById(R.id.pauseCounterC);
        destroyCounterView = findViewById(R.id.destroyCounterC);
        
        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Activity C: onStart.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityA.threadCounter += 10;
        Log.v(TAG, "Activity C: onRestart.");
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Activity C: onResume.");
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityA.pauseCounter++;
        Log.v(TAG, "Activity C: onPause. Global Pause Counter: " + ActivityA.pauseCounter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Activity C: onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityA.destroyCounter++;
        Log.v(TAG, "Activity C: onDestroy. Global Destroy Counter: " + ActivityA.destroyCounter);
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

    public void finishActivityC(View v) {
        finish();
    }
}

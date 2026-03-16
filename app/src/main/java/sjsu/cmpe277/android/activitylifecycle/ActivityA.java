package sjsu.cmpe277.android.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import static android.content.ContentValues.TAG;

public class ActivityA extends Activity {

    public static int threadCounter = 0;
    public static int pauseCounter = 0;
    public static int destroyCounter = 0;
    
    private TextView threadCounterView;
    private TextView pauseCounterView;
    private TextView destroyCounterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        threadCounter++;
        Log.v(TAG, "Activity A: onCreate. Thread Counter: " + threadCounter);
        
        threadCounterView = findViewById(R.id.threadCounter);
        pauseCounterView = findViewById(R.id.pauseCounter);
        destroyCounterView = findViewById(R.id.destroyCounter);
        
        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Activity A: onStart. Thread Counter: " + threadCounter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        threadCounter++;
        Log.v(TAG, "Activity A: onRestart. Thread Counter: " + threadCounter);
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Activity A: onResume. Thread Counter: " + threadCounter);
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseCounter++;
        Log.v(TAG, "Activity A: onPause. Pause Counter: " + pauseCounter);
        // Note: UI might not be visible to see the update immediately here
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Activity A: onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyCounter++;
        Log.v(TAG, "Activity A: onDestroy. Destroy Counter: " + destroyCounter);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);
    }

    public void startActivityC(View v) {
        Intent intent = new Intent(this, ActivityC.class);
        startActivity(intent);
    }

    public void startDialog(View v) {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
        threadCounter = 0;
        pauseCounter = 0;
        destroyCounter = 0;
        finish();
    }

    private void updateUI() {
        if (threadCounterView != null) {
            threadCounterView.setText("Thread Counter: " + String.format("%04d", threadCounter));
        }
        if (pauseCounterView != null) {
            pauseCounterView.setText("OnPause Counter: " + String.format("%04d", pauseCounter));
        }
        if (destroyCounterView != null) {
            destroyCounterView.setText("OnDestroy Counter: " + String.format("%04d", destroyCounter));
        }
    }
}

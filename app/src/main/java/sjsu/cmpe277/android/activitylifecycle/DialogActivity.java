package sjsu.cmpe277.android.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

public class DialogActivity extends Activity {

    private TextView threadCounterView;
    private TextView pauseCounterView;
    private TextView destroyCounterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        
        threadCounterView = findViewById(R.id.threadCounterDialog);
        pauseCounterView = findViewById(R.id.pauseCounterDialog);
        destroyCounterView = findViewById(R.id.destroyCounterDialog);
        
        updateUI();
        
        Log.v(TAG, "Dialog Activity: onCreate.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Dialog Activity: onStart.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Dialog Activity: onResume.");
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityA.pauseCounter++;
        Log.v(TAG, "Dialog Activity: onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Dialog Activity: onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityA.destroyCounter++;
        Log.v(TAG, "Dialog Activity: onDestroy.");
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

    public void finishDialog(View v) {
        finish();
    }
}

package com.example.yuemingj.mputest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static final String TAG = "Yueming_MPU";
    TextView mtextView;
    private static String acc_update = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mtextView = (TextView)findViewById(R.id.myTextView);

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (Sensor.TYPE_ACCELEROMETER != event.sensor.getType()) {
            return;
        }

        float[] values = event.values;
        float ax = values[0];
        float ay = values[1];
        float az = values[2];
        acc_update = "ax " + ax +" ay " + ay +" az "+az;
        Log.d(TAG, acc_update);
        mtextView.setText(acc_update);
    }
}

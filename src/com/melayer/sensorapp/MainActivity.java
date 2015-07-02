package com.melayer.sensorapp;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textInfo;
	private SensorManager manager;

	private final MyLisetener listener = new MyLisetener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textInfo = (TextView) findViewById(R.id.textInfo);

		manager = (SensorManager) getSystemService(SENSOR_SERVICE);

		List<Sensor> listSensors = manager.getSensorList(Sensor.TYPE_ALL);

		for (Sensor s : listSensors) {

			Log.i("########### SENSOR #####", s.getName());
		}

		//Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
		if (sensor != null) {

			textInfo.setText("Available");
		} else {
			textInfo.setText("! Available");
		}

		manager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	private class MyLisetener implements SensorEventListener {

		@Override
		public void onSensorChanged(SensorEvent event) {

			float []arr = event.values;
			
			textInfo.setText(""+arr[0]);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	}
}

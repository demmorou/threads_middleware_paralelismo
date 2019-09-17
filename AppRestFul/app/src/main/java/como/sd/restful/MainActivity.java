package como.sd.restful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    TextView sensorPort, info;

    ArrayList<Float> x = new ArrayList();
    ArrayList<Float> y = new ArrayList();
    ArrayList<Float> z = new ArrayList();

    StringBuilder valor = new StringBuilder();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor mGiros = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(new GirosSensor(), mGiros, SensorManager.SENSOR_DELAY_FASTEST);

        sensorPort = (TextView) findViewById(R.id.sensorinfo);
        info = (TextView) findViewById(R.id.info);
    }

    class GirosSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(final SensorEvent event) {
            //if sensor is unreliable, return void
            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
                return;
            }

            //else it will output the Roll, Pitch and Yawn values
            sensorPort.setText(
                    "Orientation X (Roll) :" + Float.toString(event.values[2]) + "\n" +
                        "Orientation Y (Pitch) :" + Float.toString(event.values[1]) + "\n" +
                    "Orientation Z (Yaw) :" + Float.toString(event.values[0])
            );

//            x.add(event.values[2]);
//            y.add(event.values[1]);
//            z.add(event.values[0]);
            info.setText(String.valueOf(x.size()));
            valor.append(event.values[2]).append(";");
            Log.d("teste", String.valueOf(valor));
        }

        public void botaoEnviar(View view){

        }
    }
}



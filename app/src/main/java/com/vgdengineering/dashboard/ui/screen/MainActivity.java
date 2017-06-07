package com.vgdengineering.dashboard.ui.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vgdengineering.dashboard.R;
import com.vgdengineering.dashboard.ui.view.CircularSeekBar;

/*
rmp - int обороти в мин - от 0 до 8000

 */
public class MainActivity extends AppCompatActivity {

    int rpm = 0;
    int speed = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        rpm = 2000;
        CircularSeekBar seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekbar.setProgress(rpm);
        TextView txt = (TextView) findViewById(R.id.rpm);
        txt.setText(Integer.toString(rpm));
        seekbar.setOnSeekBarChangeListener(new CircleSeekBarListener());

        speed = 100;
        CircularSeekBar seekbar1 = (CircularSeekBar) findViewById(R.id.circularSeekBar2);
        seekbar1.setProgress(speed);
        TextView txt1 = (TextView) findViewById(R.id.speed);
        txt1.setText(Integer.toString(speed));
        seekbar1.setOnSeekBarChangeListener(new CircleSeekBarListener());
    }

    public class CircleSeekBarListener implements CircularSeekBar.OnCircularSeekBarChangeListener {
        @Override
        public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean fromUser) {


        }

        @Override
        public void onStopTrackingTouch(CircularSeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(CircularSeekBar seekBar) {

        }
    }
}

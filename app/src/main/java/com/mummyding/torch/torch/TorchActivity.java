package com.mummyding.torch.torch;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Policy;


public class TorchActivity extends Activity implements View.OnClickListener{

    ImageView imageView;
    Camera camera = Camera.open();;
    boolean isOpen = false;
    Camera.Parameters  parameter;
    TextView state ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch);
        imageView = (ImageView) findViewById(R.id.switcher);
        state = (TextView) findViewById(R.id.state);
        imageView.setOnClickListener(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));

        parameter = camera.getParameters();
        isOpen = Boolean.parseBoolean(parameter.getFlashMode());
        changeState();
    }
    void changeState(){
        if(isOpen){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.on));
            state.setText("开");
            camera.startPreview();
            parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);

        }else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));
            state.setText("关");
            parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
        }
    }
    @Override
    public void onClick(View v) {
        isOpen = !isOpen;
        changeState();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//true对任何Activity都适用
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

    }
}

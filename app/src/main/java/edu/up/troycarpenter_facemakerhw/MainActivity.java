package edu.up.troycarpenter_facemakerhw;

/**@Author Troy Carpenter */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         External Citation
         Date: 15 February 2024
         Problem: did not know how to populate a spinner
         Resource: https://developer.android.com/develop/ui/views/components/spinner#java
         Solution: I modified some example code from the documentation
         */
        //defines the spinner we want to populate
        Spinner hairSpin=(Spinner)findViewById(R.id.hairSelector);

        //creates a charsequence using a string array resource
        ArrayAdapter<CharSequence> setUp= ArrayAdapter.createFromResource(
                this, R.array.hair_choices, android.R.layout.simple_spinner_item);

        //sets how the arrayadapter will display
        setUp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //puts everything in the spinner
        hairSpin.setAdapter(setUp);



        Face nicholasCage=new Face(this);
        SeekBar red=findViewById(R.id.redController);
        SeekBar green=findViewById(R.id.greenController);
        SeekBar blue=findViewById(R.id.blueController);

        red.setOnSeekBarChangeListener(nicholasCage);

    }

}
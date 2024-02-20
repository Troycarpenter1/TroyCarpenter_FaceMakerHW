package edu.up.troycarpenter_facemakerhw;

/**
 * @Author Troy Carpenter
 */

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.widget.SeekBar;

import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener {
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;
    public int redColor;
    public int greenColor;
    public int blueColor;


    public Face(Context context) {
        super(context);
        this.randomize();
    }

    //randomizes instance variables to make a random face
    public void randomize() {
        Random rand = new Random();
        skinColor = rand.nextInt(255);
        eyeColor = rand.nextInt(255);
        hairColor = rand.nextInt(255);
        hairStyle = rand.nextInt(4);
    }

    //draws a face using instance variables
    public void onDraw(Canvas c) {

    }

    //helper methods for onDraw method
    //draws specified hairStyle in specified HairColor
    public void drawHair(Canvas c) {

    }

    //draws the eyes in specified color in set locations
    public void drawEyes(Canvas c) {

    }

    //draws the headshape in specified color
    public void drawHead(Canvas c) {

    }

    public void coloration(){
        String numChain=""+redColor+greenColor+blueColor;
        //Integer combined=
    }

    SeekBar red = findViewById(R.id.redController);
    SeekBar green = findViewById(R.id.greenController);
    SeekBar blue = findViewById(R.id.blueController);

    /**checks if the seekbar being messed with is one of the above, and
     changes the red green and blue values defined above to match the bar's progress
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == red.getId()) {
            this.redColor = progress;
        } else if (seekBar.getId() == green.getId()) {
            this.greenColor = progress;
        } else {
            this.blueColor = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {/**not used*/}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {/**not used*/}
}

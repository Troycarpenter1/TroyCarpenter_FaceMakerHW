package edu.up.troycarpenter_facemakerhw;

/**
 * @Author Troy Carpenter
 */

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.SurfaceView;
import android.widget.SeekBar;

import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener {

    //instance variable for skin
    Paint skinColor = new Paint();
    public int skinRedColor;
    public int skinGreenColor;
    public int skinBlueColor;
    //instance variables for eyes
    Paint eyeColor = new Paint();
    public int eyeRedColor;
    public int eyeGreenColor;
    public int eyeBlueColor;
    //instance variables for hair
    Paint hairColor = new Paint();
    public int hairRedColor;
    public int hairGreenColor;
    public int hairBlueColor;
    public int hairStyle;


    public Face(Context context) {
        super(context);
        this.randomize();

    }

    //randomizes instance variables to make a random face
    public void randomize() {
        Random rand = new Random();
        //randomizes skin
        skinRedColor = rand.nextInt(255);
        skinGreenColor = rand.nextInt(255);
        skinBlueColor = rand.nextInt(255);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            skinColor.setColor(coloration(1));
        }
        //randomizes eyes
        eyeRedColor = rand.nextInt(255);
        eyeGreenColor = rand.nextInt(255);
        eyeBlueColor = rand.nextInt(255);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            eyeColor.setColor(coloration(2));
        }
        //randomizes hair
        hairRedColor = rand.nextInt(255);
        hairGreenColor = rand.nextInt(255);
        hairBlueColor = rand.nextInt(255);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            hairColor.setColor(coloration(3));
        }
        hairStyle = rand.nextInt(4);
    }

    //draws a face using instance variables
    public void onDraw(Canvas c) {
        drawHead(c);
    }

    //helper methods for onDraw method
    //draws specified hairStyle in specified HairColor
    public void drawHair(Canvas c) {

    }

    //draws the eyes in specified color in set locations
    public void drawEyes(Canvas c) {

    }

    //draws the head-shape in specified color
    public void drawHead(Canvas c) {
        c.drawCircle(50, 50, 50, skinColor);
    }

    public long coloration(int ind) {
        /**
         External Citation
         Date: 21 February 2024
         Problem: struggled finding a way to convert numbers into hex
         Resource:
         https://www.baeldung.com/java-convert-int-to-hex
         Solution: I used the example code from this post with my own variables
         */
        if(ind==1) {
            String redHex = String.format("%02x", skinRedColor);
            String greenHex = String.format("%02x", skinGreenColor);
            String blueHex = String.format("%02x", skinBlueColor);
            String color = "" + redHex + greenHex + blueHex;
            long col = parseLong(color);
            return col;
        } else if (ind==2) {
            String redHex = String.format("%02x", eyeRedColor);
            String greenHex = String.format("%02x", eyeGreenColor);
            String blueHex = String.format("%02x", eyeBlueColor);
            String color = "" + redHex + greenHex + blueHex;
            long col = parseLong(color);
            return col;
        } else {
            String redHex = String.format("%02x", hairRedColor);
            String greenHex = String.format("%02x", hairGreenColor);
            String blueHex = String.format("%02x", hairBlueColor);
            String color = "" + redHex + greenHex + blueHex;
            long col = parseLong(color);
            return col;
        }
    }

    SeekBar red = findViewById(R.id.redController);
    SeekBar green = findViewById(R.id.greenController);
    SeekBar blue = findViewById(R.id.blueController);

    /**
     * checks if the seekbar being messed with is one of the above, and
     * changes the red green and blue values defined above to match the bar's progress
     */
    @Override
    //summon radio button if statement on the morroww
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

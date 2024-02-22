package edu.up.troycarpenter_facemakerhw;

/**
 * @Author Troy Carpenter
 */

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.SurfaceView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.text.ParseException;
import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    //instance variable for what color is being edited 1-3 for skin, eyes, hair
    public int selection;

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

        String skinHex = getColor(skinRedColor, skinGreenColor, skinBlueColor);
        try {
            //skinColor.setColor(parseInt(skinHex, 16));
            skinColor.setColor(Integer.valueOf(skinHex));
        }
        catch(NumberFormatException nfe) {
            skinColor.setColor(Color.RED);
        }

        //randomizes eyes
        eyeRedColor = rand.nextInt(255);
        eyeGreenColor = rand.nextInt(255);
        eyeBlueColor = rand.nextInt(255);
        String eyeHex = getColor(eyeRedColor, eyeGreenColor, eyeBlueColor);
        eyeColor.setColor(parseInt(eyeHex,16));

        //randomizes hair
        hairRedColor = rand.nextInt(255);
        hairGreenColor = rand.nextInt(255);
        hairBlueColor = rand.nextInt(255);
        String hairHex = getColor(hairRedColor, hairGreenColor, hairBlueColor);
        hairColor.setColor(parseInt(hairHex,16));
        hairStyle = rand.nextInt(4);
    }

    //returns the hex of an rgb
    public String getColor(int r, int g, int b) {
        /**
         External Citation
         Date: 22 February 2024
         Problem: struggled finding a way to convert numbers into hex
         Resource:
         https://stackoverflow.com/questions/52148457/convert-rgb-color-to-hex-color
         Solution: I used the example code from this post with my own variables
         */
        String rgb= String.format("%02x%02x%02x%02x",255, r, g, b);
        return rgb;
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
        if(selection==1) {
            if (seekBar.getId() == red.getId()) {
                this.skinRedColor = progress;
            } else if (seekBar.getId() == green.getId()) {
                this.skinGreenColor = progress;
            } else {
                this.skinBlueColor = progress;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {/**not used*/}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {/**not used*/}

    //sets what color is being changed via an integer corresponding to the choices
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==R.id.skinToggle){
            selection=1;
        } else if (checkedId==R.id.hairToggle) {
            selection=2;
        } else if (checkedId==R.id.eyeToggle) {
            selection=3;
        }
    }
}

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
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import java.text.ParseException;
import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener,
        AdapterView.OnItemSelectedListener {

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


    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.randomize();
        setWillNotDraw(false);

    }

    //randomizes instance variables to make a random face
    public void randomize() {
        Random rand = new Random();
        //randomizes skin
        this.skinRedColor = rand.nextInt(255);
        this.skinGreenColor = rand.nextInt(255);
        this.skinBlueColor = rand.nextInt(255);
        int skinHex = getColor(skinRedColor, skinGreenColor, skinBlueColor);
        this.skinColor.setColor(skinHex);
        this.skinColor.setStyle(Paint.Style.FILL);

        //randomizes eyes
        this.eyeRedColor = rand.nextInt(255);
        this.eyeGreenColor = rand.nextInt(255);
        this.eyeBlueColor = rand.nextInt(255);
        int eyeHex = getColor(eyeRedColor, eyeGreenColor, eyeBlueColor);
        this.eyeColor.setColor(eyeHex);

        //randomizes hair
        this.hairRedColor = rand.nextInt(255);
        this.hairGreenColor = rand.nextInt(255);
        this.hairBlueColor = rand.nextInt(255);
        int hairHex = getColor(hairRedColor, hairGreenColor, hairBlueColor);
        this.hairColor.setColor(hairHex);
        this.hairStyle = rand.nextInt(4);

        this.invalidate();
    }

    //returns the hex of an rgb
    public int getColor(int r, int g, int b) {
        /**
         External Citation
         Date: 22 February 2024
         Problem: struggled finding a way to convert numbers into hex
         Resource:
         https://stackoverflow.com/questions/52148457/convert-rgb-color-to-hex-color
         & Nuxoll for help in making the value usable
         Solution: I used the example code from this post with my own variables
         */
        String rgb = String.format("%02x%02x%02x%02x", 255, r, g, b);
        int colNum = (int) Long.parseLong(rgb, 16);
        return colNum;
    }

    //draws a face using instance variables
    public void onDraw(Canvas c) {
        drawHead(c);
        drawHair(c);
        drawEyes(c);
    }

    //helper methods for onDraw method
    //draws specified hairStyle in specified HairColor
    public void drawHair(Canvas c) {
        int w = c.getWidth();
        float headLeft = ((float) w / 5) * 2 - 50;
        float headRight = ((float) w / 5) * 3 + 50;

        int h = c.getHeight();
        float headHigh = (float) h / 6;

        if (hairStyle == 0) {
            c.drawArc(headLeft - 25, headHigh - 50, headRight + 25, headHigh + 400,
                    180, 180, true, hairColor);
            invalidate();
        } else if (hairStyle == 1) {
            c.drawRect(headLeft + 175, 0, headRight - 175, headHigh + 75, hairColor);
            invalidate();
        } else if (hairStyle == 2) {
            c.drawCircle(w / 2, headHigh - 300, 500, hairColor);
            invalidate();
        } else {
            c.drawRect(headLeft, headHigh + 100, headLeft + 75, h - 50, hairColor);
            c.drawRect(headRight - 75, headHigh + 100, headRight, h - 50, hairColor);
            invalidate();
        }
    }

    //draws the eyes in specified color in set locations
    public void drawEyes(Canvas c) {
        int w = c.getWidth();
        float headLeft = ((float) w / 5) * 2 - 50;
        float headRight = ((float) w / 5) * 3 + 50;

        int h = c.getHeight();
        float headHigh = (float) h / 6;

        Paint eyeWhite = new Paint();
        eyeWhite.setColor(Color.WHITE);
        Paint eyeBlack = new Paint();
        eyeBlack.setColor(Color.BLACK);

        //draws whites of the eyes
        c.drawCircle(headLeft + 150, headHigh + 200, 75, eyeWhite);
        c.drawCircle(headRight - 150, headHigh + 200, 75, eyeWhite);
        //draws iris of eye
        c.drawCircle(headLeft + 150, headHigh + 200, 50, eyeColor);
        c.drawCircle(headRight - 150, headHigh + 200, 50, eyeColor);
        //draws pupils
        c.drawCircle(headLeft + 150, headHigh + 200, 25, eyeBlack);
        c.drawCircle(headRight - 150, headHigh + 200, 25, eyeBlack);
    }

    //draws the head-shape in specified color
    public void drawHead(Canvas c) {
        int w = c.getWidth();
        float headLeft = ((float) w / 5) * 2 - 50;
        float headRight = ((float) w / 5) * 3 + 50;

        int h = c.getHeight();
        float headHigh = (float) h / 6;

        Paint mouthColor = new Paint();
        mouthColor.setColor(0xFFDD8C9F);
        //draws head
        c.drawOval(headLeft, headHigh, headRight, h, this.skinColor);
        //draws ears
        c.drawOval(headLeft - 75, headHigh + 150, headLeft + 50, headHigh + 500,
                this.skinColor);
        c.drawOval(headRight - 50, headHigh + 150, headRight + 75, headHigh + 500,
                this.skinColor);
        //draws mouth
        c.drawArc(headLeft + 75, headHigh + 400, headRight - 75, h - 100, 0,
                180, true, mouthColor);
    }


    /**
     * checks if the seekbar being messed with is one of the above, and
     * changes the red green and blue values defined above to match the bar's progress
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //for changing hair color
        if (selection == 1) {
            if (seekBar.getId() == R.id.redController) {
                this.hairRedColor = progress;
            } else if (seekBar.getId() == R.id.greenController) {
                this.hairGreenColor = progress;
            } else {
                this.hairBlueColor = progress;
            }
            this.hairColor.setColor(getColor(this.hairRedColor, this.hairGreenColor,
                    this.hairBlueColor));
            invalidate();
            //for changing eye color
        } else if (selection == 2) {
            if (seekBar.getId() == R.id.redController) {
                this.eyeRedColor = progress;
            } else if (seekBar.getId() == R.id.greenController) {
                this.eyeGreenColor = progress;
            } else {
                this.eyeBlueColor = progress;
            }
            this.eyeColor.setColor(getColor(this.eyeRedColor, this.eyeGreenColor,
                    this.eyeBlueColor));
            invalidate();
            //for changing skin color(Default)
        } else {
            if (selection == 3) {
                if (seekBar.getId() == R.id.redController) {
                    this.skinRedColor = progress;
                } else if (seekBar.getId() == R.id.greenController) {
                    this.skinGreenColor = progress;
                } else {
                    this.skinBlueColor = progress;
                }
                this.skinColor.setColor(getColor(this.skinRedColor, this.skinGreenColor,
                        this.skinBlueColor));
                invalidate();
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
        MainActivity myApp=(MainActivity)getContext();
        if (checkedId == R.id.skinToggle) {
            this.selection = 3;
            //sets the seekbars to the skin values
            SeekBar seekRed=myApp.findViewById(R.id.redController);
            seekRed.setProgress(skinRedColor);
            SeekBar seekGreen=myApp.findViewById(R.id.greenController);
            seekGreen.setProgress(skinGreenColor);
            SeekBar seekBlue=myApp.findViewById(R.id.blueController);
            seekBlue.setProgress(skinBlueColor);
        } else if (checkedId == R.id.hairToggle) {
            this.selection = 1;
            //sets seekbars to hair values
            SeekBar seekRed=myApp.findViewById(R.id.redController);
            seekRed.setProgress(hairRedColor);
            SeekBar seekGreen=myApp.findViewById(R.id.greenController);
            seekGreen.setProgress(hairGreenColor);
            SeekBar seekBlue=myApp.findViewById(R.id.blueController);
            seekBlue.setProgress(hairBlueColor);
        } else if (checkedId == R.id.eyeToggle) {
            this.selection = 2;
            //sets seekbars to eye values
            SeekBar seekRed=myApp.findViewById(R.id.redController);
            seekRed.setProgress(eyeRedColor);
            SeekBar seekGreen=myApp.findViewById(R.id.greenController);
            seekGreen.setProgress(eyeGreenColor);
            SeekBar seekBlue=myApp.findViewById(R.id.blueController);
            seekBlue.setProgress(eyeBlueColor);
        }
    }
    public void seekSet(){
        if(selection==3) {
            SeekBar skinRed = findViewById(R.id.redController);
            skinRed.setProgress(skinRedColor);
        }
    }


    //one-line method for the randomizer button, which uses a previously made method
    @Override
    public void onClick(View v) {randomize();}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hairStyle = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {/**not used*/}
}

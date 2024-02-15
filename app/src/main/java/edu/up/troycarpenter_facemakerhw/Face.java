package edu.up.troycarpenter_facemakerhw;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Face extends SurfaceView {
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;


    public Face(Context context) {
        super(context);
        this.randomize();
    }
    //randomizes instance variables to make a random face
    public void randomize(){
        Random rand=new Random();
        skinColor=rand.nextInt(255);
        eyeColor=rand.nextInt(255);
        hairColor=rand.nextInt(255);
        hairStyle=rand.nextInt(4);
    }

    //draws a face using instance variables
    public void onDraw(Canvas c){

    }
    //helper methods for onDraw method
    //draws specified hairStyle in specified HairColor
    public void drawHair(Canvas c){

    }
    //draws the eyes in specified color in set locations
    public void drawEyes(Canvas c){

    }
    //draws the headshape in specified color
    public void drawHead(Canvas c){

    }

}

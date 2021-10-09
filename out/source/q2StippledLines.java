import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class q2StippledLines extends PApplet {



public void stippledOutline(int x1, int y1, int x2, int y2, int radius, String pattern) {
  PVector line = new PVector(x2 - x1,y2 - y1);
  PVector perpLine = new PVector(-line.y, line.x);
  PVector dash = line.copy();

  float currentX = x1;
  float currentY = y1;

  float fullLength = line.mag();
  int numberOfSlots; 

  int patternLocation = 0;
  char currentChar = pattern.charAt(patternLocation);
  boolean lastWasDash = (currentChar == '-');

  drawBaseLine(x1, y1, x2, y2, radius);
  addCap(x1, y1, x2, y2, radius);

  perpLine.normalize();
  perpLine.x = perpLine.x * radius;
  perpLine.y = perpLine.y * radius;

  dash.normalize();
  dash.x = dash.x * 2 * radius;
  dash.y = dash.y * 2 * radius;

  numberOfSlots = (int) (fullLength / dash.mag());
  
  // //x1 + perpLine.x => change in x perpindicular
  // //y1 + perpLine.y => change in x perpindicular

  // if(spotAmount == 0){
  //   // if - connect
  //   // if space then |
  // }

  System.out.println("slots = "+numberOfSlots);
  if(numberOfSlots == 0){

    stroke(255,165,0);
    System.out.println("char was = "+pattern.charAt(0));

    if(pattern.charAt(0)=='-'){ 
      drawDash(currentX,currentY,perpLine.x,perpLine.y, dash.x, dash.y);  
    } 
    else{
      drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
      currentX = x2;
      currentY = y2;
      drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
    }
  }
  else {
    
    patternLocation = 0;

    for(int currentSlot = 0; currentSlot < numberOfSlots; currentSlot++){

      // to loop through the pattern
      if(patternLocation >= pattern.length() || patternLocation < 0){
        patternLocation = 0;
      }

      currentChar = pattern.charAt(patternLocation);

      // the very first time! 
      if(currentSlot == 0){
        if(currentChar=='-'){ 
          lastWasDash = true;
          drawDash(currentX,currentY,perpLine.x,perpLine.y, dash.x, dash.y);  
        } 
        else{
          lastWasDash = false;
          drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
        }
      }
      else {
        if(lastWasDash){
          if (currentChar=='-'){ 
            lastWasDash = true;
            drawDash(currentX,currentY,perpLine.x,perpLine.y, dash.x, dash.y);  
          } 
          else{
            lastWasDash = false;
            drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
          }
        }
        else {
          if (currentChar=='-'){ 
            lastWasDash = true;
            drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
            drawDash(currentX,currentY,perpLine.x,perpLine.y, dash.x, dash.y);      
          } 
        }
      }

      patternLocation++;

      currentX = currentX + dash.x;
      currentY = currentY + dash.y;

    }

    if(patternLocation >= pattern.length()-1 || patternLocation < 0){
        patternLocation = 0;
    }
    else {
      patternLocation++;
    }

    currentChar = pattern.charAt(patternLocation);

    if(currentX != x2 || currentY != y2){

      if(lastWasDash){
        if (currentChar=='-'){ 
         stroke(255,165,0);
          beginShape(LINES);

            vertex(currentX + perpLine.x, currentY+perpLine.y);   
            vertex(x2+perpLine.x, y2+perpLine.y);  

            vertex(currentX-perpLine.x, currentY-perpLine.y);   
            vertex(x2-perpLine.x, y2-perpLine.y);  
          endShape(); 
        
        }
        else {
         drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
         drawVerticalLine(x2,y2,perpLine.x,perpLine.y);
        }
      } 
      else {
        if(currentChar=='-'){
            drawVerticalLine(currentX,currentY,perpLine.x,perpLine.y);
          beginShape(LINES);

            vertex(currentX+perpLine.x, currentY+perpLine.y);   
            vertex(x2+perpLine.x, y2+perpLine.y);  

            vertex(currentX-perpLine.x, currentY-perpLine.y);   
            vertex(x2-perpLine.x, y2-perpLine.y);  
          endShape(); 
        }
        else {
           drawVerticalLine(x2,y2,perpLine.x,perpLine.y);
        }
      }
    }

  }
  
  // for(int i = 0; i < spotAmount; i++){
  //   boolean lastDash = true;

  //   // if last one was dash and now is its a space > cap
  //   // if last one was space and now its a dash > cap
  //   // if last one was dash and this dash > draw line
  //   // if last one was space and  this space > do nothing 

  //   // always > update x and y
  // }

  // if currentx != x2 or currenty != y2 
  // based on lastwasdash and current pattern index 

}

public void drawDash(float x, float y, float perpX, float perpY, float dashX, float dashY){
  stroke(255,165,0);
    beginShape(LINES);

        vertex(x+perpX, y+perpY);   
        vertex(x+perpX+dashX, y+perpY+dashY);  

        vertex(x-perpX, y-perpY);   
        vertex(x-perpX+dashX, y-perpY+dashY);

      endShape(); 
}

public void drawVerticalLine(float x, float y, float perpX, float perpY){
  stroke(255,165,0);
    beginShape(LINES);

        vertex(x,y);
        vertex(x+perpX, y+perpY);

        vertex(x,y);       
        vertex(x-perpX, y-perpY);   

      endShape();
}

public void addCap(int x1, int y1, int x2, int y2, int radius){
  float angle = atan2(y2-y1,x2-x1);
  float startPointAngle = (angle + PI/2)/(2*PI);
  float endPointAngle = (angle - PI/2)/(2*PI);

  semicircle(startPointAngle, x1, y1, radius);
  semicircle(endPointAngle, x2, y2, radius);
}

public void semicircle(float startingPoint, int x, int y, int radius){

  float circleX, circleY;

  beginShape(POINTS);
    strokeWeight(2);
    stroke(204,255,153);

    for(float t = startingPoint; t <= startingPoint+0.5f; t+= 0.005f){
      circleX = (radius * cos(t * 2 * PI)) + x;
      circleY = (radius * sin(t* 2 * PI)) + y;
      
      vertex(circleX,circleY);
    
    }
  endShape();
}

public void drawBaseLine(int x1, int y1, int x2, int y2, int radius){
  
  beginShape(LINES);

    // draw the lines
    strokeWeight(3);
    stroke(204,204,255);
    
    vertex(x1, y1);
    vertex(x2, y2);

  endShape();

}


// you shouldn't need to touch any code below this (stippled) line
// --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---

int state = 0;

public void setup() {
  
  frameRate(30);
  noLoop();
}

public void draw() {
  background(0, 0, 83);
  
  // draw in groups of eight
  println("Beginning draw:\n---");


  // // MY CODE

 // stippledOutline(60, height/2, 500, height/2, 50, "---");
  // // println("x1=" + (width/2));
  // // println("y1=" + (height/2));
  // // println("x2=" + (width - width/3));
  // // println("y2=" + (height/2));
  // // END OF MY CODE

  int start = (state * 8) % RADII.length;
  for (int i = start; i < min(start + 8, RADII.length); i++) {
    System.out.printf("x1=%d y1=%d x2=%d y2=%d radius=%d pattern=\"%s\"\n", VERTICES[i*2][0], VERTICES[i*2][1], VERTICES[i*2+1][0], VERTICES[i*2+1][1], RADII[i], PATTERNS[i]);

    stippledOutline(VERTICES[i*2][0], VERTICES[i*2][1], VERTICES[i*2+1][0], VERTICES[i*2+1][1], RADII[i], PATTERNS[i]);
  }
  println("---");
}

public void keyPressed() {
  state++;
  redraw();
}

final int[][] VERTICES = {
  { 50, 50 },
  { 110, 110 },
  { 250, 110 },
  { 200, 50 },
  { 110, 250 },
  { 50, 300 },
  { 200, 300 },
  { 250, 250 },
  { 450, 600 },
  { 150, 600 },
  { 350, 150 },
  { 550, 250 },
  { 500, 350 },
  { 100, 450 },
  { 600, 400 },
  { 600, 100 },

  { 110, 110 },
  { 50, 50 },
  { 200, 50 },
  { 250, 110 },
  { 50, 300 },
  { 110, 250 },
  { 250, 250 },
  { 200, 300 },
  { 150, 600 },
  { 450, 600 },
  { 550, 250 },
  { 350, 150 },
  { 100, 450 },
  { 500, 350 },
  { 600, 100 },
  { 600, 400 },

  { 344, 303 },
  { 600, 200 },
  { 370, 320 },
  { 523, 320 },
  { 353, 343 },
  { 650, 650 },
  { 320, 350 },
  { 320, 620 },
  { 321, 320 },
  { 319, 320 },
  { 301, 311 },
  { 185, 153 },
  { 103, 41 },
  { 580, 105 },
  { 50, 116 },
  { 80, 595 },

  { 3, 3 },
  { 637, 3 },
  { 20, 20 },
  { 30, 620 },
  { 615, 629 },
  { 600, 45 },
  { 575, 502 },
  { 57, 612 },
  { 95, 42 },
  { 542, 83 },
  { 200, 99 },
  { 153, 497 },
  { 415, 130 },
  { 510, 473 },
  { 319, 320 },
  { 321, 320 },
};

final int[] RADII = {
  41,
  41,
  42,
  42,
  37,
  15,
  29,
  5,

  41,
  41,
  42,
  42,
  37,
  15,
  29,
  5,

  12,
  17,
  8,
  18,
  8,
  5,
  24,
  30,

  3,
  8,
  11,
  13,
  17,
  20,
  21,
  25
};

final String[] PATTERNS = {
  "-",
  "-",
  "-",
  "-",
  "-",
  "-",
  "-",
  "-",

  " ",
  " ",
  " ",
  " ",
  " ",
  " ",
  " ",
  " ",

  "- ",
  "- ",
  "-  ",
  "-- ",
  " --",
  " --",
  " -",
  " -",

  "---   ",
  "-----  ----- ",
  " ---  -- ",
  "- -- -",
  "-- -- ",
  " - --",
  "---- ",
  "   -"
};
  public void settings() {  size(640, 640, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "q2StippledLines" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

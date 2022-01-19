import processing.core.PApplet;

public class Sketch extends PApplet {
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }
  
  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  
  // Snowfall
  float[] snowyY = new float [30];
  
  // Speed - up and down keys
  float speed = 1;

  // Snow pile
  float[] snowPileHeight = new float [400];
   
  // Mouse cursor trail
  int num = 25;
  int [] x = new int[num];
  int [] y = new int[num]; 
  int indexPosition = 0;

  public void setup() {
    background(0);
    // Snowfall 
    for (int i = 0; i < snowyY.length; i++) {
        snowyY[i] = random(height);
    }
  }
  
  // Determines if up or down key was pressed to determine speed
  public void keyPressed() {
    if (keyPressed) {
      if (keyCode == UP) {
        speed += 2;
      }
      else if (keyCode == DOWN && speed > 3) {
        speed -= 2;
      }
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);  

    // Snowfall
	  for (int i = 0; i < snowyY.length; i++) {
      int snowyX = width * i / snowyY.length;
      int snowXpos = snowyX;
      
      ellipse(snowyX, snowyY[i], 10, 10);
      snowyY[i] += speed;
      
      if (snowyY[i] > height) {
        snowyY[i] = 0;
        snowPileHeight[snowXpos] += 1;
      }
      
      // Snow piles up on the ground
      for (int x = 0; x < width; x ++) {
        rect(x, height - snowPileHeight[x], 8, height);
        fill(255);

      }
    }    
    
    // Mouse cursor trail
    noStroke();
    x [indexPosition] = mouseX;
    y [indexPosition] = mouseY;

    // Cycle between 0 and the number of elements
    indexPosition = (indexPosition + 1) % num;
    
    for (int i = 0; i < num; i ++) {
      // Set the array position to read
      int pos = (indexPosition + i) % num;
      float radius = i;

      ellipse(x[pos], y [pos], radius, radius);
    }
  } 
}
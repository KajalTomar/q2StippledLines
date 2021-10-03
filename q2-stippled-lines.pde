void stippledOutline(int x1, int y1, int x2, int y2, int radius, String pattern) {
  // TODO: write this method, and any supporting code you need
}

// you shouldn't need to touch any code below this (stippled) line
// --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---

int state = 0;

void setup() {
  size(640, 640, P3D);
  frameRate(30);
  noLoop();
}

void draw() {
  background(0, 0, 83);
  
  // draw in groups of eight
  println("Beginning draw:\n---");
  int start = (state * 8) % RADII.length;
  for (int i = start; i < min(start + 8, RADII.length); i++) {
    System.out.printf("x1=%d y1=%d x2=%d y2=%d radius=%d pattern=\"%s\"\n", VERTICES[i*2][0], VERTICES[i*2][1], VERTICES[i*2+1][0], VERTICES[i*2+1][1], RADII[i], PATTERNS[i]);

    stippledOutline(VERTICES[i*2][0], VERTICES[i*2][1], VERTICES[i*2+1][0], VERTICES[i*2+1][1], RADII[i], PATTERNS[i]);
  }
  println("---");
}

void keyPressed() {
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

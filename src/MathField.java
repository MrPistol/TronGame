public class MathField {

  private static int gridSize;
  private static int cellWidth;
  private static int cellHeight;
  private static int shiftByX;
  private static int shiftByY;
  private int screenWidth, screenHeight;
  private static int fieldWidth;
  private static int fieldHeight;

  public MathField(int screenWidth, int screenHeight, int fieldWidth, int fieldHeight,
      int gridSize) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.fieldWidth = fieldWidth;
    this.fieldHeight = fieldHeight;

    int minScreenSide = Math.min(screenWidth, screenHeight);
    cellWidth = minScreenSide / fieldWidth;
    cellHeight = minScreenSide / fieldHeight;
    shiftByX = (screenWidth - fieldWidth * cellWidth) / 2;
    shiftByY = (screenHeight - fieldHeight * cellHeight) / 2;
    this.gridSize = gridSize;
  }

  public static int getFieldWidth() {
    return fieldWidth;
  }

  public static int getFieldHeight() {
    return fieldHeight;
  }

  public static int findX(int x) {
  return shiftByX + x * cellWidth;
  }
  public static int findY(int y) {
  return shiftByY + y * cellHeight;
  }

  public static int widthCellSizeForDrawing(){
   return cellWidth - gridSize;
  }

  public static int heightCellSizeForDrawing(){
    return cellHeight - gridSize;
  }
}

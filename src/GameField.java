public class GameField {

  private int width, height;



  public GameField(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public boolean isInside(int x, int y) {
    return x >= 0 && x < width &&
        y >= 0 && y < height;
  }


}

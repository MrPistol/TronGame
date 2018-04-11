import java.awt.Color;
import java.awt.Graphics2D;

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

  public boolean isInside(int x, int y) {
    return x >= 0 && x < width &&
        y >= 0 && y < height;
  }

  public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
    final int GRID_SIZE = 1;
    final Color CELL_COLOR = Color.darkGray;
    int minSideSize = Math.min(screenWidth, screenHeight);
    int cellWidth = minSideSize / width;
    int cellHeight = minSideSize / height;
    int shiftByX = (screenWidth - width * cellWidth) / 2;
    int shiftByY = (screenHeight - height * cellHeight) / 2;

    g2.setColor(CELL_COLOR);
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        g2.fillRect(shiftByX + x * cellWidth,
            shiftByY + y * cellHeight,
            cellWidth - GRID_SIZE,
            cellHeight - GRID_SIZE);
      }
    }


  }
}

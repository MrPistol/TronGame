import java.awt.Color;
import java.awt.Graphics2D;

public class DrawGameField  {

  private Color color;

  public DrawGameField(Color color) {
    this.color = color;
  }

  public void grid(Graphics2D g2) {
    g2.setColor(color);
    for (int y = 0; y < MathField.getFieldHeight(); ++y) {
      for (int x = 0; x < MathField.getFieldWidth(); ++x) {
        g2.fillRect(MathField.findX(x),
            MathField.findY(y),
            MathField.widthCellSizeForDrawing(),
            MathField.heightCellSizeForDrawing());
      }
    }
  }
}

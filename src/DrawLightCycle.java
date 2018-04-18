import java.awt.Color;
import java.awt.Graphics2D;

public class DrawLightCycle {

  private Color color;

  public DrawLightCycle(Color color) {
    this.color = color;
  }

  public void draw(Graphics2D g2, LightCycle lightCycle) {
    g2.setColor(color);
    for (int i = 0; i < lightCycle.getLength(); ++i) {
      int x = lightCycle.getSegmentsX()[i];
      int y = lightCycle.getSegmentsY()[i];

      g2.fillRect(MathField.findX(x),
          MathField.findY(y),
          MathField.widthCellSizeForDrawing(),
          MathField.heightCellSizeForDrawing());

    }
  }
}

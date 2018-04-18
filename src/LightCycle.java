import java.awt.Color;

class CollisionException extends RuntimeException {

  CollisionException(String message) {
    super(message);
  }

}


public class LightCycle {

  private int[] segmentsX;
  private int[] segmentsY;
  private int cycleHeadIndex;
  private int dx, dy;

  private boolean aLive;


  public LightCycle(int length, int startX, int startY, int dx, int dy) {
    segmentsX = new int[length];
    segmentsY = new int[length];
    cycleHeadIndex = 0;

    for (int i = 0; i < segmentsX.length; ++i) {
      segmentsX[i] = startX;
    }

    for (int i = 0; i < segmentsY.length; ++i) {
      segmentsY[i] = startY;
    }

    this.dx = dx;
    this.dy = dy;

    aLive = true;

  }


  public int[] getSegmentsX() {
    return segmentsX;
  }

  public int[] getSegmentsY() {
    return segmentsY;
  }

  public boolean isaLive() {
    return aLive;
  }


  public int getLength() {
    return segmentsX.length;
  }

  public void turnUp() {
    if (dy != 1) {
      dx = 0;
      dy = -1;
    }
  }

  public void turnRight() {
    if (dx != 1) {
      dx = 1;
      dy = 0;
    }
  }

  public void turnDown() {
    if (dy != -1) {
      dx = 0;
      dy = 1;
    }
  }

  public void turnLeft() {
    if (dx != 1) {
      dx = -1;
      dy = 0;
    }
  }

  public void move(GameField gameField, LightCycle... lightCycle) {

    if (!aLive) {
      return;
    }
    int nextCycleX = segmentsX[cycleHeadIndex] + dx;
    int nextCycleY = segmentsY[cycleHeadIndex] + dy;

    if (!gameField.isInside(nextCycleX, nextCycleY)) {
      aLive = false;
      throw new CollisionException("Столкновение со стенкой");
    }
    for (int i = 0; i < lightCycle.length; ++i) {
      if (isCollisionLightCycle(lightCycle[i])) {
        aLive = false;
        throw new CollisionException("Столкновение с самими собой");
      }
    }
    cycleHeadIndex = (cycleHeadIndex + 1) % getLength();
    segmentsX[cycleHeadIndex] = nextCycleX;
    segmentsY[cycleHeadIndex] = nextCycleY;

  }


  public boolean isCollisionLightCycle(LightCycle otherLightCycle) {
    int nextCycleX = segmentsX[cycleHeadIndex] + dx;
    int nextCycleY = segmentsY[cycleHeadIndex] + dy;

    for (int i = 0; i < segmentsX.length; ++i) {
      if (nextCycleX == otherLightCycle.segmentsX[i]
          && nextCycleY == otherLightCycle.segmentsY[i]
           ) {
        return true;
      }
    }

    return false;
  }
}

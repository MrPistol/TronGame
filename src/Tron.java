import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tron extends JFrame {

  String title = "Tron - the arcade game";
  Color background = Color.black;
  int delay = 100;

  private final static int GAME_FIELD_WIDTH = 180;
  private final static int GAME_FIELD_HEIGHT = 180;
  private final static int GRID_SIZE = 1;
  private final static Color COLOR_FIELD = Color.darkGray;


  private final static int LENGTH_LIGHT_CYCLE_ONE = 1000;
  private final static int START_X_LIGHT_CYCLE_ONE = 0;
  private final static int START_Y_LIGHT_CYCLE_ONE = 0;
  private final static int DX_LIGHT_CYCLE_ONE = 1;
  private final static int DY_LIGHT_CYCLE_ONE = 0;
  private final static Color COLOR_LIGHT_CYCLE_ONE = Color.yellow;

  private final static int LENGTH_LIGHT_CYCLE_TWO = 1000;
  private final static int START_X_LIGHT_CYCLE_TWO = 0;
  private final static int START_Y_LIGHT_CYCLE_TWO = 89;
  private final static int DX_LIGHT_CYCLE_TWO = 1;
  private final static int DY_LIGHT_CYCLE_TWO = 0;
  private final static Color COLOR_LIGHT_CYCLE_TWO = Color.cyan;


  private MathField mathField;

  private GameField gameField;
  private DrawGameField drawGameField;

  private LightCycle lightCycle;
  private DrawLightCycle drawLightCycle;

  private LightCycle lightCycleSecond;
  private DrawLightCycle drawLightCycleSecond;

  void start() {

    mathField = new MathField(getWidth(), getHeight(), GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT,
        GRID_SIZE);

    gameField = new GameField(GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
    drawGameField = new DrawGameField(COLOR_FIELD);

    lightCycle = new LightCycle(LENGTH_LIGHT_CYCLE_ONE, START_X_LIGHT_CYCLE_ONE,
        START_Y_LIGHT_CYCLE_ONE, DX_LIGHT_CYCLE_ONE, DY_LIGHT_CYCLE_ONE);
    drawLightCycle = new DrawLightCycle(COLOR_LIGHT_CYCLE_ONE);

    lightCycleSecond = new LightCycle(LENGTH_LIGHT_CYCLE_TWO, START_X_LIGHT_CYCLE_TWO,
        START_Y_LIGHT_CYCLE_TWO, DX_LIGHT_CYCLE_TWO, DY_LIGHT_CYCLE_TWO);
    drawLightCycleSecond = new DrawLightCycle(COLOR_LIGHT_CYCLE_TWO);


  }

  void update() {
    // код для обновления свойств объектов
    lightCycle.move(gameField, lightCycle, lightCycleSecond);
    lightCycleSecond.move(gameField, lightCycle, lightCycleSecond);

  }

  void draw(Graphics2D g2) {
    if (drawGameField != null) {
      drawGameField.grid(g2);
    }

    if (drawLightCycle != null) {
      drawLightCycle.draw(g2, lightCycle);
    }
    if (drawLightCycleSecond != null) {
      drawLightCycleSecond.draw(g2, lightCycleSecond);
    }

  }

  void input(int keyCode) {
    switch (keyCode) {
      case (KeyEvent.VK_UP):
        lightCycle.turnUp();
        break;
      case (KeyEvent.VK_RIGHT):
        lightCycle.turnRight();
        break;
      case (KeyEvent.VK_DOWN):
        lightCycle.turnDown();
        break;
      case (KeyEvent.VK_LEFT):
        lightCycle.turnLeft();
        break;

      case (KeyEvent.VK_W):
        lightCycleSecond.turnUp();
        break;
      case (KeyEvent.VK_D):
        lightCycleSecond.turnRight();
        break;
      case (KeyEvent.VK_S):
        lightCycleSecond.turnDown();
        break;
      case (KeyEvent.VK_A):
        lightCycleSecond.turnLeft();
        break;

      case (KeyEvent.VK_ESCAPE):
        System.exit(0);
        break;
    }


  }

  public Tron() {
    setTitle(title);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setUndecorated(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    DrawPanel panel = new DrawPanel();
    panel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        input(e.getKeyCode());
      }
    });
    add(panel);
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        super.componentShown(e);
        start();

        javax.swing.Timer timer = new javax.swing.Timer(delay, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            update();
            repaint();
          }
        });
        timer.start();
      }
    });

    setVisible(true);
  }

  public static void main(String[] args) {
    new Tron();
  }

  class DrawPanel extends JPanel {

    public DrawPanel() {
      setBackground(background);
      setFocusable(true);
      requestFocusInWindow();
      setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      RenderingHints hints = new RenderingHints(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON
      );
      g2.setRenderingHints(hints);

      super.paintComponent(g);
      draw(g2);
    }
  }

}

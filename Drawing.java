import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Drawing extends JPanel{
    // timer for the simulation loop
    Timer simTimer;

    final int FPS = 60;
    final int WIDTH = 1000;
    final int HEIGHT = 1000;

    private List<Body> bodies;
    private Simulation sim;

    public void setup(){
        this.bodies = new ArrayList<>();
        Body body1 = new Body(new Vector2D(300, 500), new Vector2D(0,0.4), new Vector2D(0,0), 100);
        Body body2 = new Body(new Vector2D(650, 400), new Vector2D(0,-0.4), new Vector2D(0,0), 80);
        Body body3 = new Body(new Vector2D(500, 300), new Vector2D(-0.1,0.1), new Vector2D(0,0), 60);
        Body body4 = new Body(new Vector2D(450, 450), new Vector2D(0,-0), new Vector2D(0,0), 80);
        bodies.add(body1);
        bodies.add(body2);
        bodies.add(body3);
        bodies.add(body4);
        sim = new Simulation(bodies);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // clear the screen
        g.clearRect(0, 0, WIDTH, HEIGHT);
    
        // You can add more drawing here
        // black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    
        g.setColor(Color.WHITE);
        for(int i = 0; i < bodies.size(); i++){
            this.bodies.get(i).draw(g);
        }
    }

    public void loop(){
        this.sim.update(300/FPS);
    }

    public Drawing() {
    // Initialize the game window
    JFrame frame = new JFrame("Simple 2D Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.setVisible(true);
    frame.add(this);
    frame.pack();
    
    // call the setup method for parts that need initialized before the game starts
    setup();

    // Initialize game timer to run at a constant FPS
    simTimer = new Timer(1000/FPS, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            loop();
            repaint();
        }
    });
    simTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Drawing();
            }
        });
    }
}
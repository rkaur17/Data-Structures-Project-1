import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Graphics;

public class Tutorial extends JPanel implements ActionListener {

    Timer tm = new Timer(500, this);
    static List<CelestialBody> bodies;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i =0; i < bodies.length(); i++){
            g.fillOval(bodies.get(i).x_coordinate, bodies.get(i).y_coordinate,
                    bodies.get(i).size_pixel * 2, bodies.get(i).size_pixel * 2);
        }
        //start the timer and start the action listener
        tm.start();
    }

    public static void main(String [] args) throws FileNotFoundException {
        File file = new File("src/test.txt"); //read the data file on command line
        Scanner s = new Scanner(file);
        String listType = s.nextLine();
        String size = s.nextLine();
        if (listType.equals("ArrayList")) { //check if data structure used is arraylist or linkedlist
            bodies = new ArrayList<>();
        } else {
            System.out.println("test");
            bodies = new LinkedList<>();
        }

        while (s.hasNext()) {
            // P0,1000000000000000000.,384,384,0.,0.,20
            // P1,150000.,350,350,-0.001,0.001,12
            // P2,100000.,480,480,0.015,-0.015,10
            String nextBody = s.nextLine();
            String [] nextBodyInfo = nextBody.split(","); // [P0, 1000000000000000000., 384, 384, 0., 0., 20]

            // convert everything to what it needs to be
            String name = nextBodyInfo[0];
            float mass = Float.parseFloat(nextBodyInfo[1]);
            int x_coordinate = Integer.parseInt(nextBodyInfo[2]);
            int y_coordinate = Integer.parseInt(nextBodyInfo[3]);
            float x_velocity = Float.parseFloat(nextBodyInfo[4]);
            float y_velocity = Float.parseFloat(nextBodyInfo[5]);
            int size_pixel = Integer.parseInt(nextBodyInfo[6]);
            CelestialBody body = new CelestialBody(name, mass, x_coordinate, y_coordinate, x_velocity, y_velocity, size_pixel);
            bodies.add(body);
            for (int i = 0; i < bodies.length(); i++ ) {
                System.out.println(bodies.get(i).name);
            }
        }
        // list bodies is now full of planets

        // draw planets
        Tutorial t = new Tutorial();
        JFrame jf = new JFrame();
        jf.setTitle("Celestial Bodies");
        jf.setSize(768, 768);
        jf.add(t);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i= 0; i < bodies.length(); i++) {
            float xChange = bodies.get(i).compute_xacceleration(bodies);
            float yChange = bodies.get(i).compute_yacceleration(bodies);
            bodies.get(i).update_velocity(xChange, yChange);
            bodies.get(i).update_position(xChange, yChange);
            repaint();
        }
    }
}

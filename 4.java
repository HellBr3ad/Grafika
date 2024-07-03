// zadanie4

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class HierarchicalModeling extends JPanel implements ActionListener {
    
    private Timer timer;
    private double angle1 = 0;
    private double angle2 = 0;
    private double angle3 = 0;

    public HierarchicalModeling() {
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform old = g2.getTransform();

        g2.translate(getWidth() / 2, getHeight() / 2);

        // Base shape
        g2.rotate(angle1);
        g2.setColor(Color.RED);
        drawPolygon(g2, 6, 50);
        
        g2.translate(100, 0);
        g2.rotate(angle2);
        g2.setColor(Color.GREEN);
        drawPolygon(g2, 8, 30);

        g2.translate(100, 0);
        g2.rotate(angle3);
        g2.setColor(Color.BLUE);
        drawPolygon(g2, 10, 20);

        g2.setTransform(old);
    }

    private void drawPolygon(Graphics2D g2, int sides, int radius) {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            xPoints[i] = (int) (radius * Math.cos(2 * Math.PI * i / sides));
            yPoints[i] = (int) (radius * Math.sin(2 * Math.PI * i / sides));
        }
        g2.fillPolygon(xPoints, yPoints, sides);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle1 += 0.01;
        angle2 -= 0.02;
        angle3 += 0.03;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hierarchical Modeling");
        HierarchicalModeling panel = new HierarchicalModeling();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

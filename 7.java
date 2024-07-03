// zadanie7

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.awt.event.*;

public class LightAndMaterial implements GLEventListener, MouseMotionListener {
    
    private float rotateY = 0;
    private int prevMouseX;

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        final GLCanvas canvas = new GLCanvas(capabilities);
        LightAndMaterial renderer = new LightAndMaterial();
        canvas.addGLEventListener(renderer);
        canvas.addMouseMotionListener(renderer);
        canvas.setSize(800, 600);

        final JFrame frame = new JFrame("Light and Material");
        frame.getContentPane().add(canvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -5.0f);
        gl.glRotatef(rotateY, 0.0f, 1.0f, 0.0f);

        // Set up material properties
        float[] matAmbient = { 0.19125f, 0.0735f, 0.0225f, 1.0f };
        float[] matDiffuse = { 0.7038f, 0.27048f, 0.0828f, 1.0f };
        float[] matSpecular = { 0.256777f, 0.137622f, 0.086014f, 1.0f };
        float matShininess = 12.8f;

        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, matAmbient, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, matDiffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
        gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, matShininess);

        // Draw pyramid
        drawPyramid(gl);
    }

    private void drawPyramid(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        // Front
        gl.glNormal3f(0.0f, 0.5f, 0.5f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        // Right
        gl.glNormal3f(0.5f, 0.5f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        // Back
        gl.glNormal3f(0.0f, 0.5f, -0.5f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        // Left
        gl.glNormal3f(-0.5f, 0.5f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glEnd();

        // Draw base
        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) { }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_DEPTH_TEST);

        // Set up lighting
        float[] ambientLight = { 0.2f, 0.2f, 0.2f, 1.0f };
        float[] diffuseLight = { 0.8f, 0.8f, 0.8f, 1.0f };
        float[] lightPosition = { 1.0f, 1.0f, 1.0f, 0.0f };

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        new GLU().gluPerspective(45.0, (double) width / height, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int dx = x - prevMouseX;
        rotateY += dx * 0.1f;
        prevMouseX = x;
        e.getComponent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        prevMouseX = e.getX();
    }
}

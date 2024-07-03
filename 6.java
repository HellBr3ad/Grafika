// zadanie6

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;

public class ThreeDGeometry implements GLEventListener {
    
    private int objectNumber = 1;
    private float rotateX, rotateY, rotateZ;

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        final GLCanvas canvas = new GLCanvas(capabilities);
        ThreeDGeometry renderer = new ThreeDGeometry();
        canvas.addGLEventListener(renderer);
        canvas.setSize(800, 800);

        final JFrame frame = new JFrame("3D Geometry with OpenGL");
        frame.getContentPane().add(canvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final FPSAnimator animator = new FPSAnimator(canvas, 300, true);
        animator.start();

        canvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_1:
                        renderer.objectNumber = 1;
                        break;
                    case java.awt.event.KeyEvent.VK_2:
                        renderer.objectNumber = 2;
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        renderer.rotateY -= 5;
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        renderer.rotateY += 5;
                        break;
                    case java.awt.event.KeyEvent.VK_UP:
                        renderer.rotateX -= 5;
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        renderer.rotateX += 5;
                        break;
                    case java.awt.event.KeyEvent.VK_PAGE_UP:
                        renderer.rotateZ -= 5;
                        break;
                    case java.awt.event.KeyEvent.VK_PAGE_DOWN:
                        renderer.rotateZ += 5;
                        break;
                }
                canvas.display();
            }
        });
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -5.0f);
        gl.glRotatef(rotateX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(rotateY, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotateZ, 0.0f, 0.0f, 1.0f);
        
        if (objectNumber == 1) {
            drawCorkscrew(gl);
        } else if (objectNumber == 2) {
            drawPyramid(gl);
        }
    }

    private void drawCorkscrew(GL2 gl) {
        gl.glBegin(GL2.GL_LINE_STRIP);
        gl.glColor3f(0f, 1f, 0f);
        for (int i = 0; i < 100; i++) {
            double angle = 0.1 * i;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            double z = 0.1 * i;
            gl.glVertex3d(x, y, z);
        }
        gl.glEnd();
    }

    private void drawPyramid(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(0f, 0f, 1f);
        int n = 16;
        for (int i = 0; i <= n; i++) {
            double angle = 2 * Math.PI * i / n;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            gl.glVertex3d(x, y, 0);
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex3f(0f, 0f, -1f);
        for (int i = 0; i <= n; i++) {
            double angle = 2 * Math.PI * i / n;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            gl.glVertex3d(x, y, 0);
        }
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) { }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_FLAT);
        gl.glEnable(GL2.GL_DEPTH_TEST);
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
}

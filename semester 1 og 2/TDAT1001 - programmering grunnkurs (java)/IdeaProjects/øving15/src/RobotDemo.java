package jogl2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.jogamp.opengl.GL;
import java.awt.*;
import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


/**
 * NeHe Lesson #2 (JOGL 2 Port): Basic 2D Shapes
 * @author Tomas Holt, based on code from Hock-Chuan Chua (May 2012)
 * @version October 2016
 */

/* Main class which extends GLCanvas. This means that this is a OpenGL canvas.
   We will use OpenGL commands to draw on this canvas.
   This implementation has no animation or user input.
*/
public class RobotDemo extends GLCanvas implements GLEventListener {
    // constants
    private static String TITLE = "RobotDemo";
    private static final int CANVAS_WIDTH = 320;  // width of the drawable
    private static final int CANVAS_HEIGHT = 240; // height of the drawable
    int cameraAngle = 0;
    int xPos = 0;
    int yPos = 0;
    int zPos = 0;

    // Setup OpenGL Graphics Renderer
    private GLU glu;  // for the GL Utility
    private GLUT glut = new GLUT();

    /** Constructor to setup the GUI for this Component */
    public RobotDemo() {
        this.addGLEventListener(this);
        this.addKeyListener(new RotateKeyListener());
    }

// ------ Implement methods declared in GLEventListener (init,reshape,display,dispose)

    /**
     * Called immediately after the OpenGL context is initialized. Can be used
     * to perform one-time initialization. Run only once.
     */
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
    }

    /**
     * Handler for window re-size event. Also called when the drawable is
     * first set to visible
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;

        //Set the view port (display area) to cover the entire window
        //gl.glViewport(0, 0, width/2, height/2);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }

    /**
     * Called by OpenGL for drawing
     */
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        glu.gluLookAt(gluLookAtX, 1, gluLookAtZ + zPos, xPos, yPos, zPos, 0, 1, 0);

        drawAllAxis(gl);

        gl.glTranslated(xPos, yPos, zPos);
        drawFeet(gl);
        drawHead(gl);
        drawBody(gl);
        drawArms(gl);

    }

    private double footAngle = 90;
    private void drawFeet(GL2 gl){
        gl.glPushMatrix();

        //if (footAngle > 0) footAngle--;
        gl.glRotated(footAngle,1,0,0);
        glut.glutSolidCylinder(0.2, 1, 10, 10);

        gl.glTranslated(0.6,0,0);
        glut.glutSolidCylinder(0.2, 1, 10, 10);
        gl.glPopMatrix();
    }

    private void drawFoot(GL2 gl, double vinkel){
        //
    }

    private void drawHead(GL2 gl){
        gl.glPushMatrix();
        gl.glTranslated(0.3, 1, 0);
        gl.glRotated(-90,0,1,0);
        glut.glutSolidSphere(0.3, 100, 100);
        gl.glPopMatrix();
    }

    private void drawBody(GL2 gl) {
        gl.glRotated(footAngle,1,0,0);

        gl.glTranslated(0.3, 0.0, -0.7);
        glut.glutSolidCylinder(0.3, 1, 10, 10);
    }

    private void drawArms(GL2 gl){
        gl.glPushMatrix();

        gl.glRotated(footAngle,1.0,1.0,0.0);
        glut.glutSolidCylinder(0.2, 1, 10, 10);

        gl.glTranslated(0.6,0,0);
        glut.glutSolidCylinder(0.2, 1, 10, 10);
        gl.glPopMatrix();
    }

    private double gluLookAtX = 0;
    private double gluLookAtZ = 5;

    private class RotateKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            System.out.println("Lytter kalt");
            if (e.getKeyChar() == 'l'){
                if (gluLookAtX > 0){
                    gluLookAtX = 0;
                    gluLookAtZ = 5;
                }else{
                    gluLookAtX = 5;
                    gluLookAtZ = 0;
                }
            }
            else if (e.getKeyChar() == 'd') {
                cameraAngle++;
                gluLookAtX = 5 * Math.sin(cameraAngle);
                gluLookAtZ = 5 * Math.cos(cameraAngle);
            }
            else if (e.getKeyChar() == 'a') {
                cameraAngle--;
                gluLookAtX = 5 * Math.sin(cameraAngle);
                gluLookAtZ = 5 * Math.cos(cameraAngle);
            }
            else if (e.getKeyChar() == 's') {
                //translatez
                zPos++;
            }
            else if (e.getKeyChar() == 'w') {
                //translatez
                zPos--;
            }


            RobotDemo.this.repaint();
        }
    }

    private void drawAllAxis(GL2 gl){
        gl.glColor3d(1, 0, 0);

        double[] tabell = {2,0,0};
        drawAxis(gl,tabell);

        gl.glColor3d(0, 1, 0);
        //y-akse

        double[] tabell2 = {0,2,0};
        drawAxis(gl,tabell2);

        gl.glColor3d(0, 0, 1);
        //z-akse

        double[] tabell3 = {0,0,2};
        drawAxis(gl,tabell3);
    }

    private void drawAxis(GL2 gl, double[] tabell){
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3dv(tabell, 0);
        gl.glEnd();
    }

    /**
     * Called before the OpenGL context is destroyed. Release resource such as buffers.
     */
    public void dispose(GLAutoDrawable drawable) { }

    /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
    public static void main(String[] args) {
        GLCanvas canvas = new RobotDemo();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas, 30);
        animator.start();
    }
}
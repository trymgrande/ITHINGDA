import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jogamp.opengl.util.FPSAnimator;

/**
 * NeHe Lesson #4 (JOGL 2 Port): Rotation
 * @author Tomas Holt 2016, based on code by Hock-Chuan Chua
 * @version May 2012
 */
@SuppressWarnings("serial")
public class JOGL2Nehe04Rotation extends GLCanvas implements GLEventListener {
   // Define constants for the top-level container
   private static String TITLE = "NeHe Lesson #4: Rotation";
   private static final int CANVAS_WIDTH = 800;  // width of the drawable
   private static final int CANVAS_HEIGHT = 640; // height of the drawable
   private static final int FPS = 60; // animator's target frames per second
      
   // Setup OpenGL Graphics Renderer
   
   private GLU glu;  // for the GL Utility
   private float angleTriangle = 0.0f;
   private float angleQuad = 0.0f;
   private float speedTriangle = 0.5f;
   private float speedQuad = -0.4f;
   
   /** Constructor to setup the GUI for this Component */
   public JOGL2Nehe04Rotation() {
      this.addGLEventListener(this);
   }
   
   // ------ Implement methods declared in GLEventListener ------

   /**
    * Called back immediately after the OpenGL context is initialized. Can be used 
    * to perform one-time initialization. Run only once.
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
      gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
   }

   /**
    * Call-back handler for window re-size event. Also called when the drawable is 
    * first set to visible.
    */
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;

      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

   /**
    * Called back by the animator to perform rendering.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers

      // ---- Render a Triangle -----
      gl.glLoadIdentity();  // reset the model-view matrix
      gl.glTranslatef(-1.5f, 0.0f, -6.0f); // translate left and into the screen
      gl.glRotatef(angleTriangle, 0.0f, 1.0f, 0.0f); // rotate about the y-axis
      gl.glBegin(GL2.GL_TRIANGLES); // draw using triangles
         gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
         gl.glVertex3f(0.0f, 1.0f, 0.0f);
         gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
         gl.glVertex3f(-1.0f, -1.0f, 0.0f);
         gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
         gl.glVertex3f(1.0f, -1.0f, 0.0f);
      gl.glEnd();

      // ---- Render a Quad -----
      gl.glLoadIdentity(); // reset the current model-view matrix
      gl.glTranslatef(1.5f, 0.0f, -6.0f); // translate right and into the screen
      gl.glRotatef(angleQuad, 1.0f, 0.0f, 0.0f); // rotate about the x-axis
      gl.glColor3f(0.5f, 0.5f, 1.0f); // Light-blue
      gl.glBegin(GL2.GL_QUADS); // draw using quads
         gl.glVertex3f(-1.0f, 1.0f, 0.0f);
         gl.glVertex3f(1.0f, 1.0f, 0.0f);
         gl.glVertex3f(1.0f, -1.0f, 0.0f);
         gl.glVertex3f(-1.0f, -1.0f, 0.0f);
      gl.glEnd();

      // Update the rotational angle after each refresh.
      angleTriangle += speedTriangle;
      angleQuad += speedQuad;
   }

   /** 
    * Called back before the OpenGL context is destroyed. Release resource such as buffers. 
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }
   
      /** The entry main() method to setup the top-level container and animator */
   public static void main(String[] args) {
        // Create the OpenGL rendering canvas
        GLCanvas canvas = new JOGL2Nehe04Rotation();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Create a animator that drives canvas' display() at the specified FPS. 
        final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

        // Create the top-level container
        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);
        animator.start(); // start the animation loop
   }
}

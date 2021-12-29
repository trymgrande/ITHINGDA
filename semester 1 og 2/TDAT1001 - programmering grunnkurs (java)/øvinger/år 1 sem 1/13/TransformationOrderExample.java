import java.awt.*;
import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.gl2.GLUT;


/*
 * @author Tomas Holt, shows the effect of transformation order
 * @version October 2016
 */

/* Main class which extends GLCanvas. This means that this is a OpenGL canvas.
   We will use OpenGL commands to draw on this canvas.
   This implementation has no animation or user input.

   This implementation shows the importance of the order transformation are executed.
*/
public class TransformationOrderExample extends GLCanvas implements GLEventListener {
   // constants
   private static String TITLE = "Importance of transformation order";
   private static final int CANVAS_WIDTH = 800;  // width of the drawable
   private static final int CANVAS_HEIGHT = 640; // height of the drawable
  
   // Setup OpenGL Graphics Renderer 
   private GLU glu = new GLU(); // for the GL Utility
   private GLUT glut = new GLUT();
   
   /** Constructor to setup the GUI for this Component */
   public TransformationOrderExample() {
      this.addGLEventListener(this);
   }
   
// ------ Implement methods declared in GLEventListener (init,reshape,display,dispose)          

   /**
    * Called immediately after the OpenGL context is initialized. Can be used 
    * to perform one-time initialization. Run only once.
    */
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glEnable(GL2.GL_DEPTH_TEST);           // enables depth testing
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
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }
   
   //transformation before rotation
   private void drawCubeTransformationFirst(GL2 gl){
        gl.glLoadIdentity();  // reset the model-view matrix
        glu.gluLookAt(2, 0, 9, 0, 0, 0, 0, 1, 0);
        gl.glRotated(45, 0,0,1);//45 deg around z-axis
        gl.glTranslated(1,0,0); //translate 1 along x-axis   
        gl.glScaled(1, 2, 1);//strech cube in y-axis  
        
        //drawXYZAxis(gl);// transformation will move axis!
        
        gl.glColor3d(1, 1, 1);//white
        glut.glutWireCube(1);
   }
   
   //rotation before transformation
   private void drawCubeRotationFirst(GL2 gl){
        gl.glLoadIdentity();  // reset the model-view matrix
        glu.gluLookAt(2, 0, 9, 0, 0, 0, 0, 1, 0);
        gl.glTranslated(1,0,0); //translate 1 along x-axis
        gl.glRotated(45, 0,0,1);//45 deg around z-axis      
        
        //drawXYZAxis(gl);//drawXYZAxis(gl);// transformation will move axis!   
        
        gl.glScaled(1, 2, 1);//strech cube in y-axis
        
        gl.glColor3d(0, 0, 1);//blue
        glut.glutWireCube(1);
   }
   
   private void drawXAxis(GL2 gl){
       gl.glColor3d(1, 0, 0);
       gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(0,0,0);
            gl.glVertex3d(3,0,0);
       gl.glEnd();
   }
   
    private void drawYAxis(GL2 gl){
       gl.glColor3d(0, 1, 0);
       gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(0,0,0);
            gl.glVertex3d(0,3,0);
       gl.glEnd();
   }
    
    private void drawZAxis(GL2 gl){
       gl.glColor3d(0, 0, 1);
       gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(0,0,0);
            gl.glVertex3d(0,0,3);
       gl.glEnd();
   }
    
    private void drawXYZAxis(GL2 gl){
        drawXAxis(gl);
        drawYAxis(gl);
        drawZAxis(gl);
    }

   /**
    * Called by OpenGL for drawing
    */
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      
      glu.gluLookAt(2, 0, 9, 0, 0, 0, 0, 1, 0);
      //gl.glRotated(-30, 0, 1, 0);
      drawXYZAxis(gl);
      gl.glColor3d(1, 1, 1);
      drawCubeRotationFirst(gl);
      drawCubeTransformationFirst(gl);    
   }

   /** 
    * Called before the OpenGL context is destroyed. Release resource such as buffers. 
    */
   public void dispose(GLAutoDrawable drawable) { }
   
   /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
   public static void main(String[] args) {
       GLCanvas canvas = new TransformationOrderExample();
       canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
       
       final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
       frame.getContentPane().add(canvas);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
       frame.setTitle(TITLE);
       frame.pack();
       frame.setVisible(true);      
   }
}

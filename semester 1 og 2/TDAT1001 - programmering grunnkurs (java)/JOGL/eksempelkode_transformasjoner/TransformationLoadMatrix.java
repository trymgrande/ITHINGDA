import static com.jogamp.opengl.GL2.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/*
 * Tomas Holt, oktober 2018
*  Examples with glLoadMatrix() and glMultMatrix(), using arrays (matrices)
*  vs. glRotate()/glTranslate(). Code has a KeyboardListsener => press l button
*  to rotate triangles (remeber to active/mouse click in window before pressing l).
 */

public class TransformationLoadMatrix extends GLCanvas implements GLEventListener {
   // Define constants for the top-level container
   private static String TITLE ="Active window - push key";
   private static final int CANVAS_WIDTH = 640;  // width of the drawable
   private static final int CANVAS_HEIGHT = 480; // height of the drawable
    
   private GLU glu;  // for the GL Utility
   private double angle = 0.0d;  
   private GLCanvas canvas;
   
   /** Constructor to setup the GUI for this Component */
   public TransformationLoadMatrix() {
      this.addGLEventListener(this);
      this.addKeyListener(new KeyboardListener());
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
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
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
      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }
   
   private void drawTriangle(GL2 gl){
        gl.glBegin(GL_TRIANGLES); // draw using triangles
            gl.glColor3d(1, 0, 0); // Red
            gl.glVertex3d(0, 1, 0);
            gl.glColor3d(0, 1, 0); // Green
            gl.glVertex3d(-1, -1, 0);
            gl.glColor3d(0, 0, 1); // Blue
            gl.glVertex3d(1, -1, 0);
        gl.glEnd();
   }
   
    //manipulate MODEL_VIEW matrix with traditional OpenGL 2 transformation methods/functions
    //draw two triangles.
    private void drawTransformationsTraditional(GL2 gl, double rotAngle){
        gl.glLoadIdentity();  // reset the model-view matrix
      
        gl.glTranslated(1.5d, 0.0d, -6.0d); // translate right and into the screen
        gl.glRotated(rotAngle, 0.0d, 1.0d, 0.0d); // rotate about the y-axis

        drawTriangle(gl);
        
        gl.glLoadIdentity();  // reset the model-view matrix
      
        gl.glTranslated(-1.5d, 0.0d, -6.0d); // translate left and into the screen
        gl.glRotated(rotAngle, 0.0d, 1.0d, 0.0d); // rotate about the y-axis

        drawTriangle(gl);
   }
    
    //method for multiply 4x4 matrices! Row first multiplication.
    private double[] multiplyMatrices(double[] a, double[] b){
        double[] ret = new double[16];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                ret[i + j * 4] = a[j * 4] * b[i] + a[j * 4 + 1] * b[i + 4] + a[j * 4 + 2] * b[i + 8] + a[j * 4 + 3] * b[i + 12];
            }
        }
        return ret;
    }
    
    //draw two triangles. Uses both column first and row first matrices.
    private void drawTranformationWithMatricesDirectly(GL2 gl, double rotAngle){
        double a = Math.toRadians(rotAngle);//Math.cos/sin needs radians!
        
        //this is how tranlation and rotation matrices looks with row first
        double[] matrix_row_first_trans = { 1d,   0d, 0d,     1.5d,
                                            0d,   1d, 0d,     0d,
                                            0d,   0d, 1d,     -6d,
                                            0d,   0d, 0d,     1d  };

        double[] matrix_row_first_rot = {Math.cos(a),   0d,     Math.sin(a),    0d,
                                            0d,         1d,         0d,         0d,
                                       -Math.sin(a),    0d,     Math.cos(a),    0d,
                                            0d,         0d,         0d,         1d  };
        
        //this is how tranlation and rotation matrices looks with column first
        //column first is used in OpenGL (Java uses row first)!
        double[] matrix_col_first_trans = {1d,0d,0d,0d,0d,1d,0d,0d,0d,0d,1d,0d,-1.5d,0d,-6d,1d};
        double[] matrix_col_first_rot = {Math.cos(a),0,-Math.sin(a),0,0,1,0,0,Math.sin(a),0,Math.cos(a),0,0,0,0,1};
        
        //do transformations on MODEL_VIEW matrix
        gl.glLoadIdentity();
        
        //OpenGL uses/expects column first matrices.
        //multiply trasformation matrix with rotation matrix.
        gl.glMultMatrixd(matrix_col_first_trans,0);
        gl.glMultMatrixd(matrix_col_first_rot,0);
        
        drawTriangle(gl);
                
        gl.glLoadIdentity();
        
//        //using row fist matrix => must transpose matrices
        gl.glMultTransposeMatrixd(matrix_row_first_trans,0);
        gl.glMultTransposeMatrixd(matrix_row_first_rot,0); 

        drawTriangle(gl);
    }
    
    //draw two triangles, using our own matrix multiplication method.
    private void drawTransformationWithOwnMatrixMultiplicationMethod(GL2 gl, double rotAngle){
        double a = Math.toRadians(rotAngle);//Math.cos/sin needs radians!
        
        double[] matrix_row_first_trans = { 1d,   0d, 0d,     1.5d,
                                            0d,   1d, 0d,     0d,
                                            0d,   0d, 1d,     -6d,
                                            0d,   0d, 0d,     1d  };

        double[] matrix_row_first_rot = {Math.cos(a),   0d,     Math.sin(a),    0d,
                                            0d,         1d,         0d,         0d,
                                       -Math.sin(a),    0d,     Math.cos(a),    0d,
                                            0d,         0d,         0d,         1d  };
                      
        //this is how tranlation and rotation matrices looks with column first
        //column first is used in OpenGL (Java uses row first)!
        double[] matrix_col_first_trans = {1d,0d,0d,0d,0d,1d,0d,0d,0d,0d,1d,0d,-1.5d,0d,-6d,1d};
        double[] matrix_col_first_rot = {Math.cos(a),0,-Math.sin(a),0,0,1,0,0,Math.sin(a),0,Math.cos(a),0,0,0,0,1};
        
        //do transformations on MODEL_VIEW matrix
        gl.glLoadIdentity();
        
        //NB! In this case the rotations need to be in opposite order,
        //because our multiplcation code is for row first matrices!!
        double[] m  = multiplyMatrices(matrix_col_first_rot,matrix_col_first_trans);
        gl.glLoadMatrixd(m,0);
        drawTriangle(gl);//draw the triangle                        
        
        gl.glLoadIdentity();
        //using row first matrix multiplication => order is correct
        m = multiplyMatrices(matrix_row_first_trans,matrix_row_first_rot);
        //need to load transpose matrix as OpenGL wants column first result!!
        gl.glLoadTransposeMatrixd(m, 0);//load the new MODEL_VIEW matrix     

        drawTriangle(gl);//draw the triangle                        
    }

   /**
    * Called back by the animator to perform rendering.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      
        //UNCOMMENT/COMMENT the method (one of three) below you want to use.
        
        drawTransformationsTraditional(gl, angle);//see method description
        
        //drawTranformationWithMatricesDirectly(gl, angle);//see method description
        
        //drawTransformationWithOwnMatrixMultiplicationMethod(gl,angle);//see method description
   }

   /** 
    * Called back before the OpenGL context is destroyed. Release resource such as buffers. 
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }
   
   //if key pressed => increase rotation angle
   class KeyboardListener extends KeyAdapter{
            
        public void keyPressed(KeyEvent e){
            char c = e.getKeyChar();
            System.out.println("Knapp " + c + " er trykket");
            //roterer uansett.
            angle += 5;
            TransformationLoadMatrix.this.repaint(); //or canvas.repain();
        }
    }
   
      /** The entry main() method to setup the top-level container */
    public static void main(String[] args) {
         GLCanvas canvas = new TransformationLoadMatrix();
         canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

         final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
         frame.getContentPane().add(canvas);
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.setTitle(TITLE);
         frame.pack();
         frame.setVisible(true);  
    }
   
}

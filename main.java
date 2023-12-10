import javax.swing.*;
import com.jogamp.opengl.*;

public class Main extends JFrame {

    public Main() {
        setTitle("3D Игра");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(new Game());
        add(canvas);

        canvas.requestFocus();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;

public class Game implements GLEventListener {

    private float x = 0.0f;
    private float y = 0.0f;
    private float z = -5.0f;

    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        if (height <= 0) {
            height = 1;
        }

        final float aspect = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(x, y, z);

        // .... добавьте ваш код для отрисовки игровых объектов ....

        gl.glFlush();
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void moveLeft() {
        x -= 0.1f;
    }

    public void moveRight() {
        x += 0.1f;
    }

    public void moveForward() {
        z += 0.1f;
    }

    public void moveBackward() {
        z -= 0.1f;
    }

    public void keyPressed(char key) {
        switch (key) {
            case 'a':
                moveLeft();
                break;
            case 'd':
                moveRight();
                break;
            case 'w':
                moveForward();
                break;
            case 's':
                moveBackward();
                break;
        }
    }

    public void keyReleased(char key) {
        // Дополнительный код, если необходимо, при отпускании клавиши
    }
}
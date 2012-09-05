package small.business.swing.gui.utils;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// @author Santhosh Kumar T - santhosh@in.fiorano.com 
public class ModalFrameUtil {
    // show the given frame as modal to the specified owner. 
    // NOTE: this method returns only after the modal frame is closed. 

    public static void showAsModal(final Frame frame, final Frame owner) {
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //int height = screenSize.height;
        //int width = screenSize.width;
        // Get the screen size  
        /*GraphicsConfiguration gc = frame.getGraphicsConfiguration();
         Rectangle bounds = gc.getBounds();
         frame.setLocation((int) ((bounds.width / 2) -
         (screenSize.getWidth() / 2)),
         (int) ((bounds.height / 2) - (screenSize.getHeight() / 2)));*/
        // center the jframe on screen
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                owner.setEnabled(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                owner.setEnabled(true);
                frame.removeWindowListener(this);
            }
        });

        owner.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                if (frame.isShowing()) {
                    //frame.setExtendedState(JFrame.NORMAL);
                    int extState = frame.getExtendedState();
                    // Removes possible iconification settings 
                    extState = extState & (0xff - Frame.ICONIFIED);
                    frame.setExtendedState(extState);
                    frame.toFront();
                } else {
                    owner.removeWindowListener(this);
                }
            }
        });


        frame.setVisible(true);
        try {
            new EventPump(frame).start();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    static class EventPump implements InvocationHandler {

        Frame frame;

        public EventPump(Frame frame) {
            this.frame = frame;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            return frame.isShowing() ? Boolean.TRUE : Boolean.FALSE;
        }

        // when the reflection calls in this method has to be 
        // replaced once Sun provides a public API to pump events. 
        public void start() throws Exception {
            Class<?> clazz = Class.forName("java.awt.Conditional");
            Object conditional = Proxy.newProxyInstance(clazz.getClassLoader(),
                    new Class[]{clazz}, this);
            Method pumpMethod = Class.forName("java.awt.EventDispatchThread")
                    .getDeclaredMethod("pumpEvents",
                    new Class[]{clazz});
            pumpMethod.setAccessible(true);
            pumpMethod.invoke(Thread.currentThread(),
                    new Object[]{conditional});
        }
    }
}

package small.business.swing.gui.utils;

import java.awt.SystemTray;
import java.awt.TrayIcon;

/**
 *
 * @author root
 */
public final class TrayMessage {

    TrayMessage(String message) {
        showTrayMessageInfo(message);
    }

    public TrayMessage() {
    }
    
    public void showTrayMessageInfo(String message){
        showTrayMessage(message,TrayIcon.MessageType.INFO);
    }

    private void showTrayMessage(String message,TrayIcon.MessageType messageType) {
        if (SystemTray.isSupported()) {
            final SystemTray tray = SystemTray.getSystemTray();
            TrayIcon[] trayIcons = tray.getTrayIcons();
            trayIcons[0].displayMessage("Small business", message, messageType);
            //trayIcons[0].displayMessage("Small business", "Ініціалізація програми", TrayIcon.MessageType.INFO);
        }
    }
}

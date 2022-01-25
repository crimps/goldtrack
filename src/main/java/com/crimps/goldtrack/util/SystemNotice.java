package com.crimps.goldtrack.util;

import java.awt.*;

/**
 * <p>标题： 系统消息提示 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/24 17:12</p>
 * <p>类全名：com.crimps.goldtrack.util.SystemNotice</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class SystemNotice {

    private static TrayIcon trayIcon;

    /**
     * 推送系统通知
     *
     * @param title
     * @param message
     * @throws AWTException
     */
    public static void displayTray(String title, String message) throws AWTException {
        // Display info notification:
        getTrayIcon().displayMessage(title, message, TrayIcon.MessageType.INFO);
    }

    /**
     * 获取系统图标
     *
     * @return
     */
    private static TrayIcon getTrayIcon(){
        if(null == trayIcon){
            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();
            // If you want to create an icon in the system tray to preview
            Image image = Toolkit.getDefaultToolkit().createImage("gold.png");

            trayIcon = new TrayIcon(image, "Gold Track");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("Gold Track");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println("TrayIcon could not be added.");
            }
        }
        return trayIcon;
    }
}
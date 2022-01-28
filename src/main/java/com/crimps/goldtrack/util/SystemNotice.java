package com.crimps.goldtrack.util;


import java.awt.*;
import java.util.List;

/**
 * <p>标题： 系统消息提示 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/24 17:12</p>
 * <p>类全名：com.crimps.goldtrack.util.SystemNotice</p>
 * <p>
 * 作者：
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
     * @param messageList
     * @throws AWTException
     */
    public static void displayTray(String title, List<String> messageList, TrayIcon.MessageType messageType) throws AWTException {
        String message = getMessageToShow(messageList);
        getTrayIcon().displayMessage(title, message, messageType);
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
            Image image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("gold.png"));
            trayIcon = new TrayIcon(image, "Gold Track");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("Gold Track：黄金中自有市中心大别墅、跑车、收租");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println("TrayIcon could not be added.");
            }
        }
        return trayIcon;
    }

    /**
     * 系统消息格式化
     *
     * @param messageList
     * @return
     */
    private static String getMessageToShow(List<String> messageList) {
        int lineLength = 56;
        StringBuffer messageBuffer = new StringBuffer("");
        for(String message : messageList){
            while (message.length() % lineLength > 0){
                message += " ";
            }
            messageBuffer.append(message);
        }
        return messageBuffer.toString();
    }
}
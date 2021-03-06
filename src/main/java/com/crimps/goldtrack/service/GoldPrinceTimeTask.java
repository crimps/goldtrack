package com.crimps.goldtrack.service;

import com.crimps.goldtrack.dto.GoldPrinceDto;
import com.crimps.goldtrack.dto.HistoryGoldPrinceDto;
import com.crimps.goldtrack.dto.ICBCRuyiGoldPrinceDto;
import com.crimps.goldtrack.util.SystemNotice;

import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.List;

/**
 * <p>标题： 金价定时任务 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/25 10:14</p>
 * <p>类全名：com.crimps.goldtrack.service.GoldPrinceTimeTask</p>
 * <p>
 * 作者：
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class GoldPrinceTimeTask extends TimerTask {

    private String taskName;

    public GoldPrinceTimeTask(String taskName){
        this.taskName = taskName;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        GoldPrinceService goldPrinceService = new GoldPrinceService();
        ICBCGoldPrinceService icbcGoldPrinceService = new ICBCGoldPrinceService();
        try {
            StringBuffer titleTip = new StringBuffer("");
            ICBCRuyiGoldPrinceDto icbcRuyiGoldPrinceDto = icbcGoldPrinceService.getICBCRuyiGoldPrince();
            Double ruyiPrince = icbcGoldPrinceService.getLastICBCRuyiPrince(icbcRuyiGoldPrinceDto);
            GoldPrinceDto goldPrinceDto = goldPrinceService.getGoldPrinceBySeg();
            titleTip.append("工行如意金:").append(ruyiPrince).append(" ").append("国际金价:").append(goldPrinceDto.getLastPrince());
            HistoryGoldPrinceDto historyGoldPrinceDto = goldPrinceService.getHistoryGoldPrince();
            StrategyService strategyService = new StrategyService();
            List<String> messageList = new ArrayList<>();
            String message = "Min:" + goldPrinceDto.getMin() + ", Max:" + goldPrinceDto.getMax();
            messageList.add(message);
            Calendar calendar = Calendar.getInstance();
            //近30天行情
            calendar.add(Calendar.DAY_OF_YEAR, -30);
            Date before30Day = calendar.getTime();
            List<String> history30TipList = strategyService.historyTip(goldPrinceDto, historyGoldPrinceDto, before30Day);
            messageList.addAll(history30TipList);
            //阀值信息
            ConfigService configService = new ConfigService();
            Double threshold = configService.getGoldPringThreshold();
            Double lastPrince = goldPrinceDto.getLastPrince();
            String thresholdTip = "";
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(2);
            boolean flag = false;
            if(lastPrince.compareTo(threshold) > 0){
                thresholdTip = "高于预设阀值[" + threshold + "] : " + numberFormat.format(lastPrince - threshold) + "↑";
            }else{
                flag = true;
                thresholdTip = "低于预设阀值[" + threshold + "] : " + numberFormat.format(threshold - lastPrince) + "↓";
            }
            messageList.add(thresholdTip);
            if(flag){
                SystemNotice.displayTray(titleTip.toString(), messageList, TrayIcon.MessageType.WARNING);
            }else{
                SystemNotice.displayTray(titleTip.toString(), messageList, TrayIcon.MessageType.INFO);
            }
        } catch (IOException | AWTException | ParseException e) {
            e.printStackTrace();
        }
    }
}
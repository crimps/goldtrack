package com.crimps.goldtrack.service;

import com.crimps.goldtrack.dto.GoldPrinceDto;
import com.crimps.goldtrack.dto.HistoryGoldPrinceDto;
import com.crimps.goldtrack.util.ConfigService;
import com.crimps.goldtrack.util.GoldPrinceService;
import com.crimps.goldtrack.util.SystemNotice;

import java.awt.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.List;

/**
 * <p>标题： </p>
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
        try {
            GoldPrinceDto goldPrinceDto = goldPrinceService.getGoldPrinceBySeg();
            String lastPrinceTip = goldPrinceDto.getLastPrinceTip();
            HistoryGoldPrinceDto historyGoldPrinceDto = goldPrinceService.getHistoryGoldPrince();
            StrategyService strategyService = new StrategyService();
            List<String> messageList = new ArrayList<>();
            String message = "Min:" + goldPrinceDto.getMin() + ", Max:" + goldPrinceDto.getMax();
            messageList.add(message);
            //近7天行情
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date before7Day = calendar.getTime();
            List<String> history7TipList = strategyService.historyTip(goldPrinceDto, historyGoldPrinceDto, before7Day);
            messageList.addAll(history7TipList);
            //近30天行情
            calendar.add(Calendar.DAY_OF_YEAR, -23);
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
            if(lastPrince.compareTo(threshold) > 0){
                thresholdTip = "高于预设阀值[" + threshold + "] : " + numberFormat.format(lastPrince - threshold) + "↑";
            }else{
                thresholdTip = "低于预设阀值[" + threshold + "] : " + numberFormat.format(threshold - lastPrince) + "↓";
            }
            messageList.add(thresholdTip);
            SystemNotice.displayTray(lastPrinceTip, messageList);
        } catch (IOException | AWTException | ParseException e) {
            e.printStackTrace();
        }
    }
}
package com.crimps.goldtrack.service;

import com.crimps.goldtrack.dto.GoldPrinceDto;
import com.crimps.goldtrack.dto.SegGoldPrinceDto;
import com.crimps.goldtrack.util.GoldPrinceService;
import com.crimps.goldtrack.util.SystemNotice;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.TimerTask;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/25 10:14</p>
 * <p>类全名：com.crimps.goldtrack.service.GoldPrinceTimeTask</p>
 * <p>
 * 作者：chenwz
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
            String lastPrince = goldPrinceDto.getLastPrince();
            SystemNotice.displayTray(lastPrince, "Min:" + goldPrinceDto.getMin() + ", Max:" + goldPrinceDto.getMax());
        } catch (IOException | AWTException | ParseException e) {
            e.printStackTrace();
        }

    }
}
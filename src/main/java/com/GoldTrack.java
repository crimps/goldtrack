package com;

import com.crimps.goldtrack.service.GoldPrinceTimeTask;
import com.crimps.goldtrack.service.ConfigService;

import java.io.IOException;
import java.util.Timer;

/**
 * <p>标题：  </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/24 17:02</p>
 * <p>类全名：com.crimps.goldtrack.GoldTrack</p>
 * <p>
 * 作者：
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class GoldTrack {

    /**
     * 默认刷新时间间隔
     */
    private static final Long period = 10 * 60 * 1000L;

    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
        ConfigService configService = new ConfigService();
        Long configPeriod = configService.getPeriod();
        if(null == configPeriod){
            configPeriod = period;
        }
        timer.schedule(new GoldPrinceTimeTask("segGold"), 0, configPeriod);
    }

}
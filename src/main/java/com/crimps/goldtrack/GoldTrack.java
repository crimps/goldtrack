package com.crimps.goldtrack;

import com.crimps.goldtrack.service.GoldPrinceTimeTask;

import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * <p>标题：  </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/24 17:02</p>
 * <p>类全名：com.crimps.goldtrack.GoldTrack</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class GoldTrack {

    private static final Long period = 10 * 60 * 1000L;

    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new GoldPrinceTimeTask("segGold"), 0, period);
    }

}
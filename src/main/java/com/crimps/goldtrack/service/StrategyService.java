package com.crimps.goldtrack.service;

import com.crimps.goldtrack.dto.GoldPrinceDto;
import com.crimps.goldtrack.dto.HistoryGoldPrinceDto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>标题： 策略服务 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/25 15:47</p>
 * <p>类全名：com.crimps.goldtrack.service.StrategyService</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class StrategyService {

    /**
     * 获取历史行情数据比较
     *
     * @param goldPrinceDto
     * @param historyGoldPrinceDto
     * @return
     */
    public List<String> historyTip(GoldPrinceDto goldPrinceDto, HistoryGoldPrinceDto historyGoldPrinceDto, Date startTime){
        List<String> tipList = new ArrayList<>();
        Double lastPrince = goldPrinceDto.getLastPrince();
        Double lowNum = 0d;
        Double highNum = 0d;
        Double totalNum = 0d;
        for(HistoryGoldPrinceDto.GoldPrince goldPrince : historyGoldPrinceDto.getGoldPrinceList()){
            if (startTime.before(goldPrince.getDate())){
                totalNum++;
                Double lowest = goldPrince.getLowest();
                Double highest = goldPrince.getHighest();
                //低于当天最低价
                if(Double.compare(lastPrince, lowest) < 0){
                    lowNum++;
                }
                //高于当天最高价
                if(Double.compare(lastPrince, highest) > 0){
                    highNum++;
                }
            }
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        Double lowRate = Double.valueOf(lowNum / totalNum * 100);
        Double highRate = Double.valueOf(highNum / totalNum) * 100;
        tipList.add("低于历史行情：" + numberFormat.format(lowRate) + "%");
        tipList.add("高于历史行情：" + numberFormat.format(highRate) + "%");
        return tipList;
    }
}
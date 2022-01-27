package com.crimps.goldtrack.dto;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>标题： 标准国际金价 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/25 10:28</p>
 * <p>类全名：com.crimps.goldtrack.dto.GoldPrinceDto</p>
 * <p>
 * 作者：
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@Data
public class GoldPrinceDto {

    private Double min;
    private Double max;
    private String heyue;
    private Date delay;
    private List<Prince> princeList;

    @Data
    public static class Prince{
        Date time;
        Double data;

        public Prince(Date time, Double data){
            this.time = time;
            this.data = data;
        }
    }

    /**
     * 获取实时金价
     * @return
     */
    public Double getLastPrince(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        Date now = calendar.getTime();
        Date maxTime = null;
        Double data = null;
        for(Prince prince : princeList){
            Date time = prince.getTime();
            if(null == maxTime){
                maxTime = time;
                data = prince.getData();
            }else{
                if(time.after(maxTime) && time.before(now) && time.before(delay)){
                    maxTime = time;
                    data = prince.getData();
                }
            }
        }
        return data;
    }

    /**
     * 获取实时金价
     *
     * @return
     */
    public String getLastPrinceTip(){
        String lastPrince = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        Date now = calendar.getTime();
        Date maxTime = null;
        Double data = null;
        for(Prince prince : princeList){
            Date time = prince.getTime();
            if(null == maxTime){
                maxTime = time;
                data = prince.getData();
            }else{
                if(time.after(maxTime) && time.before(now) && time.before(delay)){
                    maxTime = time;
                    data = prince.getData();
                }
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        lastPrince = "金价" + data;
        return lastPrince;
    }
}
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
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/25 10:28</p>
 * <p>类全名：com.crimps.goldtrack.dto.GoldPrinceDto</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@Data
public class GoldPrinceDto {

    private Integer min;
    private Integer max;
    private String heyue;
    private Date delay;
    private List<Prince> princeList;

    @Data
    public static class Prince{
        Date time;
        String data;

        public Prince(Date time, String data){
            this.time = time;
            this.data = data;
        }
    }

    /**
     * 获取实时金价
     *
     * @return
     */
    public String getLastPrince(){
        String lastPrince = "";
        Date now = Calendar.getInstance().getTime();
        Date maxTime = null;
        String data = null;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        lastPrince = dateFormat.format(maxTime) + " 金价：" + data;
        return lastPrince;
    }
}
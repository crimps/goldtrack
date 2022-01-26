package com.crimps.goldtrack.util;

import java.io.*;

/**
 * <p>标题： 配置相关服务类 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/26 9:53</p>
 * <p>类全名：com.crimps.goldtrack.util.ConfigService</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class ConfigService {

    /**
     * 金价阀值
     */
    private Double threshold;

    /**
     * 刷新间隔时间
     */
    private Long period;

    /**
     * 获取配置文件中的金价阀值
     *
     * @return
     * @throws IOException
     */
    public Double getGoldPringThreshold() throws IOException {
        if(null == threshold){
            String path = System.getProperty("user.dir")+ File.separator + "goldTrack.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line="";
            while((line=br.readLine())!=null){
                threshold = Double.valueOf(line);
            }
        }
        return threshold;
    }

    public Long getPeriod() throws IOException {
        if(null == period){
            String path = System.getProperty("user.dir")+ File.separator + "goldTrack.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line="";
            while((line=br.readLine())!=null){
                period = Long.valueOf(line) * 60 * 1000;
                break;
            }
        }
        return period;
    }
}
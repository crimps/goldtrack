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

    public Double getGoldPringThreshold() throws IOException {
        Double threshold = 360D;
        String path = System.getProperty("user.dir")+ File.separator + "goldTrack.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line="";
        while((line=br.readLine())!=null){
            threshold = Double.valueOf(line);
        }
        return threshold;
    }
}
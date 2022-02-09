package com.crimps.goldtrack.service;

import com.crimps.goldtrack.dto.ICBCRuyiGoldPrinceDto;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * <p>标题： 工行</p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/2/7 16:45</p>
 * <p>类全名：com.crimps.goldtrack.service.ICBCGoldPrinceService</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class ICBCGoldPrinceService {

    /**
     * 工行贵金属查询URL
     */
    private final String GOLD_URL = "https://mybank.icbc.com.cn/servlet/AsynGetDataServlet";

    /**
     * 贵金属类型查询参数
     */
    private final String PARAM_TRANCODE = "tranCode";

    /**
     * 工行如意金
     */
    private final String VALUE_RUYI = "A00505";

    /**
     * 获取工行如意金价格信息
     *
     * @return
     * @throws IOException
     */
    public ICBCRuyiGoldPrinceDto getICBCRuyiGoldPrince() throws IOException {
        ICBCRuyiGoldPrinceDto icbcRuyiGoldPrinceDto = null;
        String url = GOLD_URL + "?" + PARAM_TRANCODE + "=" + VALUE_RUYI;
        OkHttpClient httpClient = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(getRequest);
        Response response = call.execute();
        String icbcRuyiGoldStr = response.body().string();
        if(StringUtils.isNotBlank(icbcRuyiGoldStr)){
            Gson gson = new Gson();
            icbcRuyiGoldPrinceDto = gson.fromJson(icbcRuyiGoldStr, ICBCRuyiGoldPrinceDto.class);
        }
        return icbcRuyiGoldPrinceDto;
    }

    /**
     * 获取最新买入价
     *
     * @param icbcRuyiGoldPrinceDto
     * @return
     */
    public Double getLastICBCRuyiPrince(ICBCRuyiGoldPrinceDto icbcRuyiGoldPrinceDto){
        Double ruyiPrince = 0D;
        if(null != icbcRuyiGoldPrinceDto && null != icbcRuyiGoldPrinceDto.getPronoinfo()){
            List<ICBCRuyiGoldPrinceDto.PronoinfoBean> pronoinfoBeanList = icbcRuyiGoldPrinceDto.getPronoinfo();
            if(pronoinfoBeanList.size() >= 0){
                ICBCRuyiGoldPrinceDto.PronoinfoBean pronoinfoBean = pronoinfoBeanList.get(0);
                ruyiPrince = Double.valueOf(pronoinfoBean.getBuyprice()) / 100D;
            }
        }
        return ruyiPrince;
    }

}
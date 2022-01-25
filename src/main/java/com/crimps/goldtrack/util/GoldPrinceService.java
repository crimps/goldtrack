package com.crimps.goldtrack.util;

import com.crimps.goldtrack.dto.GoldPrinceDto;
import com.crimps.goldtrack.dto.HistoryGoldPrinceDto;
import com.crimps.goldtrack.dto.SegGoldPrinceDto;
import com.crimps.goldtrack.dto.SegHistoryGoldPrinceDto;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>标题： 国际金价服务类 </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/24 17:24</p>
 * <p>类全名：com.crimps.goldtrack.util.GoldPrince</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
public class GoldPrinceService {

    /**
     * 每日实时金价链接
     */
    private final String GOLD_URL_SEG = "http://www.sge.com.cn/graph/quotations?instid=Au99.99";

    /**
     * 历史行情价格
     */
    private final String HISTORY_GOLD_URL_SEG = "http://www.sge.com.cn/graph/Dailyhq?instid=Au99.99";


    /**
     * 通过上海国际金价获取标准国际金价
     *
     * @return
     * @throws IOException
     */
    public GoldPrinceDto getGoldPrinceBySeg() throws IOException, ParseException {
        SegGoldPrinceDto segGoldPrinceDto = getSegGoldPrince();
        return coversinGoldPrince(segGoldPrinceDto);
    }

    /**
     * 上海国际金价转换为标准国际金价
     *
     * @param segGoldPrinceDto
     * @return
     */
    private GoldPrinceDto coversinGoldPrince(SegGoldPrinceDto segGoldPrinceDto) throws ParseException {
        GoldPrinceDto goldPrinceDto = new GoldPrinceDto();
        goldPrinceDto.setMin(segGoldPrinceDto.getMin());
        goldPrinceDto.setMax(segGoldPrinceDto.getMax());
        goldPrinceDto.setHeyue(segGoldPrinceDto.getHeyue());
        //最后更新时间
        goldPrinceDto.setDelay(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").parse(segGoldPrinceDto.getDelaystr()));
        goldPrinceDto.setPrinceList(new ArrayList<>());
        List<String> times = segGoldPrinceDto.getTimes();
        List<Double> datas = segGoldPrinceDto.getData();
        if(null != times && null != datas){
            int length =times.size();
            if(length > datas.size()){
                length = datas.size();
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Date now = new Date();
            for(int index = 0; index < length; index++){
                //时间转换
                String time = dateStr + " " + times.get(index);
                Date date = dateFormat.parse(time);
                if(date.before(now)){
                    GoldPrinceDto.Prince prince = new GoldPrinceDto.Prince(date, datas.get(index));
                    goldPrinceDto.getPrinceList().add(prince);
                }
            }
        }
        return goldPrinceDto;
    }


    /**
     * 获取上海黄金金价
     *
     * @return
     * @throws IOException
     */
    private SegGoldPrinceDto getSegGoldPrince() throws IOException {
        SegGoldPrinceDto segGoldPrinceDto = null;
        OkHttpClient httpClient = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(GOLD_URL_SEG)
                .get()
                .build();
        Call call = httpClient.newCall(getRequest);
        Response response = call.execute();
        String goldStr = response.body().string();
        if(StringUtils.isNotBlank(goldStr)){
            Gson gson = new Gson();
            segGoldPrinceDto = gson.fromJson(goldStr, SegGoldPrinceDto.class);
        }
        return segGoldPrinceDto;
    }


    /**
     * 获取标准黄金历史行情数据
     *
     * @return
     */
    public HistoryGoldPrinceDto getHistoryGoldPrince() throws IOException, ParseException {
        HistoryGoldPrinceDto historyGoldPrinceDto = new HistoryGoldPrinceDto();
        List<HistoryGoldPrinceDto.GoldPrince> goldPrinceList = new ArrayList<>();
        historyGoldPrinceDto.setGoldPrinceList(goldPrinceList);
        SegHistoryGoldPrinceDto segHistoryGoldPrinceDto = getSegHistoryGoldPrince();
        for(List<String> list : segHistoryGoldPrinceDto.getTime()){
            if(list.size() == 5){
                HistoryGoldPrinceDto.GoldPrince goldPrince = new HistoryGoldPrinceDto.GoldPrince();
                goldPrince.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0)));
                goldPrince.setOpen(Double.valueOf(list.get(1)));
                goldPrince.setClose(Double.valueOf(list.get(2)));
                goldPrince.setLowest(Double.valueOf(list.get(3)));
                goldPrince.setHighest(Double.valueOf(list.get(4)));
                historyGoldPrinceDto.getGoldPrinceList().add(goldPrince);
            }
        }
        return historyGoldPrinceDto;
    }

    /**
     * 获取上海黄金交易所历史行情数据
     *
     * @return
     */
    private SegHistoryGoldPrinceDto getSegHistoryGoldPrince() throws IOException {
        SegHistoryGoldPrinceDto segHistoryGoldPrinceDto = new SegHistoryGoldPrinceDto();
        OkHttpClient httpClient = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(HISTORY_GOLD_URL_SEG)
                .get()
                .build();
        Call call = httpClient.newCall(getRequest);
        Response response = call.execute();
        String historyGoldStr = response.body().string();
        if(StringUtils.isNotBlank(historyGoldStr)){
            Gson gson = new Gson();
            segHistoryGoldPrinceDto = gson.fromJson(historyGoldStr, SegHistoryGoldPrinceDto.class);
        }
        return segHistoryGoldPrinceDto;
    }
}
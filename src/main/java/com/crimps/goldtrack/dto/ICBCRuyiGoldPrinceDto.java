package com.crimps.goldtrack.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/2/7 16:48</p>
 * <p>类全名：com.crimps.goldtrack.dto.ICBCGoldPrinceDto</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@NoArgsConstructor
@Data
public class ICBCRuyiGoldPrinceDto {
    private List<PronoinfoBean> pronoinfo;
    @SerializedName("TranErrorCode")
    private String tranErrorCode;
    private List<RyinfoBean> ryinfo;
    @SerializedName("TranErrorDisplayMsg")
    private String tranErrorDisplayMsg;

    @NoArgsConstructor
    @Data
    public static class PronoinfoBean {
        private String buyprice;
        private String sellprice;
        private String prodcode;
        private String pptxnno;
    }

    @NoArgsConstructor
    @Data
    public static class RyinfoBean {
        private String buyGMStep;
        private String productId;
        private String buyMinGM;
        private String regMinGM;
        private String totalMonth;
        private String regAmtStep;
        private String jcTXONOFF;
        private String productName;
        private String dayType;
        private String buyMinAmt;
        private String perMonth;
        private String zoneNo;
        private String regGMStep;
        private String pptxnno;
        private String dayNum;
        private String regMinAmt;
        private String buyAmtStep;
    }
}
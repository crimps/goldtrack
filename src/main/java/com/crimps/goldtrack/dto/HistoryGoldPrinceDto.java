package com.crimps.goldtrack.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>标题： 标准金价历史行情Dto</p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/25 15:20</p>
 * <p>类全名：com.crimps.goldtrack.dto.HistoryGoldPrinceDto</p>
 * <p>
 * 作者：
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@Data
public class HistoryGoldPrinceDto {

    private List<GoldPrince> goldPrinceList;

    @Data
    public static class GoldPrince{
        private Date date;
        private Double open;
        private Double close;
        private Double lowest;
        private Double highest;
    }
}
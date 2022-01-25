package com.crimps.goldtrack.dto;

import java.util.List;

/**
 * <p>标题： 上海国际金价Dto </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: 厦门象屿科技有限公司</p>
 * <p>创建日期：2022/1/25 9:59</p>
 * <p>类全名：com.crimps.goldtrack.dto.GoldPrinceDto</p>
 * <p>
 * 作者：chenwz
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@lombok.NoArgsConstructor
@lombok.Data
public class SegGoldPrinceDto {

    private List<String> times;
    private Double min;
    private List<Double> data;
    private Double max;
    private String heyue;
    private String delaystr;
}
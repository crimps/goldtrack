package com.crimps.goldtrack.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>标题： 上海黄金交易所金价历史行情Dto </p>
 * <p>功能： </p>
 * <p>版权： Copyright (c) 2022</p>
 * <p>公司: </p>
 * <p>创建日期：2022/1/25 15:15</p>
 * <p>类全名：com.crimps.goldtrack.dto.SegHistoryGoldPrinceDto</p>
 * <p>
 * 作者：
 * 初审：
 * 复审：
 *
 * @version 1.0
 */
@NoArgsConstructor
@Data
public class SegHistoryGoldPrinceDto {

    private List<List<String>> time;
}
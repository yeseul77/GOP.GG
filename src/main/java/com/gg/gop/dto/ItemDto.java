package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private String championName_KR;
    private String teamPosition;
    private int core1;
    private int core2;
    private int core3;
    private int core3_percentage;
    private int core3_win_percentage;
}

package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChampionDto {
	private String champion_name_kr;
	private String champion_name;
	private String champion_skill_id1;
	private String champion_skill_id2;
	private String champion_skill_id3;
	private String champion_skill_id4;
	private String champion_skill_name1;
	private String champion_skill_name2;
	private String champion_skill_name3;
	private String champion_skill_name4;
	private String champion_passive;
	private String champion_passive_en;

}

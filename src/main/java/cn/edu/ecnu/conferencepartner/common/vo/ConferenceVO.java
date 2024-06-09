package cn.edu.ecnu.conferencepartner.common.vo;

import cn.edu.ecnu.conferencepartner.common.enums.ConferenceGradeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Year;

@Schema(name = "会议展示数据")
@Data
@Builder
public class ConferenceVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "年份")
    private Year year;

    @Schema(description = "简称")
    private String abbrev;

    @Schema(description = "等级")
    private ConferenceGradeType grade;

    @Schema(description = "全称")
    private String name;

    @Schema(description = "截稿日期")
    private LocalDate deadline;

    @Schema(description = "是否延期")
    private Boolean isExtended;

    @Schema(description = "通知日期")
    private LocalDate notificationDate;

    @Schema(description = "会议日期")
    private LocalDate conferenceDate;

    @Schema(description = "会议地点")
    private String location;

    @Schema(description = "届数")
    private Integer session;

    @Schema(description = "会议链接")
    private String link;
}

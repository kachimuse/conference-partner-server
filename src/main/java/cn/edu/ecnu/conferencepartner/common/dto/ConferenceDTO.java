package cn.edu.ecnu.conferencepartner.common.dto;

import cn.edu.ecnu.conferencepartner.common.enums.ConferenceGradeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.Year;

@Schema(name = "新增会议数据传输对象")
@Data
public class ConferenceDTO {

    @Schema(description = "年份")
    private Integer year;

    @Schema(description = "会议简称")
    private String abbrev;

    @Schema(description = "会议等级")
    private Integer grade;

    @Schema(description = "会议全称")
    private String name;

    @Schema(description = "会议截稿日期")
    private LocalDate deadline;

    @Schema(description = "会议截稿是否延期")
    private Boolean isExtended;

    @Schema(description = "会议通知日期")
    private LocalDate notificationDate;

    @Schema(description = "会议日期")
    private LocalDate conferenceDate;

    @Schema(description = "会议地点")
    private String location;

    @Schema(description = "会议届数")
    private Integer session;

    @Schema(description = "会议链接")
    private String link;

}

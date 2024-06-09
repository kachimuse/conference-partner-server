package cn.edu.ecnu.conferencepartner.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Schema(name = "会议分页查询请求数据传输对象")
@Data
@EqualsAndHashCode(callSuper = true)
public class ConferencePageQueryDTO extends PageQueryDTO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "年份")
    private Integer year;

    @Schema(description = "等级")
    private Integer grade;
}

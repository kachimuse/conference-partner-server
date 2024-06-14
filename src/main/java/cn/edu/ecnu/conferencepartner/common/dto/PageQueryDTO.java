package cn.edu.ecnu.conferencepartner.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "分页查询请求数据传输对象")
@Data
public class PageQueryDTO {

    @Schema(description = "页码")
    private Integer pageNum = 1;

    @Schema(description = "每页展示数量")
    private Integer pageSize = 10;

    @Schema(description = "排序字段")
    private String sortedBy;

    @Schema(description = "是否升序排序")
    private Boolean isAsc;

}

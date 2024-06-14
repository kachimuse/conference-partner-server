package cn.edu.ecnu.conferencepartner.common.vo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Schema(description = "分页查询展示数据")
@Data
public class PageVO<T> {

    @Schema(description = "查询总数")
    private Long total;

    @Schema(description = "查询总页数")
    private Long pages;

    @Schema(description = "查询记录")
    private List<T> records;

    public static <PO, VO> PageVO<VO> of(Page<PO> page, Class<VO> clazzVO) {
        PageVO<VO> pageVO = new PageVO<>();
        pageVO.setTotal(page.getTotal());
        pageVO.setPages(page.getPages());

        List<PO> records = page.getRecords();
        if (records.isEmpty()) {
            pageVO.setRecords(Collections.emptyList());
            return pageVO;
        }
        pageVO.setRecords(BeanUtil.copyToList(records, clazzVO));
        return pageVO;
    }

    public static <PO, VO> PageVO<VO> of(Page<PO> page, Function<PO, VO> converter) {
        PageVO<VO> pageVO = new PageVO<>();
        pageVO.setTotal(page.getTotal());
        pageVO.setPages(page.getPages());

        List<PO> records = page.getRecords();
        if (records.isEmpty()) {
            pageVO.setRecords(Collections.emptyList());
            return pageVO;
        }

        pageVO.setRecords(records.stream().map(converter).collect(Collectors.toList()));
        return pageVO;
    }
}

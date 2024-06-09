package cn.edu.ecnu.conferencepartner.controller.admin;

import cn.edu.ecnu.conferencepartner.common.dto.ConferenceDTO;
import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResult;
import cn.edu.ecnu.conferencepartner.common.vo.ConferenceVO;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.service.IConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理端 - 会议管理")
@RestController("adminConferenceController")
@RequestMapping("/admin/conferences")
@Slf4j
@RequiredArgsConstructor
public class ConferenceController {

    private final IConferenceService conferenceService;

    @Operation(summary = "根据条件分页查询会议")
    @GetMapping
    public CommonResult<PageVO<ConferenceVO>> queryByConditionAndPage(ConferencePageQueryDTO query) {
        log.info("根据条件分页查询会议：{}", query);
        return CommonResult.success(conferenceService.queryByConditionAndPage(query));
    }

    @Operation(summary = "根据id查询指定会议")
    @GetMapping("/{id}")
    public CommonResult<ConferenceVO> queryById(@PathVariable("id") Long id) {
        log.info("根据id查询指定会议：{}", id);
        return CommonResult.success(conferenceService.queryById(id));
    }

    @Operation(summary = "新增会议")
    @PostMapping
    public CommonResult<?> save(@RequestBody ConferenceDTO conferenceDTO) {
        log.info("新增会议：{}", conferenceDTO);
        conferenceService.save(conferenceDTO);
        return CommonResult.success();
    }

    @Operation(summary = "批量删除会议")
    @DeleteMapping
    public CommonResult<?> removeByIds(@RequestParam List<Long> ids) {
        log.info("批量删除会议：{}", ids);
        conferenceService.removeByIds(ids);
        return CommonResult.success();
    }
}

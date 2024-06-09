package cn.edu.ecnu.conferencepartner.common.po;

import cn.edu.ecnu.conferencepartner.common.enums.ConferenceGradeType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.Year;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 会议 PO
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("conference")
public class Conference implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年份
     */
    @TableField("year")
    private Year year;

    /**
     * 会议简称
     */
    @TableField("abbrev")
    private String abbrev;

    /**
     * 会议等级 CCF-A为3 CCF-B为2 CCF-C为1 0为其他
     */
    @TableField("grade")
    private ConferenceGradeType grade;

    /**
     * 会议全称
     */
    @TableField("name")
    private String name;

    /**
     * 截稿日期
     */
    @TableField("deadline")
    private LocalDate deadline;

    /**
     * 是否延期
     */
    @TableField("extended")
    private Boolean isExtended;

    /**
     * 通知日期
     */
    @TableField("notification_date")
    private LocalDate notificationDate;

    /**
     * 会议日期
     */
    @TableField("conference_date")
    private LocalDate conferenceDate;

    /**
     * 会议地点
     */
    @TableField("location")
    private String location;

    /**
     * 会议届数 0表示届数未知
     */
    @TableField("session")
    private Integer session;

    /**
     * 会议链接
     */
    @TableField("link")
    private String link;

    /**
     * 会议创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}

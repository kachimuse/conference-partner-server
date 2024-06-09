package cn.edu.ecnu.conferencepartner.common.utils;

import cn.edu.ecnu.conferencepartner.common.dto.PageQueryDTO;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页查询 工具类
 * @author 龚奕玮
 * @since 2024-06-09
 */
public class PageUtil {

    public static <T> Page<T> toMybatisPlusPage(PageQueryDTO query, OrderItem... items) {
        Page<T> page = Page.of(query.getPageNum(), query.getPageSize());
        String sortedBy = query.getSortedBy();
        Boolean isAsc = query.getIsAsc();
        if (sortedBy != null && !sortedBy.isEmpty()) {
            page.addOrder(new OrderItem().setColumn(sortedBy).setAsc(isAsc));
        }
        if (items != null) {
            page.addOrder(items);
        }
        return page;
    }

}

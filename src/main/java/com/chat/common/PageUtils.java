package com.chat.common;

import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.github.pagehelper.PageInfo;

public class PageUtils {

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    public static PageResponse getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResponse pageResult = new PageResponse();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setListInfo(pageInfo.getList());
        return pageResult;
    }

}

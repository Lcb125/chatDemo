package com.chat.sys.service;

import com.chat.common.PageUtils;
import com.chat.sys.entity.AccessInfo;
import com.chat.sys.entity.ConfigInfo;
import com.chat.sys.entity.page.PageRequest;
import com.chat.sys.entity.page.PageResponse;
import com.chat.sys.mapper.ConfigInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConfigInfoService {

    @Autowired
    ConfigInfoMapper configInfoMapper;

    public String queryByCodeKey(String ifCheck) {
        return configInfoMapper.queryByCodeKey(ifCheck);
    }

    public PageResponse queryConfigInfo(PageRequest pageRequest) {

        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    public List<ConfigInfo> queryConfig(ConfigInfo configInfo){
        return configInfoMapper.queryConfig(configInfo);
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<ConfigInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setStatus("1");
        List<ConfigInfo> sysMenus = configInfoMapper.queryConfig(configInfo);
        return new PageInfo<ConfigInfo>(sysMenus);
    }

    public boolean deleteConfigInfo(ConfigInfo configInfo) {
        return configInfoMapper.deleteConfigInfo(configInfo);
    }

    public boolean updateConfigInfo(ConfigInfo configInfo) {
        configInfo.setUpdateTime(new Date());
        return configInfoMapper.updateConfigInfo(configInfo);
    }
}

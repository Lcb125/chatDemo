//package com.chat.common;
//
//import com.chat.sys.entity.AccessInfo;
//import com.chat.sys.service.AccessInfoService;
//import com.chat.sys.service.ConfigInfoService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//@EnableScheduling
//public class Task {
//
//    private final static String accessType = "P";
//
//    @Autowired
//    AccessInfoService accessInfoService;
//
//    @Autowired
//    ConfigInfoService configInfoService;
//
//    @Scheduled(cron = "5 0 0 * * ?")
//    private synchronized void resetNum(){
//
//
//        List<AccessInfo> accessInfos = accessInfoService.queryAccess(new AccessInfo());
//
//        for (AccessInfo accessInfo : accessInfos){
//            if (StringUtils.equals(accessType,accessInfo.getAccessType())){
//                String value = configInfoService.queryByCodeKey(accessInfo.getAccessLevel());
//                accessInfo.setAvailableNum(Integer.valueOf(value));
//                accessInfo.setUpdateTime(new Date());
//                accessInfoService.updateAccessInfo(accessInfo);
//            }
//
//        }
//
//        System.out.println("可用次数已重置");
//    }
//
//
//}

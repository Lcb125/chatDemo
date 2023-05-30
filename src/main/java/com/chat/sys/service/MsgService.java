package com.chat.sys.service;

import com.chat.sys.entity.MsgInfo;
import com.chat.sys.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgService {

    @Autowired
    MsgMapper msgMapper;

    public List<MsgInfo> queryMsgInfo(MsgInfo msgInfo) {
        return msgMapper.queryMsg(msgInfo);
    }
}

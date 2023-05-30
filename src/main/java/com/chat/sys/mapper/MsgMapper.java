package com.chat.sys.mapper;

import com.chat.sys.entity.MsgInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgMapper {


    List<MsgInfo> queryMsg(MsgInfo msgInfo);
}

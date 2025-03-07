package com.example.basicboardv2prac.mapper;

import com.example.basicboardv2prac.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void saved(Member member);
    Member findByUserId(String userId);
}

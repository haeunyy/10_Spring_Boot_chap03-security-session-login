package com.greedy.security.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.security.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO findMemberById(String username);

}

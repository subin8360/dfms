package com.spring.dfms.admin.member.service;

import java.util.List;
import java.util.Map;

import com.spring.dfms.admin.member.dto.AdminDto;
import com.spring.dfms.member.dto.MemberDto;

public interface AdminMemberService {
	
	public boolean adminLogin(AdminDto adminDto) throws Exception;
	public List<MemberDto> getMemberList() throws Exception;
	public void modifyMemberInfo(Map<String,String> memberMap) throws Exception;
	

}

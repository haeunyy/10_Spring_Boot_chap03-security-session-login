package com.greedy.security.member.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/* TBL_MEMBER */
@Data
public class MemberDTO {
	
	private int no;									//회원번호
	private String id;								//회원아이디
	private String pwd;								//회원비밀번호
	private String tempPwdYn;						//임시비밀번호여부
	private Date pwdChangedDatetime;				//회원비밀번호변경일자
	private String pwdExpDate;						//회원비밀번호만료일자
	private String name;							//회원이름
	private Date registDatetime;					//회원가입일시
	private int accumLoginCount;					//누적로그인횟수
	private int loginFailedCount;					//로그인연속실패횟수
	private String accLockYn;						//계정잠금여부
	private String accInactiveYn;					//계정비활성화여부
	private String accExpDate;						//계정만료일자
	private String accExpYn;						//계정만료여부
	private Date accSecessionDatetime;				//계정탈퇴일시
	private String accSecessionYn;					//계정탈퇴여부
	
	/* TBL_MEMBER_ROLE - 한 멤버는 여러 권한을 가질 수 있다 */
	private List<MemberRoleDTO> memberRoleList;		//권한 목록
	
}

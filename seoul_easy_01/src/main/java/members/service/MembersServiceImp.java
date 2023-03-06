package members.service;
//회원가입8

import common.exception.WrongEmailPasswordException;

import members.dao.MembersDAO;
import members.dto.AuthInfo;
import members.dto.ChangePwdCommand;
import members.dto.MembersDTO;

public class MembersServiceImp implements MembersService{
	private MembersDAO membersDao; //MembersDAO를받음
	
	public MembersServiceImp() {
		
	}
	
	public void setMembersDao(MembersDAO membersDao) {
		this.membersDao = membersDao;
	}

	//회원가입 후 바로 로그인처리하기
	@Override
	public AuthInfo addMemberProcess(MembersDTO dto) {
		membersDao.insertMember(dto);//회원등록하고
		return new AuthInfo(dto.getMemberEmail(), dto.getMemberName(), dto.getMemberPass());//로그인 처리 해 리턴
	}

	//로그인
	@Override
	public AuthInfo loginProcess(MembersDTO dto) {
		MembersDTO member = membersDao.selectByEmail(dto.getMemberEmail());//null이 아니면 회원
		if(member == null) {//null이면 비회원(해당 이메일 없음)
			//System.out.println("회원이 아닙니다.");
			throw new WrongEmailPasswordException();
		}
		
		if(!member.matchPassword(dto.getMemberPass())) {//입력한 비번과 DB에 저장된 비번이 일치하는지 확인
			//System.out.println("비밀번호가 다릅니다.");
			throw new WrongEmailPasswordException();
		}
		
		return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
		//Authinfo를 컨트롤로 넘겨줌
	}

	//회원정보수정폼2
	@Override
	public MembersDTO updateMemberProcess(String memberEmail) {
		return membersDao.selectByEmail(memberEmail); //이 이메일에 해당되는 데이터값 리턴
	}

	//회원정보수정처리3
	@Override
	public AuthInfo updateMemberProcess(MembersDTO dto) {
		membersDao.updateMember(dto);
		MembersDTO member = membersDao.selectByEmail(dto.getMemberEmail());
	    return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
		
	}

	//비밀번호 변경 처리2
	@Override
	public void updatePassProcess(String memberEmail, ChangePwdCommand changePwd) {
	//비밀번호 변경 처리 6끝
		MembersDTO member = membersDao.selectByEmail(memberEmail);
		if(member == null)//멤버가 아니면
			throw new WrongEmailPasswordException();//exception띄우고
		//본인 확인위해 입력한 패스워드값이 일치하는지 확인
		member.changePassword(changePwd.getCurrentPassword(), changePwd.getNewPassword());
		//맞으면 넘겨줌
		membersDao.updateByPass(member);
		
		
		
	}

}





















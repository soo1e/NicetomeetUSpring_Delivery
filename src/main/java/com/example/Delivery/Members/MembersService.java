package com.example.Delivery.Members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembersService {

    @Autowired
    private SpringDataJPAMembersRepository membersRepository;

    // 전체 멤버 조회
    public List<Members> getAllMembers() {
        return membersRepository.findAll();
    }

    // 특정 멤버 조회
    public Members getMemberById(Long id) {
        Optional<Members> optionalMember = membersRepository.findById(id);
        return optionalMember.orElse(null);
    }

    // 멤버 등록
    public Members addMember(Members member) {
        return membersRepository.save(member);
    }

    // 멤버 삭제
    public void deleteMember(Long id) {
        membersRepository.deleteById(id);
    }

    // 멤버 수정
    public Members updateMember(Long id, Members updatedMember) {
        if (membersRepository.existsById(id)) {
            updatedMember.setMemberId(id);
            return membersRepository.save(updatedMember);
        } else {
            return null;
        }
    }
}


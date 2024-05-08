package com.example.Delivery.Members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    public Optional<Members> getMemberById(Long memberId) {
        return membersRepository.findById(memberId);
    }

    // 멤버 저장
    public void saveMember(Members member) {
        membersRepository.save(member);
    }

    // 멤버 수정
    public Members updateMember(Long memberId, Members updatedMember) {
        if (membersRepository.existsById(memberId)) {
            updatedMember.setMemberId(memberId);
            return membersRepository.save(updatedMember);
        } else {
            throw new NoSuchElementException("해당 id의 멤버가 존재하지 않습니다.");
        }
    }

    // 멤버 삭제
    public void deleteMember(Long memberId) {
        if (membersRepository.existsById(memberId)) {
            membersRepository.deleteById(memberId);
        } else {
            throw new NoSuchElementException("해당 id의 멤버가 존재하지 않습니다.");
        }
    }
}


package com.example.Delivery.Members;

import com.example.Delivery.Members.DTO.MemberRequestDTO;
import com.example.Delivery.Members.Error.DuplicateEmailException;
import com.example.Delivery.Members.Error.DuplicatePhoneNumberException;
import com.example.Delivery.Members.Error.DuplicateUsernameException;
import com.example.Delivery.Members.Error.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembersService {

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
    public void saveMember(MemberRequestDTO memberRequestDTO) throws DuplicateEmailException {
        // 사용자명 중복 체크
        if (membersRepository.existsByUsername(memberRequestDTO.getUsername())) {
            throw new DuplicateUsernameException(ErrorMessage.DUPLICATE_USERNAME.getMessage());
        }

        // 이메일 중복 체크
        if (membersRepository.existsByEmail(memberRequestDTO.getEmail())) {
            throw new DuplicateEmailException(ErrorMessage.DUPLICATE_EMAIL.getMessage());
        }

        // 전화번호 중복 체크
        if (membersRepository.existsByPhoneNumber(memberRequestDTO.getPhoneNumber())) {
            throw new DuplicatePhoneNumberException(ErrorMessage.DUPLICATE_PHONE_NUMBER.getMessage());
        }
        {
            Members member = new Members();
            member.setUsername(memberRequestDTO.getUsername());
            member.setEmail(memberRequestDTO.getEmail());
            member.setPassword(memberRequestDTO.getPassword());
            member.setPhoneNumber(memberRequestDTO.getPhoneNumber());
            member.setAddress(memberRequestDTO.getAddress());
            member.setRole(Members.Role.MEMBER);

            membersRepository.save(member);
        }
    }

        // 멤버 수정
        public Members updateMember (Long memberId, Members updatedMember){
            if (membersRepository.existsById(memberId)) {
                updatedMember.setMemberId(memberId);
                return membersRepository.save(updatedMember);
            } else {
                throw new NoSuchElementException("해당 id의 멤버가 존재하지 않습니다.");
            }
        }

        // 멤버 삭제
        public void deleteMember (Long memberId){
            if (membersRepository.existsById(memberId)) {
                membersRepository.deleteById(memberId);
            } else {
                throw new NoSuchElementException("해당 id의 멤버가 존재하지 않습니다.");
            }
        }
    }


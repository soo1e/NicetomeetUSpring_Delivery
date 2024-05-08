package com.example.Delivery.Members;

import com.example.Delivery.Members.Error.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@AllArgsConstructor

public class MembersController {

    private MembersService membersService;


    // 모든 멤버 조회
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        List<Members> members = membersService.getAllMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.MEMBER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(members, HttpStatus.OK);
        }
    }

    // 특정 멤버 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") Long id) {
        Members member = membersService.getMemberById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.MEMBER_NOT_FOUND.getMessage()));
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // 멤버 등록
    @PostMapping
    public ResponseEntity<String> saveMember(@RequestBody Members member) {
        try {
            membersService.saveMember(member);
            return new ResponseEntity<>(ErrorMessage.MEMBER_REGISTRATION_SUCCESS.getMessage(), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(ErrorMessage.MEMBER_REGISTRATION_FAILED.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 멤버 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody Members updatedMember) {
        try {
            Members member = membersService.updateMember(id, updatedMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ErrorMessage.MEMBER_UPDATE_FAILED.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 멤버 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        try {
            membersService.deleteMember(id);
            return ResponseEntity.ok(ErrorMessage.MEMBER_DELETE_SUCCESS.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.MEMBER_DELETE_FAILED.getMessage());
        }
    }
}

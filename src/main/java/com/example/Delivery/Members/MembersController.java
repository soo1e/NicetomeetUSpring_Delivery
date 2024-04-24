package com.example.Delivery.Members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    // 모든 멤버 조회
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        List<Members> members = membersService.getAllMembers();
        if (!members.isEmpty()) {
            return new ResponseEntity<>(members, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 멤버가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 특정 멤버 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") Long id) {
        Members member = membersService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 멤버가 없습니다.", HttpStatus.NOT_FOUND);        }
    }

    // 멤버 등록
    @PostMapping
    public ResponseEntity<Members> addMember(@RequestBody Members member) {
        Members addedMember = membersService.addMember(member);
        return new ResponseEntity<>(addedMember, HttpStatus.CREATED);
    }

    // 멤버 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        try {
            membersService.deleteMember(id);
            return new ResponseEntity<>("멤버 삭제에 성공했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 멤버 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody Members updatedMember) {
        Members member = membersService.updateMember(id, updatedMember);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("멤버 수정에 실패했습니다.", HttpStatus.NOT_FOUND);
        }
    }
}

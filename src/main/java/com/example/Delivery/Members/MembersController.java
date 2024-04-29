package com.example.Delivery.Members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MembersController {

    private MembersService membersService;

    @Autowired
    public void MemberController(MembersService membersService) {
        this.membersService = membersService;
    }

    // 모든 멤버 조회
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        List<Members> membersList = membersService.getAllMembers();
        if (!membersList.isEmpty()) {
            return new ResponseEntity<>(membersList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 멤버가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 특정 멤버 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") Long id) {
        Optional<Members> member = membersService.getMemberById(id);
        if (member.isPresent()) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 멤버가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 멤버 등록
    @PostMapping
    public ResponseEntity<String> saveMember(@RequestBody Members member) {
        try {
            membersService.saveMember(member);
            return new ResponseEntity<>("멤버를 성공적으로 등록했습니다.", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 멤버 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody Members updatedMember) {
        try {
            Members member = membersService.updateMember(id, updatedMember);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 멤버 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        try {
            membersService.deleteMember(id);
            return ResponseEntity.ok("멤버 삭제가 완료되었습니다");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 id의 멤버가 존재하지 않습니다.");
        }
    }
}

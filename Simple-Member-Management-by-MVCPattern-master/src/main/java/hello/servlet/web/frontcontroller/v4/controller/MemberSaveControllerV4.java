package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);  // 비즈니스 로직 - 멤버 생성
        memberRepository.save(member);              // 비즈니스 로직 - 멤버 저장

        model.put("member", member);            // V3 -> V4 될 때 개선된 부분  (모델에 멤버 넣기)
        return "save-result";                   // V3 -> V4 될 때 개선된 부분  (끝에 주소 이름만 반환하기)
    }
}

package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();   // 조회1 - 호출할 때 마다 객체 생성
        MemberService memberService2 = appConfig.memberService();   // 조회2 - 호출할 때 마다 객체 생성

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }
}

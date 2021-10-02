package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringSingleton {

    @Test
    void realSpringSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        System.out.println("meberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isSameAs(memberService2);

        AppConfig appConfig = new AppConfig();
        MemberService memberService3 = appConfig.memberService();   // 조회1 - 호출할 때 마다 객체 생성
        MemberService memberService4 = appConfig.memberService();   // 조회2 - 호출할 때 마다 객체 생성
        System.out.println("memberService3 = " + memberService3);
        System.out.println("memberService4 = " + memberService4);
        assertThat(memberService3).isNotSameAs(memberService4);
    }

}

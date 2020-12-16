
package hello.core.singleton;
import hello.core.AppConfig;
import hello.core.member.MemberService; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
//1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
//2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
//참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);
        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
         SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println("singletonService1 = " + singletonService1); System.out.println("singletonService2 = " + singletonService2);
        // singletonService1 == singletonService2
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }
    @Test
    @DisplayName("스프링 싱클톤 테스트")
    public void springContainer()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean(MemberService.class);
//2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean(MemberService.class);
//참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);
        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
    @Test
    @DisplayName("스테이트풀 테스트 ")
    public void statefulTest()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1=ac.getBean(StatefulService.class);
        StatefulService s2=ac.getBean(StatefulService.class);
        s1.order("윤석",10000);
        s2.order("소정",20000);
        System.out.println(s1.getPrice());
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
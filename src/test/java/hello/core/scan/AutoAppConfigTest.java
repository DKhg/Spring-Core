package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        //@ComponentScan 만 붙여놓으면, 이 클래스(AutoAppConfig)가 속한 패키지를 기준으로 자동 스캔.
        //그 파일의 패키지포함 하위에 있는 모든 @Component, @Service, @Repository, @Controller 등의 빈을 찾아 자동 등록.
        //@Autowired 가 붙은 필드나 생성자가 있으면, 자동으로 빈을 주입.
        //권장 방법 : 클래스의 위치를 프로젝트 최상단에 두기
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        //빈이 생성되고 의존성 주입이 완료된 후 실행되는 초기화 메서드.
        //SingletonBean 이 컨테이너에 등록된 직후 실행됨.
        //즉, ac.getBean(SingletonBean.class) 을 하기 전에 이미 실행됨.
        //실행 시 "SingletonBean.init" 이 출력됨.
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        //스프링 컨테이너가 종료되기 직전에 호출되는 메서드.
        //ac.close(); 를 호출하면 이 메서드가 실행됨.
        //실행 시 "SingletonBean.destroy" 가 출력됨.
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}

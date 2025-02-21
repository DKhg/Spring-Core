package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary 우선순위를 정하는 방법 @Autowired 시 여러 빈이 매칭되면 이 어노테이션이 달린 필드를 우선 선택
public class RateDiscountPolicy implements DiscountPolicy{

    private int disCountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * disCountPercent / 100;
        } else {
            return 0;
        }
    }
}

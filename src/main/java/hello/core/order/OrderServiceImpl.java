package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor -> 18~19 Line 처럼 final 붙은 필드를 가지고 24~28 Line 처럼 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

    //Interface 의존하도록 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //DIP 위반 ( interface 에 의존해야하는데 implement 에도 의존 )
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Autowired //type 을 가져온다 discountPolicy 의 type 이 rate 랑 fix 두 가지가 있어서 두개가다 빈 등록이 되어있으면 오류가 뜨고 만약
               //discountPolicy(필드명, 파라미터명)의 이름을 두가지중 하나로 설정하면 그 이름을 type 을 가져온다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

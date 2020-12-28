package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder()
    {
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new RateDiscountPolicy());
//        orderService.createOrder(1l,"1",1000);
    }
}
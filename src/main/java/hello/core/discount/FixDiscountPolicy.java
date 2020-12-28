package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class FixDiscountPolicy implements DiscountPolicy
{
    private final int dis=1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()== Grade.VIP)
        {
            return dis;
        }
        else {
            return 0;
        }
    }
}

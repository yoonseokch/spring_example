package hello.core.scope;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean s1 = ac.getBean(PrototypeBean.class);
        s1.addCount();
        assertThat(s1.getCount()).isEqualTo(1);
        PrototypeBean s2 = ac.getBean(PrototypeBean.class);
        s2.addCount();
        assertThat(s2.getCount()).isEqualTo(1);

    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean b1 = ac.getBean(ClientBean.class);
        int count1 = b1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean b2 = ac.getBean(ClientBean.class);
        int count2 = b2.logic();
        assertThat(count2).isEqualTo(1);
    }
    @Scope("singleton")
    static class ClientBean{
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;
        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addCount() {
            this.count++;
        }
        public int getCount() {
            return this.count;
        }
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}

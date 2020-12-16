package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("메타정보 확인")
    void findAppBean(){
        String[] beanDefinition = ac.getBeanDefinitionNames();
        for (String name : beanDefinition){
            BeanDefinition beanDefinition1 = ac.getBeanDefinition(name);
            if (beanDefinition1.getRole()==BeanDefinition.ROLE_APPLICATION)
            {
                System.out.println("이름 : "+name + "역할 : "+ beanDefinition1);
            }
        }
    }
}

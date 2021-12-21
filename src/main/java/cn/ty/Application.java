package cn.ty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy  //开启Zuul的网关功能
@EnableDiscoveryClient  //设置为eureka客户端
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

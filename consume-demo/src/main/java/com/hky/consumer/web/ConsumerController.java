package com.hky.consumer.web;

import com.hky.consumer.client.UserClient;
import com.hky.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author iforeverhz
 */
/*
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        //根据服务id获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host+":"+port +"/user/"+ id;
        System.out.println(url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
*/

/*负载均衡*/

/*
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RibbonLoadBalancerClient client;



    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        //根据服务id获取实例
        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
        ServiceInstance instance = client.choose("user-service");
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host+":"+port +"/user/"+ id;
        System.out.println(url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
*/


/*@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    @GetMapping("/{id}")
//    @HystrixCommand(fallbackMethod ="queryByIdFallback" )
    @HystrixCommand(commandProperties = {
          //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000") //自定义超时时长
    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), //请求阈值
    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //休眠时间窗
    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"), //错误阈值百分比

    }) //启用熔断
    public String queryById(@PathVariable("id") Long id) {

        if (id%2==0){
            throw new RuntimeException();
        }
        long begin = System.currentTimeMillis();

        String url = "http://user-service/user/" + id;
        System.out.println(url);
        String user = restTemplate.getForObject(url, String.class);
        long end = System.currentTimeMillis();
        // 记录访问用时：
        logger.info("访问用时：{}", end - begin);
        return user;
    }

    public String queryByIdFallback( Long id) {
        return "服务器拥挤";
    }

    public String defaultFallback() {
        return "服务器拥挤";
    }
}*/


@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private UserClient userClient;
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);
    }

}
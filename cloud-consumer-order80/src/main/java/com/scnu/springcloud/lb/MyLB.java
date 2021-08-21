package com.scnu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstances) {
        int serverCount = serviceInstances.size();
        int nextServerIndex = this.incrementAndGetModulo(serverCount);
        return serviceInstances.get(nextServerIndex);
    }

    private int incrementAndGetModulo(int size){
        int current;
        int next;
        do{
            current = nextServerCyclicCounter.get();
            next = (current + 1) % size;
        }while(!nextServerCyclicCounter.compareAndSet(current,next));
        return next;
    }
}

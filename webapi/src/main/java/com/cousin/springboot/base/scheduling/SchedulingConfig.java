package com.cousin.springboot.base.scheduling;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by cousin on 2016/11/28.
 */
@Configurable
@EnableScheduling
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduler(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>SchedulingConfig.scheduler()");
    }

}

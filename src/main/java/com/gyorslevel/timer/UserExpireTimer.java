package com.gyorslevel.timer;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author dave00
 */
public class UserExpireTimer extends QuartzJobBean {

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jec) throws org.quartz.JobExecutionException {
        
        UserExpireController controller = (UserExpireController) jec.getJobDetail().getJobDataMap().get("controller");
        controller.expireUsers();
        
    }
}
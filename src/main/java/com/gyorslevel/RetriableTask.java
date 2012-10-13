/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyorslevel;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import org.apache.log4j.Logger;

public class RetriableTask<T> implements Callable<T> {

    private static Logger logger = Logger.getLogger(RetriableTask.class);
    private Callable<T> task;
    private int numberOfRetries; // total number of tries
    private int numberOfTriesLeft; // number left
    private long timeToWait; // wait interval

    public RetriableTask(int numberOfRetries, long timeToWait,
            Callable<T> task) {
        this.numberOfRetries = numberOfRetries;
        this.numberOfTriesLeft = numberOfRetries;
        this.timeToWait = timeToWait;
        this.task = task;
    }

    public T call() throws Exception {
        while (true) {
            try {
                logger.info("Executing task. Trials left: " + numberOfTriesLeft);
                return task.call();
            } catch (InterruptedException e) {
                logger.error(e);
                throw e;
            } catch (CancellationException e) {
                logger.error(e);
                throw e;
            } catch (Exception e) {
                numberOfTriesLeft--;
                if (numberOfTriesLeft == 0) {
                    throw new RuntimeException(numberOfRetries
                            + " attempts to retry failed at " + timeToWait
                            + "ms interval", e);
                }
                Thread.sleep(timeToWait);
            }
        }
    }
}
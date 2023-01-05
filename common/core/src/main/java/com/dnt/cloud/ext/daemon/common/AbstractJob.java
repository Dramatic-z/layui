package com.dnt.cloud.ext.daemon.common;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.dnt.cloud.CommonConstants;
import com.dnt.cloud.common.lang.diagnostic.Profiler;

/**
 * 抽象任务
 * @author hazyhao
 *
 */
public abstract class AbstractJob implements CommonConstants {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    boolean          run;

    public synchronized void run() {
        if (!isRun()) {
            return;
        }
        MDC.put(LOG_PREFIX,
            String.format("任务-%s-%s", getJobName(), UUID.randomUUID().toString().replace("-", "")));
        Profiler.start();
        try {
            logger.info("执行开始");
            execute();
        } catch (Exception e) {
            logger.error("执行异常", e);
        } finally {
            Profiler.release();
            logger.info("执行结束(耗时: {} ms)", Profiler.getDuration());
            if (Profiler.getDuration() > 3000) {
                logger.info("时间分析: {}", Profiler.dump());
            }
            MDC.clear();
        }
    }

    protected boolean isRun() {
        return run;
    }

    protected void setRun(boolean run) {
        this.run = run;
    }

    /** 
     * @return 任务名称，用于日志记录
     */
    protected abstract String getJobName();

    /** 实际执行 */
    protected abstract void execute() throws Exception;

}

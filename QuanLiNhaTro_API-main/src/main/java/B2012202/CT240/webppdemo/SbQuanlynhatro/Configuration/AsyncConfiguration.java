package B2012202.CT240.webppdemo.SbQuanlynhatro.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.util.concurrent.Executor;

//Cau hinh cho viec thuc thi cac phuong phap bat dong bo
@Configuration

@EnableAsync
//Cho phep Spring kha nang chay cac phuong thuc bat dong bo

public class AsyncConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);
    @Bean(name = "taskExecutor")
    //Cho phep tuy chinh luong thuc thi
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);//so luong co dinh dong thoi
        executor.setMaxPoolSize(8);//so luong toi da dong thoi
        executor.setQueueCapacity(100);//so luong trong hang doi
        executor.setThreadNamePrefix("ThreadASYNCHostel-");
        executor.initialize();
        return executor;
    }
}

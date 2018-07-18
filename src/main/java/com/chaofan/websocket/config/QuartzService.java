package com.chaofan.websocket.config;

import com.chaofan.websocket.Web.SocketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Chaofan at 2018/7/18 16:12
 * email:chaofan2685@qq.com
 * 此类为一个定时任务，使用前须在启动类上添加@EnableScheduling注解
 **/
@Component
public class QuartzService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzService.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow(){
        logger.debug("开始查询需要删除的图片。。。。。。。。。。");
        Map<Long,String> img = SocketController.img;
        Long now = System.currentTimeMillis()-60000;
        Iterator<Map.Entry<Long, String>> it = img.entrySet().iterator();
        int a = 0;
        while(it.hasNext()){
            Map.Entry<Long, String> entry = it.next();
            if (entry.getKey() < now){
                if (deleteFile(entry.getValue())){
                    it.remove();
                    a++;
                }
            }
        }
        logger.debug("删除任务执行完毕，共删除"+a+"张图片");
    }

    private boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.debug("图片"+fileName+"删除成功");
                return true;
            } else {
                logger.debug("图片"+fileName+"删除成功");
                return false;
            }
        } else {
            logger.debug("图片"+fileName+"删除失败");
            return false;
        }
    }

}

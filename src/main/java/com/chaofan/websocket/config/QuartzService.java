package com.chaofan.websocket.config;

import com.chaofan.websocket.Web.SocketController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Chaofan at 2018/7/18 16:12
 * email:chaofan2685@qq.com
 **/
@Component
public class QuartzService {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void timerToNow(){
        Map<Long,String> img = SocketController.img;
        Long now = System.currentTimeMillis()-60000;
        Iterator<Map.Entry<Long, String>> it = img.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Long, String> entry = it.next();
            if (entry.getKey() < now){
                if (deleteFile(entry.getValue())){
                    it.remove();
                }
            }
        }

    }

    private boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

}

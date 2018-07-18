package com.chaofan.websocket.Web;

import cn.hutool.core.util.RandomUtil;
import com.chaofan.websocket.module.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Chaofan at 2018/7/6 9:39
 * email:chaofan2685@qq.com
 **/

@RestController
@RequestMapping("/ws")
public class SocketController {

    //图片保存路径
    private String imgPath = SocketController.class.getResource("/static/img/").getFile();

    public static Map<Long,String> img = new HashMap();

    /**
     * 根据房间号获得其中的用户
     * @param room 房间号
     * @return
     */
    @RequestMapping("/online")
    public Map<String,Object> online(String room){
        Map<String,Object> result = new HashMap<>();
        CopyOnWriteArraySet<User> rooms = MyWebSocket.UserForRoom.get(room);
        List<String> nicks = new ArrayList<>();
        rooms.forEach(user -> nicks.add(user.getNickname()));
        if (rooms != null){
            result.put("onlineNum",rooms.size());
            result.put("onlineUsera",nicks);
        }else {
            result.put("onlineNum",0);
            result.put("onlineUsera",null);
        }
        return result;
    }


    /**
     * 实现文件上传
     * */
    @RequestMapping("/fileUpload")
    public Map<String,Object> fileUpload(HttpServletRequest request, @RequestParam("fileName") MultipartFile file){
        Map<String,Object> result = new HashMap<>();
        //获取项目访问路径
        String root = request.getRequestURL().toString().replace(request.getRequestURI(),"");
        if(file.isEmpty()){
            return null;
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        //重命名文件
        String imgName = RandomUtil.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(imgPath + imgName);
        img.put(System.currentTimeMillis(),imgPath + imgName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            //返回图片访问路径
            result.put("url",root +"/img/" + imgName);
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

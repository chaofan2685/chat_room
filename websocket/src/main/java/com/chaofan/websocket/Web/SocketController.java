package com.chaofan.websocket.Web;

import com.chaofan.websocket.module.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

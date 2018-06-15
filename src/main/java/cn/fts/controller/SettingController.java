package cn.fts.controller;

import cn.fts.utils.Constant;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ww on 2018/6/15.
 */
@Controller
@RequestMapping(value = "/setting",produces = {"application/json;charset=UTF-8"})
public class SettingController {

    @RequestMapping("/current")
    @ResponseBody
    public String list() {
        return JSON.toJSONString(Constant.settings());
    }
}

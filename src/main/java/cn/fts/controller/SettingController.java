package cn.fts.controller;

import cn.fts.config.ConstantAd;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ww on 2018/6/15.
 */
@Controller
@RequestMapping(value = "/setting",produces = {"application/json;charset=UTF-8"})
public class SettingController {

    @Autowired
    private ConstantAd constantAd;

    @RequestMapping("/current")
    @ResponseBody
    public String list() {
        return JSON.toJSONString(constantAd.settings());
    }

    @RequestMapping("/doRefresh")
    @ResponseBody
    public String doRefresh() {
        constantAd.refresh();
        return JSON.toJSONString(constantAd.settings());
    }
}

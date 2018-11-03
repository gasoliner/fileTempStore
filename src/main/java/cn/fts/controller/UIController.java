package cn.fts.controller;

import cn.fts.plugin.TimeTask;
import cn.fts.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 万洪基 on 2017/6/25.
 */
@Controller
public class UIController {

    /**
     * todo 此种方式有并发问题
     */
    private volatile boolean timeTaskIsRunning = false;

    @Autowired
    private TimeTask timeTask;

    @RequestMapping("/")
    public String showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!timeTaskIsRunning) {
            timeTask.start();
            timeTaskIsRunning = true;
        }
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}

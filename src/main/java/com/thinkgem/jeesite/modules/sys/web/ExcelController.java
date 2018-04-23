package com.thinkgem.jeesite.modules.sys.web;

import com.thinkgem.jeesite.common.excel.ExcelUtils;
import com.thinkgem.jeesite.common.service.ExcelService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import com.thinkgem.jeesite.modules.sys.entity.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/4/15.
 */
@Controller("index")
public class ExcelController extends BaseController {

    @Autowired
    private ExcelService jsonService;


    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    @ResponseBody
    public StringBuffer testExcel(Excel excel, @RequestParam MultipartFile file, HttpServletResponse response, HttpServletRequest request
    , ModelMap modelMap) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        List<String[]> res = ExcelUtils.readExcel(file);

        System.out.println("测试已经进入该方法");
        String json = null;
        String[] array = null;
        List list = new ArrayList();
        String[] str = null;
        StringBuffer buffer = new StringBuffer();
        if (res != null && res.size() > 0) {
            //存库或者其他操作
            JSONArray jsonArray = JSONArray.fromObject(res);

            json = jsonArray.toString();

            System.out.println(json);
            System.out.println("-------------------");
            System.out.println(jsonArray);
        }
        for (int j = 0; j < res.size(); j++) {

            str = res.get(j);
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i]);
                buffer.append(str[i]);
            }
        }

        Map<String,Object> map =new HashMap<String, Object>();
        String test ="hello world";
        map.put("file",file);
//        map.put("res",res);
        modelMap.addAttribute("test",test);
        modelMap.addAttribute("map",map);


//        modelMap.addAttribute("str",list);
//        request.setAttribute("list", str);
        request.setAttribute("res",res);
        request.getRequestDispatcher("/WEB-INF/page/show.jsp").forward(request,response);

        return buffer;
        //这种只是将数据直接返回到网页前端
//        return json;

    }

    @RequestMapping(value = "/mytest.do",method = RequestMethod.POST)
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file, ModelMap modelMap) throws ServletException, IOException {

        System.out.println("您已进入该方法！！！");
        String filename = file.getOriginalFilename().toString();
        System.out.println(filename);
  /*      Map<String,String> map = new HashMap<String, String>();
        map.put("A","what");
        map.put("B","the");
        map.put("C","fuck");
        modelMap.addAttribute("filename",filename);
        modelMap.addAttribute("map",map);
        request.getRequestDispatcher("WEB-INF/page/pic.jsp").forward(request,response);
  */
        String stest = ExcelUtils.responseExcel(file);
//        System.out.println(stest);
//        modelMap.addAttribute("test",stest); //这个与下面的作用是一样的
        request.setAttribute("test",stest);

        //在这里转换为json格式存到数据库,而这个仅仅是存储一条数据
//        jsonService.addJson(stest);
        List<Json> list = new ArrayList<Json>();
        String[] split = stest.split("<tr>|</tr>");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            list.add(new Json(i,split[i]));

        }

        for (int i = 0; i < list.size(); i++) {
            JSONObject object = JSONObject.fromObject(list.get(i));
            jsonService.addJson(object.toString());
        }

//        request.getRequestDispatcher("WEB-INF/page/show.jsp").forward(request,response);
        return "show";
    }

}

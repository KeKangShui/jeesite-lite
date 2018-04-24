package com.thinkgem.jeesite.modules.sys.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.thinkgem.jeesite.common.excel.ExcelUtils;
import com.thinkgem.jeesite.common.persistence.PageFactory;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import com.thinkgem.jeesite.modules.sys.entity.Json;
import com.thinkgem.jeesite.modules.sys.service.ExcelService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
@Controller
@RequestMapping(value = "${adminPath}/sys/excel")
public class ExcelController extends BaseController {

    @Autowired
    private ExcelService excelService ;


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


    @ModelAttribute
    public Excel get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return excelService.selectById(id);
        } else {
            return new Excel();
        }
    }

//    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = { "/list", "" })
    public String list(Model model) {
        List<String> typeList = excelService.findTypeList();
        model.addAttribute("typeList", typeList);
        return "modules/sys/excelList";
    }

    @ResponseBody
//    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "data")
    public Map listData(Excel excel) {
        Page<Excel> page = excelService.findPage(new PageFactory<Excel>().defaultPage(), excel);
        return jsonPage(page);
    }

    @ResponseBody
//    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "query")
    public List<Excel> query(@RequestParam(required=false) String type) {
        Excel excel = new Excel();
//        excel.setType(type);
        return excelService.findList(excel);
    }

    @RequestMapping(value = "/up")
    public String up(){
        return "modules/sys/upload";
    }

//    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "/form")
    public String form(Excel excel, Model model) {
        model.addAttribute("excel", excel);
        return "modules/sys/excelForm";
    }


    @RequestMapping(value = "/mytest.do",method = RequestMethod.POST)
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file, ModelMap modelMap) throws ServletException, IOException {

        System.out.println("您已进入该方法！！！");
        String filename = file.getOriginalFilename().toString();
        System.out.println(filename);

        String stest = ExcelUtils.responseExcel(file);
        request.setAttribute("test",stest);

        //在这里转换为json格式存到数据库,而这个仅仅是存储一条数据
//        jsonService.addJson(stest);
        List<Json> list = new ArrayList<Json>();
        String[] split = stest.split("<tr>|</tr>");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            list.add(new Json(split[i]));
        }
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = JSONObject.fromObject(list.get(i));
            excelService.addJson(object.toString());
        }
//        return "modules/sys/show";
        return "modules/sys/excelList";
    }



}

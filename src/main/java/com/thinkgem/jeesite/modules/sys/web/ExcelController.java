package com.thinkgem.jeesite.modules.sys.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.excel.ExcelUtils;
import com.thinkgem.jeesite.common.persistence.PageFactory;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Excel;
import com.thinkgem.jeesite.modules.sys.entity.Json;
import com.thinkgem.jeesite.modules.sys.service.ExcelService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/4/15.
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/excel/")
public class ExcelController extends BaseController {

    @Autowired
    private ExcelService excelService ;

    @ModelAttribute("excel")
    public Excel get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return excelService.selectById(id);
        } else {
            return new Excel();
        }
    }

    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = { "list", "" })
    public String list(Model model) {
        List<String> typeList = excelService.findIdList();
        model.addAttribute("typeList", typeList);
        return "modules/sys/excel/excel_List";
    }

    @ResponseBody
    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "data")
    public Map listData(Excel excel) {
        Page<Excel> page = excelService.findPage(new PageFactory<Excel>().defaultPage(), excel);
        return jsonPage(page);
    }

    @ResponseBody
    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "query")
    public List<Excel> query(@RequestParam(required=false) String id) {
        Excel excel = new Excel();
        excel.setId(id);
        return excelService.findList(excel);
    }



    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "form")
    public String form(Excel excel, Model model) {
        model.addAttribute("excel", excel);
        return "modules/sys/excel/excel_Form";
    }

    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "show")
    public String show(Excel excel, Model model) {
        model.addAttribute("excel", excel);
        return "modules/sys/excel/show";
    }

    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "/up")
    public String up(){
        return "modules/sys/excel/upload";
    }

    @RequiresPermissions("sys:excel:edit")
    @RequestMapping(value = "save")
    public String save(Excel excel, Model model, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/excel/?type=" + excel.getType();
        }
        if (!beanValidator(model, excel)) {
            return form(excel, model);
        }
        excelService.save(excel);
        addMessage(redirectAttributes, "保存Excel'"  + "'成功");
        return "redirect:" + adminPath + "/sys/excel/?type=" + excel.getType();
    }

    @RequiresPermissions("sys:excel:edit")
    @RequestMapping(value = "delete")
    public String delete(Excel excel, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/excel/";
        }
        excelService.delete(excel);
        addMessage(redirectAttributes, "删除Excel成功");
        return "redirect:" + adminPath + "/sys/excel/?type=" + excel.getType();
    }

    @RequiresPermissions("sys:excel:edit")
    @RequestMapping(value = "batchDelete")
    public String batchDelete(String ids, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/sys/excel/";
        }
        excelService.batchDelete(ids);
        addMessage(redirectAttributes, "批量删除Excel成功");
        return "redirect:" + adminPath + "/sys/excel/";
    }



    @RequiresPermissions("sys:excel:view")
    @RequestMapping(value = "/mytest.do",method = RequestMethod.POST)
    public String test(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file, ModelMap modelMap) throws ServletException, IOException {
        List<Json> list = new ArrayList<Json>();

        String stest = ExcelUtils.responseExcel(file);
        request.setAttribute("test",stest);

        //在这里转换为json格式存到数据库,而这个仅仅是存储一条数据
//        excelService.addJson(stest);
        list.add(new Json(stest));
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = JSONObject.fromObject(list.get(i));
            excelService.addJson(i,object.toString());
        }
        return "modules/sys/excel/upload";
//        return "modules/sys/excel_List";
    }

    /**
     * 查询的方法
     */
    @RequestMapping(value = "/selectJson.do")
    public String selectSheetJson(HttpServletRequest request,HttpServletResponse response){
        String s[] = null;
        List<String> strings =new ArrayList<String>();
        //从数据库中读取内容
        List<Excel> collection =excelService.selectJsonById();
//        List<Excel> collection =jsonService.selectById();

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i));
            JSONObject object = JSONObject.fromObject(collection.get(i));
            System.out.println(object);
            System.out.println("------------");
            strings.add(jsonToText(object));
            System.out.println(strings.get(i));
        }
        request.setAttribute("table",strings);
        return "modules/sys/excel/show";
    }
    public static String jsonToText(JSONObject s){
        String text=s.get("json").toString();
        text = text.replace("{\"json\":\"","");
        text =text.replace("\"}","");
        text = text.replace("\\","");
        text = text.replace("nnnn","");
        return text;
    }


}

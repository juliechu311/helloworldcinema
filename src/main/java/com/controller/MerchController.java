package com.controller;


import com.entity.Merch;
import com.service.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/merch")
@Component
public class MerchController {

    @Autowired
    MerchService merchService;

    @GetMapping("/merchId/{id}")
    public String showProductPage(@PathVariable Integer id, Model model) {
        Merch merch = merchService.getbyMerchId(id);
        model.addAttribute("merch", merch);
        return "front_end/merchStore/TestSingleMerch"; // 返回單獨商品頁面的HTML文件名
    }

    @GetMapping("/front")
    public String front() {
        return "front_end/merchStore/TestMerchStore";
    }

    @GetMapping("/frontsingle")
    public String frontsingle() {
        return "front_end/merchStore/TestSingleMerch";
    }

    @PostMapping("/toggleMerchStatus")
    public String toggleMerchStatus(@Valid Merch merchVo) {

        Merch merch = merchService.getbyMerchId(merchVo.getMerchId());
        String text = "上架";
        if (merch.getMerchStatus().equals("上架")) {
            text = "下架";
        }
        merch.setMerchStatus(text);
        merchService.updateMerch(merch);
        return "redirect:/merch/listAllMerch";
    }


    @PostMapping("/addMerch")
    public String addMerch(Model model) {

        Merch merch = new Merch();
        model.addAttribute(merch);
        return "/back_end/merchStore/addMerch";
    }

    @PostMapping("/insertMerch")
    public String insert(@Valid Merch merch, Model model) {
        merchService.addMerch(merch);
        List<Merch> list = merchService.getAll();
        model.addAttribute("merchListDate", list);
        model.addAttribute("success", "修改成功");
        return "/back_end/merchStore/listAllMerch";

    }

    @PostMapping("/getbyMerchId")
    public String getbyMerchId(@RequestParam("merchId") Integer merchId, Model model) {
        Merch merch = merchService.getbyMerchId(merchId);
        model.addAttribute("Merch", merch);
        return "/front_end/merchStore/TestSingleMerch";

    }

    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("merchId") String merchId, ModelMap model) {
        Merch merch = merchService.getbyMerchId(Integer.valueOf(merchId));
        model.addAttribute("merch", merch);
        return "/back_end/merchStore/updateMerch"; // 查詢完成後轉交update_emp_input.html
    }

    @GetMapping("updateMerch")
    public String ShowupdateMerch(@RequestParam Integer merchId , Model model) {
        Merch merch = merchService.getbyMerchId(Integer.valueOf(merchId));
        model.addAttribute("merch", merch);
        return "/back_end/merchStore/updateMerch";
    }

    @PostMapping("updateMerch")
    public String updateMerch(@Valid Merch merch, Model model) {

//        merch = merchService.getbyMerchId(Integer.valueOf(merch.getMerchId()));
        model.addAttribute("success", "修改成功");
        merchService.updateMerch(merch);

        return "redirect:/merch/listAllMerch";

    }



    @PostMapping("/deleteMerch")
    public String deleteMerch(@RequestParam("merchId") Integer merchId, Model model) {
        merchService.deleteMerch(merchId);
        List<Merch> list = merchService.getAll();
        model.addAttribute("merchListDate", list);
        model.addAttribute("success", "刪除成功");
        return "/back_end/merchStore/listAllMerch";


    }

    @GetMapping("listAllMerch")
    public String listAllMerch(Model model) {
        List<Merch> merchList = merchService.getAll();
        model.addAttribute("merchListData", merchList);
        return "back_end/merchStore/listAllMerch";
    }

    @ModelAttribute("merchListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
    protected List<Merch> referenceListData(Model model) {

        List<Merch> list = merchService.getAll();
        return list;
    }

    @PostMapping("/getbyMerchStatus")
    public String MerchStatus(Model model){
        List<Merch> merchlist = merchService.getbyMerchStatus("上架");
        model.addAttribute("merchStatusList",merchlist);
        return "/front_end/merchStore/TestMerchStore";
    }


}

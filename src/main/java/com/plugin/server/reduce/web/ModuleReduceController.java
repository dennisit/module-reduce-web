//--------------------------------------------------------------------------
// Copyright (c) 2010-2020, En.dennisit or Cn.苏若年
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
// Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
// Neither the name of the dennisit nor the names of its contributors
// may be used to endorse or promote products derived from this software
// without specific prior written permission.
// Author: dennisit@163.com | dobby | 苏若年
//--------------------------------------------------------------------------
package com.plugin.server.reduce.web;

import com.plugin.server.mvc.base.BaseMultiController;
import com.plugin.server.reduce.client.ModuleReduceClient;
import com.plugin.server.reduce.data.Page;
import com.plugin.server.reduce.object.ModuleObject;
import com.plugin.server.reduce.view.ViewConstants;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Description:
 * @author dennisit@163.com
 * @version 1.0
 */
@Controller
@RequestMapping("/moduleReduce")
public class ModuleReduceController extends BaseMultiController{

    public static final int DEFAULT_PAGE_SIZE = 5;


    @Resource(name = "moduleReduceClient")
    private ModuleReduceClient moduleReduceClient;

    /**
     * page show module list
     * @param request
     * @param moduleObject
     * @return
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView listModules(HttpServletRequest request, @ModelAttribute("moduleObject") ModuleObject moduleObject){
        Map<String,Object> context = new HashMap<String,Object>();
        int pageNow = 1 ;
        int pageSize = DEFAULT_PAGE_SIZE;
        String pageCurr = request.getParameter("pageNumber");
        String eachPer = request.getParameter("pagePerSize");
        if(StringUtils.isNotBlank(pageCurr) && pageCurr.matches("\\d+")){
            pageNow = Integer.parseInt(pageCurr);
        }
        if(StringUtils.isNotBlank(eachPer) && eachPer.matches("\\d+")){
            pageSize = Integer.parseInt(eachPer);
        }
        Page<ModuleObject> page = moduleReduceClient.queryModules(moduleObject,pageNow,pageSize);
        System.out.println(toJson(page));
        context.put("page",page);
        context.put("searchObject",moduleObject);
        return toView(ViewConstants.MODULE_LIST_VIEW, context);
    }


    /**
     * page show module list
     * @param request
     * @param moduleObject
     * @return
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView pageModules(HttpServletRequest request,@ModelAttribute("moduleObject") ModuleObject moduleObject){
        Map<String,Object> context = new HashMap<String,Object>();
        int pageNow = 1 ;
        int pageSize = DEFAULT_PAGE_SIZE;
        String pageCurr = request.getParameter("pageNumber");
        String eachPer = request.getParameter("pagePerSize");
        if(StringUtils.isNotBlank(pageCurr) && pageCurr.matches("\\d+")){
            pageNow = Integer.parseInt(pageCurr);
        }
        if(StringUtils.isNotBlank(eachPer) && eachPer.matches("\\d+")){
            pageSize = Integer.parseInt(eachPer);
        }
        System.out.println("min -search:" + toJson(moduleObject));
        Page<ModuleObject> page = moduleReduceClient.queryModules(moduleObject,pageNow,pageSize);
        System.out.println(toJson(page));
        context.put("page",page);
        return toView(ViewConstants.MODULE_PAGE_VIEW, context);
    }

    /**
     * return to form page
     * @return
     */
    @RequestMapping(value = {"/form"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView formPage(){
        return toView(ViewConstants.MODULE_FORM_VIEW,null);
    }


    /**
     * add new module server
     * @param request
     * @param moduleObject
     * @return
     */
    @RequestMapping(value = {"/put"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insertModule(HttpServletRequest request,@ModelAttribute("moduleObject") ModuleObject moduleObject){
        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("新增数据:" + toJson(moduleObject));
        boolean result = moduleReduceClient.insertModule(moduleObject);
        if(result){
            return redirectView("pages");
        }else{
            map.put("moduleObject",moduleObject);
            map.put("message","操作失败,服务名已经存在或者服务异常!");
        }
        return toView(ViewConstants.MODULE_FORM_VIEW,map);
    }

    /**
     * open module switch
     * @param request
     * @return
     */
    @RequestMapping(value = {"/open"}, method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String openModule(HttpServletRequest request){
        String moduleName = request.getParameter("moduleName");
        String moduleToken = request.getParameter("moduleToken");
        boolean result = moduleReduceClient.openModule(moduleName,moduleToken);
        if(result){
            return "true";
        }
        return "false";
    }

    /**
     * close module switch
     * @param request
     * @return
     */
    @RequestMapping(value = {"/close"}, method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String closeModule(HttpServletRequest request){
        String moduleName = request.getParameter("moduleName");
        String moduleToken = request.getParameter("moduleToken");
        boolean result = moduleReduceClient.closeModule(moduleName,moduleToken);
        if(result){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = {"/info"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView authorInfo(){
        return toView(ViewConstants.MODULE_INFO,null);
    }

    public String toJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setModuleReduceClient(ModuleReduceClient moduleReduceClient) {
        this.moduleReduceClient = moduleReduceClient;
    }
}

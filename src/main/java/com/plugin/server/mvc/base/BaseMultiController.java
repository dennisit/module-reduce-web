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
package com.plugin.server.mvc.base;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * Description:
 *
 * @author dennisit@163.com
 * @version 1.0
 */
public class BaseMultiController extends MultiActionController {

    /**
     * redirect Url
     * @param redirectUrl
     *
     * @return
     * 		redirect page
     */
    public ModelAndView redirectView(String redirectUrl) {
        return new ModelAndView(new RedirectView(redirectUrl));
    }

    /**
     * to view page with context data
     * @param viewPath
     * @param map
     * @return
     * 		view page with data in map
     */
    public ModelAndView toView(String viewPath,Map<String,Object> map) {
        return new ModelAndView(viewPath,map);
    }
}

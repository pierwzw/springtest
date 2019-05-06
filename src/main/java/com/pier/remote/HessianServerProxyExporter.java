package com.pier.remote;

import com.pier.config.Constant;
import com.pier.WebHessianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther zhongweiwu
 * @date 2018/10/18 15:22
 */
public class HessianServerProxyExporter extends HessianServiceExporter {

    private static Logger logger = LoggerFactory.getLogger(HessianServerProxyExporter.class);

    public String hessianAuth;

    @Autowired
    private WebHessianService webHessianService;

    public HessianServerProxyExporter() {
        this.hessianAuth = Constant.HESSIAN_AUTH;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("++++ hessian request clientIp:" + request.getRemoteAddr() + "++++requestData:" + request.getRequestURL());
        String auth = request.getHeader("hessianAuth");
        if(auth != null && auth.equalsIgnoreCase(this.hessianAuth)) {
            super.handleRequest(request, response);
        } else {
            logger.info("+++++hessianAuth->fail :" + request.getRemoteAddr() + "," + request.getRequestURL());
        }
    }
}

package cn.heu.hmps.web.system;

import com.sungard.cas.client.validation.IAccountValidate;
import com.sungard.cas.client.validation.ILogonHandler;
import com.sungard.cas.client.validation.LogonFailHandler;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Yingtao.Q
 * Date: 12-3-23
 * Time: 下午11:07
 */
public class SecurityAction implements ILogonHandler,LogonFailHandler {
    public void onLogon(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Assertion assertion) {
        ServletContext servletContext = httpServletRequest.getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            session = httpServletRequest.getSession(true);
        }
        String userName = (String) httpServletRequest.getSession().getAttribute("sungard_username");
        try {
            httpServletResponse.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void ssoLogonFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("login error");
        try {
            httpServletResponse.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

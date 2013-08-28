package cn.heu.hmpsmobile.web.system;

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

/**
 * User: Yingtao.Q
 * Date: 12-3-23
 * Time: 下午11:07
 */
public class SecurityAction implements ILogonHandler,LogonFailHandler {
    public void onLogon(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Assertion assertion) {

        System.out.println("login ok!!!");
    }


    public void ssoLogonFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("login error");
    }
}

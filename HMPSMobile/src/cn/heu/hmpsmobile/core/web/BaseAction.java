package cn.heu.hmpsmobile.core.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * User: Yingtao.Q
 * Date: 11-7-14
 * Time: 10:05
 */
public class BaseAction extends ActionSupport implements ServletContextAware,
        ServletResponseAware, ServletRequestAware, SessionAware {

    private static final long serialVersionUID = 1L;

    protected ServletContext servletContext;

    protected HttpServletRequest httpServletRequest;

    protected HttpServletResponse httpServletResponse;

    protected HttpSession httpSession;

    protected Map<String, Object> session;

    public void setServletContext(ServletContext context) {
        this.servletContext = context;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.httpServletResponse = response;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
        this.httpSession = request.getSession();
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}


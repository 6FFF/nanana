package cn.ty.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component //把当前类注解成一个元件到spring中，直接使用
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";//路由开始前 进行过滤处理
    }

    @Override
    public int filterOrder() {
        return 1;//执行顺序 两个以上才有意义  越大越先执行  通过ZuulFilter实现的Comparable比较器进行比较大小
    }

    @Override
    public boolean shouldFilter() {
        return true;//true表示需要过滤，false表示不过滤
    }

    @Override
    public Object run() throws ZuulException {//业务逻辑代码
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        String token = req.getParameter("token");
        if (token == null || "".equals(token.trim())){//没有token
            //没有token,登录效验失败，拦截，不进行继续的响应
            context.setSendZuulResponse(false);//
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);//设置错误码 401
        }
        return null;
    }
}

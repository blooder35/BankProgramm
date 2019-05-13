package system.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import system.utility.LoggerUtility;
import system.utility.StringConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class LoggerInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger= LoggerFactory.getLogger(StringConstants.USER_LOGGER);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoggerUtility.logRequestString(request, logger);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            LoggerUtility.logModelAndView(modelAndView, logger);
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            logger.info("Error occured:{}", ex.toString());
        } else {
            logger.info("Success");
        }
        super.afterCompletion(request, response, handler, ex);
    }
}

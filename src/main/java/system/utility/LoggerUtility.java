package system.utility;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class LoggerUtility {

    public static void logRequestString(HttpServletRequest request, Logger logger) {
        if (logger.isInfoEnabled()) {
            setMDCIdCounter(request);
            //logger.info("{} request to {}?{} parameters:{}", request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getParameterMap());
            setMDCRequestString(request);
        }
    }

    public static void logModelAndView(ModelAndView modelAndView, Logger logger) {
        if (logger.isInfoEnabled()) {
            MDC.put(StringConstants.MDC_MODEL_VALUES, modelAndView.getModelMap().values().toString());
        }
    }

    public static void logResponce(ContentCachingResponseWrapper responseWrapper, Logger logger) throws IOException {
        if (logger.isInfoEnabled()) {
            MDC.put(StringConstants.MDC_RESPONSE, IOUtils.toString(responseWrapper.getContentInputStream(),"UTF-8"));
        }
    }

    private static void setMDCRequestString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod());
        sb.append(" request to ");
        sb.append(request.getRequestURI());
        if (request.getQueryString() != null) {
            sb.append("?");
            sb.append(request.getQueryString());
        }
        sb.append(" parameters:");
        sb.append(request.getParameterMap());
        MDC.put(StringConstants.MDC_REQUEST, sb.toString());
    }

    private static void setMDCIdCounter(HttpServletRequest request) {
        HttpSession session = request.getSession();
        synchronized (session) {
            Object idObj = session.getAttribute(StringConstants.MDC_ID);
            if (idObj != null) {
                int newId = Integer.parseInt(idObj.toString());
                newId++;
                MDC.put(StringConstants.MDC_ID, Integer.toString(newId));
                session.setAttribute(StringConstants.MDC_ID, Integer.toString(newId));
            } else {
                MDC.put(StringConstants.MDC_ID, StringConstants.MDC_STARTING_ID);
                session.setAttribute(StringConstants.MDC_ID, StringConstants.MDC_STARTING_ID);
            }
            MDC.put(StringConstants.MDC_IP, request.getRemoteAddr());
            MDC.put(StringConstants.MDC_SESSION, session.getId());
        }
    }
}

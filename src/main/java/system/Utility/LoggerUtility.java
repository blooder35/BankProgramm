package system.Utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoggerUtility {
    private static final String THROWABLE_NAME = "Error in LoggerUtility";

    public static void logRequestString(HttpServletRequest request, Logger logger) {
        if (logger.isInfoEnabled()) {
            setMDCIdCounter(request);
            logger.info("{} request to {}?{} parameters:{}", request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getParameterMap());
        }
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

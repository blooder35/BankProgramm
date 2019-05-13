package system.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import system.utility.LoggerUtility;
import system.utility.StringConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class LoggerFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(StringConstants.USER_FILTER_LOGGER);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
        LoggerUtility.logRequestString(requestWrapper, logger);
        filterChain.doFilter(requestWrapper,responseWrapper);
        LoggerUtility.logResponce(responseWrapper, logger);
        logger.info("Completed");
        responseWrapper.copyBodyToResponse();
    }
}

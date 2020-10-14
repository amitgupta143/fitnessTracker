package com.deloitte.fitnesstracker.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.deloitte.fitnesstracker.constants.FitnessTrackerConstants;
import com.deloitte.fitnesstracker.exception.TokenNotValidException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

	private Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	private JwtTokenProvider jwtTokenProvider;
	
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
	        if (token == null || !jwtTokenProvider.validateToken(token)) {
	        	throw new TokenNotValidException(FitnessTrackerConstants.SESSION_ERROR);
	        }
	        chain.doFilter(request, response);  
        } catch (Exception e) {
        	logger.info(FitnessTrackerConstants.EXCEPTION_MSG);
        	((HttpServletResponse)response).sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
		}
	}

}

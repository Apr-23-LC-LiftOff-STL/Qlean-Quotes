package org.launchcode.qleanquotes;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.qleanquotes.wrappers.CachedBodyHttpServletRequest;

import java.io.IOException;

public class CachedBodyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);

        // Perform any necessary operations using the cached request object

        filterChain.doFilter(cachedRequest, response);
    }
}

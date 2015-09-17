package me.theegg.train.interceptor;

import me.theegg.train.model.User;
import me.theegg.train.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by seal on 9/16/15.
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        System.out.println("user interceptor");
        if (token != null) {
            User user = userRepository.findOneByToken(token);
            request.setAttribute("user", user);
        }

        return super.preHandle(request, response, handler);
    }
}

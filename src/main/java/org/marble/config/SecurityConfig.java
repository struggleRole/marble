package org.marble.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.marble.pojo.Hr;
import org.marble.pojo.RespBean;
import org.marble.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;
    @Autowired
    MyFilter myFilter;
    @Autowired
    MyDecision myDecision;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //忽略登录页面
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers
                ("/login","/css/**","/fonts/**","/img/**"
                ,"/js/**","/index.html","/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myDecision);
                        o.setSecurityMetadataSource(myFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword("");
                        System.out.println(hr.getId());
                        RespBean ok = RespBean.ok("登陆成功！", hr);
                        writer.write(new ObjectMapper().writeValueAsString(ok));
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        RespBean respBean = RespBean.error("登陆失败！");
                        if (e instanceof LockedException){ //打开他的父类按住Ctrl+H
                            respBean.setMsg("账户被锁定！");
                        }else if (e instanceof DisabledException){
                            respBean.setMsg("账户被禁用！");
                        }else if (e instanceof CredentialsExpiredException){
                            respBean.setMsg("密码过期!");
                        }else if (e instanceof AccountExpiredException){
                            respBean.setMsg("账户被锁定！");
                        }else if (e instanceof BadCredentialsException){
                            respBean.setMsg("用户名或者密码错误，请重新输入！");
                        }
                        writer.write(new ObjectMapper().writeValueAsString(respBean));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and().
                logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(RespBean
                                .ok("注销成功！")));
                        writer.flush();
                        writer.close();
                    }
                })
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse
                    resp, AuthenticationException e) throws IOException,
                    ServletException {
                resp.setContentType("application/json;charset=utf-8");
                resp.setStatus(401);
                PrintWriter writer = resp.getWriter();
                RespBean respBean =RespBean.error("访问失败！");
                if (e instanceof InsufficientAuthenticationException){ //打开他的父类按住Ctrl+H
                    respBean.setMsg("尚未登录，请登录!");

                }
                writer.write(new ObjectMapper().writeValueAsString(respBean));
                writer.flush();
                writer.close();
            }
        });
    }
}

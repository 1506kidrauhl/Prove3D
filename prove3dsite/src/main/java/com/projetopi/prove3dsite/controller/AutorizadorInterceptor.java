package com.projetopi.prove3dsite.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) throws Exception {

        String uri = request.getRequestURI();

        //Permissão de URLs que não tem necessidade de estar logado
        if (uri.endsWith("index") || uri.contains("cadastro") || uri.contains("login")) {
            return true;
        }

        //Permissão de arquivos de acesso aos arquivos do site
        if (uri.contains("css") || uri.contains("js") || uri.contains("img") ||
                uri.contains("fonts") || uri.contains("icons-reference")) {
            return true;
        }

        //Se o usuário estiver logado, é true
        if (request.getSession().getAttribute("dadosUser") != null) {
            return true;
        }

        //Caso nada seja verdadeiro, retorna ao login
        response.sendRedirect("/index");
        return false;
    }
}


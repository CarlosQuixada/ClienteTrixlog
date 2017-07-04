package br.trixlog.carlos.client.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.trixlog.carlos.client.model.Papel;
import br.trixlog.carlos.client.model.Usuario;

@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("/")||uri.endsWith("/usuario/logar")|| uri.endsWith("/home") || uri.endsWith("/usuario/logout")){
			return true;
		}
		
		Usuario logado = (Usuario)request.getSession().getAttribute("usuarioLogado");
		
		
		if(logado!=null){
			if(logado.getPapel() == Papel.RH){
				return permitirRH(uri);
			}else if(logado.getPapel() == Papel.LOGISTICA){
				return permitirAnalistaLogistica(uri);
			}else if(logado.getPapel() == Papel.ADMIN){
				return true;
			}
		}
		
		response.sendRedirect("/");
		return false;
		
	}
	
	private boolean permitirRH(String uri){
		if(uri.endsWith("/usuario/cadastrarFuncionario") || uri.endsWith("/usuario/cadastrarFuncionarioForm")){
			return true;
		}
		return false;
	}
	
	private boolean permitirAnalistaLogistica(String uri){
		if(uri.endsWith("/rota/gerarRotaForm") || uri.contains("/rota/gerarRota") || uri.endsWith("/rota/listarRota") ){
			return true;
		}
		return false;
	}	
}
package com.myweb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
@Setter
public class LoginFailureHandler implements AuthenticationFailureHandler {
	//이 클래스는 AuthenticationFailureHandler 인터페이스를 구현하는 클래스로,
	//로그인 실패 시 처리를 담당합니다.

	private String authEmail;
	private String errorMessage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			//메서드는 로그인 실패 시에 필요한 정보를 수집하고, 오류 메시지를 생성하여 사용자에게 보여주며, 로그 파일에도 기록하는 역할을 합니다.
			AuthenticationException exception) throws IOException, ServletException {
		setAuthEmail(request.getParameter("email"));
		//사용자의 로그인 시도에서 입력한 이메일 주소를 HttpServletRequest 객체에서 가져와서 
		//setAuthEmail 메서드를 통해 authEmail 변수에 저장합니다. 이렇게 함으로써 로그인 실패 시 어떤 이메일 주소로 로그인을 시도했는지 추적할 수 있습니다.
		
		//exception 발생시 메세지 저장
		if(exception instanceof BadCredentialsException ||
				exception instanceof InternalAuthenticationServiceException) {
			//exception 객체가 BadCredentialsException 또는 InternalAuthenticationServiceException 클래스의 인스턴스인지 검사합니다.
			//이러한 예외는 주로 로그인 시도와 관련된 오류를 나타냅니다.
			setErrorMessage(exception.getMessage().toString());
			//위의 if 조건에서 만약 해당 예외가 발생했다면, exception 객체의 메시지를 문자열로 변환하고,
			//이 문자열을 setErrorMessage 메서드를 통해 errorMessage 변수에 저장합니다. 이렇게 함으로써 로그인 실패 이유를 errorMessage에 기록합니다.
		}
		log.info(">>>>>>>> error Message >>> " + errorMessage);
		request.setAttribute("email", getAuthEmail());
		//HttpServletRequest 객체에 authEmail 값을 속성(attribute)으로 추가하여, 로그인 페이지로 다시 이동할 때 이메일 주소를 함께 전달합니다.
		
		request.setAttribute("errMsg", getErrorMessage());
		//HttpServletRequest 객체에 errorMessage 값을 속성으로 추가하여, 로그인 페이지로 다시 이동할 때 오류 메시지를 함께 전달합니다.
		
		request.getRequestDispatcher("/member/login?error").forward(request, response);
		//로그인 페이지로 리디렉션하고, 이메일 주소와 오류 메시지를 함께 전달합니다. 이렇게 함으로써 사용자에게 로그인 실패 사유를 표시하고 다시 로그인 페이지로 이동할 수 있습니다.
	}
}

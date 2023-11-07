package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// security package를 생성하여 사용자 핸들러 생성
	
	
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		// password 암호화 객체 빈 생성
		return new BCryptPasswordEncoder();
		//비밀번호를 안전하게 저장하기 위해 사용됩니다. 일반적으로 사용자의 비밀번호를 저장할 때 암호화하여 저장하고, 
		//로그인 시에 비밀번호를 해시(암호화)한 값과 사용자가 입력한 비밀번호를 해시하여 비교하여 인증을 수행합니다.
	}
	
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		//이 코드는 AuthenticationSuccessHandler를 구현한 LoginSuccessHandler 클래스를 빈으로 등록합니다. 
		return new LoginSuccessHandler();
		
		//authSuccessHandler() 메서드는 로그인 성공 시 어떤 동작을 수행해야 하는지 정의하는 데 사용됩니다. 이 빈은 로그인 성공 시의 처리를 커스터마이즈할 때 사용됩니다.
	}
	
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
		//authFailureHandler() 메서드는 로그인 실패 시 어떤 동작을 수행해야 하는지 정의하는 데 사용됩니다. 이 빈은 로그인 실패 시의 처리를 커스터마이즈할 때 사용됩니다.
	}
	
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
		//CustomAuthMemberService 클래스를 빈으로 등록합니다. customUserService() 메서드는 사용자 정보를 로드하는 서비스를 정의하는 데 사용됩니다.
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
		//configure(AuthenticationManagerBuilder auth): 이 코드는 스프링 시큐리티의 AuthenticationManagerBuilder를 사용하여 인증 관리자를 구성합니다.
		//customUserService()를 호출하여 사용자 정보를 제공하고, bcPasswordEncoder()를 호출하여 비밀번호를 암호화하는 데 사용합니다. 
		//이렇게 함으로써 스프링 시큐리티는 사용자의 인증을 처리하고 비밀번호를 검증할 수 있게 됩니다.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		// http 승인 요청
		http.authorizeRequests().antMatchers("/member/list").hasRole("ADMIN") // 관리자 권한
		.antMatchers("/","/board/list","/board/detail","/resources/**","/upload/**","/board/spread/*"
				, "/board/register", "/comment/**","/member/register","/member/login").permitAll() // 모든 이용자 권한
		.anyRequest().authenticated(); // => 인증된 사용자만 처리
		// 모든 이용자 권한
		// => 인증된 사용자만 처리
		
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authSuccessHandler())
		.failureHandler(authFailureHandler());
		
		
		// 커스텀 로그인 페이지 구성
		// controller에 주소요청 맵핑도 같이 꼭 적어줘야 함
		
		// 로그아웃 페이지
		http.logout()
		.logoutUrl("/member/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
		
		
		
	}
	
	
	
}

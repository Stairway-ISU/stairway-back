package com.start.config;
// 스프링 시큐리티 설정
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration  //해당 클래스 Configuration으로 등록
@EnableWebSecurity // Security Filter를 등록, Spring Security를 활성화 시키기
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() //csrf 토큰 비활성화 -> jwt는 세션을 stateless 상태로 관리하기 때문에 csrf에 대한 공격을 방어할 필요가 없기 때문에 disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() //어떤 request 요청이 온다면 (url에 따른 페이지에 대한 권한 부여를 시작하는 메서드)->
                    .antMatchers("/", "/auth/**") //해당 경로들은 인증없이 이용 가능
                    .permitAll() //접근 허용
                    .anyRequest() //다른 요청들의 경우
                    .authenticated() //인증이 필요함 - 없는 경우 접근 제한 페이지를 보여줌
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();

    }

    @Bean //password를 해쉬 함수를 적용해줌 = 암호화
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService);
    }
}

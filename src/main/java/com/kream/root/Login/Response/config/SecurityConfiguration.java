package com.kream.root.Login.Response.config;


import com.kream.root.Login.jwt.JwtAuthenticationFilter;
import com.kream.root.Login.jwt.JwtTokenProvider;
import com.kream.root.admin.jwt.AdminJwtAuthenticationFilter;
import com.kream.root.admin.jwt.AdminJwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfiguration {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdminJwtTokenProvider adminJwtTokenProvider;

    @Autowired
    private com.kream.root.Login.config.CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//        	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
////        	.cors(cors -> cors.disable())
////        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))와 충돌
//            .httpBasic(httpBasic -> httpBasic.disable()) // REST API는 UI를 사용하지 않으므로 기본설정을 비활성화
//
//            .csrf(csrf -> csrf.disable()) // REST API는 csrf 보안이 필요 없으므로 비활성화
//            .sessionManagement(sessionManagement ->
//                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT Token 인증방식으로 세션은 필요 없으므로 비활성화
//            )
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/**").permitAll() // swagger
//                .requestMatchers("/auth/**").permitAll() // 가입 및 로그인 주소는 허용
//
//                .requestMatchers("/normalUser/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // 일반유저 로그인
//                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // 관리자유저 로그인
//                .requestMatchers("/gamePage").permitAll() // 가입 및 로그인 주소는 허용
//
//                .requestMatchers("**exception**").permitAll()
//                .anyRequest().hasAuthority("ROLE_ADMIN") // 나머지 요청은 인증된 ADMIN만 접근 가능
//            )
//            .exceptionHandling(exceptionHandling ->
//		            exceptionHandling
//		            .authenticationEntryPoint(customAuthenticationEntryPoint)  // 여기서 loginMenu 로 리다이렋트
//            )
//                .addFilterBefore(new AdminJwtAuthenticationFilter(adminJwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // JWT Token 필터를 id/password 인증 필터 이전에 추가
//
//        return http.build();
//    }
//@Bean
//@Order(1)
//public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//            .httpBasic(httpBasic -> httpBasic.disable())
//            .csrf(csrf -> csrf.disable())
//            .sessionManagement(sessionManagement ->
//                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .authorizeHttpRequests(authorize -> authorize
//                    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//                    .anyRequest().authenticated()
//            )
//            .exceptionHandling(exceptionHandling ->
//                    exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
//            )
//            .addFilterBefore(new AdminJwtAuthenticationFilter(adminJwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//}
//    @Bean
//    @Order(2)
//    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .httpBasic(httpBasic -> httpBasic.disable())
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/**").permitAll()
//                        .requestMatchers("/auth/**", "/gamePage", "/**").permitAll()
//                        .requestMatchers("/normalUser/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .httpBasic(httpBasic -> httpBasic.disable())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            )
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
            );
//            .addFilterBefore(new AdminJwtAuthenticationFilter(adminJwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://*");
        configuration.addAllowedOrigin("http://43.200.110.19:80/");
        configuration.addAllowedOrigin("http://43.200.110.19:8080/");
//        configuration.addAllowedOrigin("http://52.78.125.42:80/");
        configuration.addAllowedOrigin("http://192.168.0.13:3000");
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:3002");
        configuration.addAllowedOrigin("http://www.pinjun.xyz:3000"); // 새로운 도메인 추가
        configuration.addAllowedOrigin("http://58.125.92.250:3000"); // 새로운 도메인 추가
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
//        configuration.addAllowedOrigin("*");
        configuration.setAllowCredentials(true);

//        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

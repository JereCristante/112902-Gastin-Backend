package com.gastin.app.Gastin.Security;

import com.gastin.app.Gastin.Security.jwt.JwtEntryPoint;
import com.gastin.app.Gastin.Security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);
        http.csrf().disable();
        http.cors().configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.applyPermitDefaultValues();
            corsConfig.addAllowedOrigin("*"); // Permitir todos los orígenes
            corsConfig.addAllowedMethod(HttpMethod.PUT);
            corsConfig.addAllowedMethod(HttpMethod.DELETE);
            corsConfig.addAllowedMethod(HttpMethod.GET);
            corsConfig.addAllowedMethod(HttpMethod.POST);
            corsConfig.addAllowedHeader("Authorization");
            // Agrega otros encabezados según sea necesario
            return corsConfig;
        });
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .requestMatchers("api/auth/**").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        http.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    //@Bean
    //public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter() {
     //   return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
       //     @Override
         //   public void configure(HttpSecurity http) {
           //     try {
             //       http.csrf().disable()
               //             .cors().and()
                 //           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   //         .and()
                     //       .authorizeRequests()
                            //.requestMatchers("/api/**").permitAll() // Configura las rutas públicas
                       //     .anyRequest().authenticated()
                         //   .and()
                           // .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                           // .and()
                            //.formLogin().disable()
                            //.logout().disable();

                    //http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
                //} catch (Exception e) {
                  //  throw new RuntimeException(e);
               // }
            //}
        //};
   //}

//    @Bean
//    public JwtTokenFilter jwtTokenFilter(){
//        return new JwtTokenFilter();
//    }
//
//    //@Bean
//    //public UserDetailsService UserDetailsService(){
//     //   var user = User.withUsername("jcristante@hotmail.com.ar").password("1234").roles().build();
//      //  return new InMemoryUserDetailsManager(user);
//    //}
//   // @Bean
//    //SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
//        //JwtTokenFilter jwtTokenFilter = new  JwtTokenFilter();
//        //jwtTokenFilter.setAuthenticationManager(authManager);
//        //jwtTokenFilter.setFilterProcessesUrl("/login");
//
//        //return http.csrf().disable().authorizeRequests()
//         //       .anyRequest().authenticated().and().httpBasic().and()
//         //       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//          //      .and().addFilterBefore(jwtTokenFilter(),UsernamePasswordAuthenticationFilter.class)
//           //     .build();
//    //}
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/api/**").permitAll()
//                .anyRequest().authenticated();
//                //.and()
//                //.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
//                //.and()
//                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        //http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }

    //public static void main(String[] args) {
     ///   System.out.println("");
    //}

}

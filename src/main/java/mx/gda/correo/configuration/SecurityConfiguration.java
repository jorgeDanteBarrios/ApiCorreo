package mx.gda.correo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*	 add {noop} when dont use PasswordEncoder Ej  password("{noop}admin123")*/
		if(env.getActiveProfiles()[0].equals("prod")){
			auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("n57KrFBwt")).roles("ADMIN")  //admin
			.and()
			.withUser("apiCorreoUser").password(passwordEncoder().encode("ocyTBs@u&W!N")).roles("REGISTRA")  // for external
			;
		}else{
			auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("n57KrFBwt")).roles("ADMIN")  //admin
			.and()
			.withUser("apiCorreoUser").password(passwordEncoder().encode("ocyTBs@u&W!N")).roles("REGISTRA")  // for external
			.and()
			.withUser("svitlaUser").password(passwordEncoder().encode("E3O@dpB%Uj*i")).roles("REGISTRA") 
			;
		}		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* .antMatchers(HttpMethod.GET, "/ApiLogin/**").hasRole("ADMIN") */
		http
		 .cors().and()
		 .httpBasic()
         .and()
         .authorizeRequests()       
         .antMatchers(HttpMethod.POST, "/ApiCorreo/sendEmail").permitAll()
         .antMatchers(HttpMethod.POST, "/ApiCorreo/sendEmail/resultados/covid").permitAll()
         .antMatchers(HttpMethod.POST, "/ApiCorreo/webhook").permitAll()         
         .antMatchers(HttpMethod.POST, "/ApiCorreo/origen").authenticated()         
         .antMatchers(HttpMethod.POST, "/ApiCorreo/motivo").authenticated()
         .antMatchers(HttpMethod.GET, "/ApiCorreo/**").authenticated()
         .antMatchers(HttpMethod.PUT, "/ApiCorreo/**").authenticated()
         //.antMatchers(HttpMethod.PUT, "/ApiCorreo/origen").authenticated()         
         //.antMatchers(HttpMethod.PUT, "/ApiCorreo/motivo").authenticated()        
         .and()
         .csrf().disable()
         .formLogin().disable();
					
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(Boolean.valueOf(true));
		//corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}

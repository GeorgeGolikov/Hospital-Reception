package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import project.security.jwt.JwtSecurityConfigurer;
import project.security.jwt.JwtTokenProvider;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(HttpMethod.GET, "/hospital/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/hospital/auth/signin").permitAll()

                .antMatchers(HttpMethod.POST, "/people/addPerson").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/people/editPerson/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/people/deletePerson").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/people/peopleShowAll").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/people/peopleShowAll/{id}").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/diagnosis/diagnosisShowAll").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/diagnosis/diagnosisShow/{id}").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.GET, "/wards/wardsShowAll").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/wards/wardsShowAll/{id}").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.POST, "/diagnosis/addDiagnosis").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/diagnosis/editDiagnosis/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/diagnosis/deleteDiagnosis").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/wards/addWard").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/wards/editWard/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/wards/deleteWard").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/user.html").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/admin.html").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}

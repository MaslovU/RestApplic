package maslov.aptitos.config;

import maslov.aptitos.domain.SuperUser;
import maslov.aptitos.repo.UserDetailsRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                .and()
                    .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        return map -> {
            String id = (String) map.get("sub");
            SuperUser superUser = userDetailsRepo.findById(id).orElseGet(() -> {
               SuperUser newUser = new SuperUser();

               newUser.setId(id);
               newUser.setName((String) map.get("name"));
               newUser.setEmail((String) map.get("email"));
               newUser.setGender((String) map.get("gender"));
               newUser.setLocate((String) map.get("locale"));
               newUser.setUserpic((String) map.get("picture"));

               return newUser;
            });
            superUser.setLastVisit(LocalDateTime.now());

            return userDetailsRepo.save(superUser);
        };
    }
}
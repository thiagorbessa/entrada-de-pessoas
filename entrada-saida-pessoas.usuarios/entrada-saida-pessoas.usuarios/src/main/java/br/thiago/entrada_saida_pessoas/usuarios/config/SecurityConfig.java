package br.thiago.entrada_saida_pessoas.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Collection;

@Configuration // Marca esta classe como uma classe de configuração do Spring
@EnableWebSecurity // Habilita a configuração de segurança web no Spring
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Cria usuários em memória com suas credenciais e papéis
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build());
        manager.createUser(User.withUsername("user").password(passwordEncoder().encode("user123")).roles("USER").build());
        return manager; // Retorna o gerenciador de usuários em memória
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Retorna uma instância de BCryptPasswordEncoder para codificação de senhas
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() // Configura as regras de autorização
                .antMatchers("/front/html/GerenciamentoUsuario/crudUser.html").hasRole("ADMIN") // Acesso restrito a ADMIN
                .antMatchers("/front/html/entrada.html").hasRole("USER") // Acesso restrito a USER
                .anyRequest().authenticated() // Qualquer outra solicitação precisa estar autenticada
                .and()
            .formLogin() // Configura o formulário de login
                .loginPage("/html/login.html") // Página de login personalizada
                .loginProcessingUrl("/perform_login") // URL de processamento do formulário de login
                .defaultSuccessUrl("/front/html/entrada.html", true) // URL de sucesso após o login
                .failureUrl("/html/login.html?error=true") // URL em caso de falha no login
                .permitAll() // Permite acesso à página de login para todos
                .and()
            .logout() // Configura o logout
                .permitAll(); // Permite o logout para todos
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Obtém as autorizações do usuário autenticado
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // Determina a URL de destino com base nas autorizações do usuário
            String targetUrl = determineTargetUrl(authorities);
            // Redireciona para a URL de destino
            response.sendRedirect(targetUrl);
        };
    }

    // Método para determinar a URL de destino com base nas autorizações do usuário
    private String determineTargetUrl(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            // Verifica se o usuário tem papel ADMIN
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                // Se sim, redireciona para a página de gerenciamento de usuários
                return "/front/html/GerenciamentoUsuario/crudUser.html";
            // Verifica se o usuário tem papel USER
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                // Se sim, redireciona para a página de entrada
                return "/front/html/entrada.html";
            }
        }
        // Se nenhum papel corresponder, redireciona para uma página de login inválido
        return "/html/login.html?error=true";
    }
}

package com.example.chatappli.config;

import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity//CSRF保護が自動的に有効になる。
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().permitAll(); 古いので記事通りには使わない。
        http.authorizeHttpRequests(auhorize ->
                auhorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                        .hasRole("ADMIN").requestMatchers(new AntPathRequestMatcher("/board")).hasRole("USER").anyRequest().authenticated());//、h2-consoleから始まるパスはCSRF対策しない。

        http.formLogin(login -> login.loginPage("/user").permitAll()//ログインページのカスタマイズ
                .defaultSuccessUrl("/board")).logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessUrl("/user"));//ログイン認証ページ要求、ログイン成功後デフォルト画面の設定

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")));//csrf制限解除　URL　/h2-console/任意...に誰でもアクセス可
        http.headers((head) -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));//同じドメインであればiframeを許可する。　メソッド参照を使った場合　
        //http.headers((head) -> head.frameOptions((frame)-> frame.sameOrigin())); メソッド参照を使わない場合
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
/*
* http.csrf((csrf) -> csrf.ignoringRequestMatchers("/h2-console/**"));
* http.csrf()で関数型インターフェイスCustomizerの<CsrfConfigurer<HttpSecurity>>でCustomizerを受け取る
*メソッド参照が使えた理由として、frameOptionsとsameOriginの戻り値「HeadersConfigurer<H>」と
*....Customizer<HeadersConfigurer.FrameOptionsConfig> frameOptionsCustomizer
*
* バグ　requestMatchers ("/error").permitAll() このメソッドは、これらのパターンが Spring MVC パターンであるかどうかを判断できません。
*   Resolved link https://github.com/spring-projects/spring-security/issues/13602
*   Error source (Source before change :変更前のソース)
*       http.authorizeHttpRequests(auhorize ->
                auhorize.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated());
*   Resolve source (Source after change :変更語のソース)
*       http.authorizeHttpRequests(auhorize ->
                auhorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll().anyRequest().authenticated());//、h2-consoleから始まるパスはCSRF対策しない。/
*       memo
*           permitAll()はURLが誰でも許可されていることを示す。...AbstractRequestMatcherRegistry<C>のネストされた
* */


/*
*        //http.authorizeRequests().anyRequest().permitAll(); 古いので記事通りには使わない。
        http.authorizeHttpRequests(auhorize ->
                auhorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll().anyRequest().authenticated());//、h2-consoleから始まるパスはCSRF対策しない。/

        http.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")));//csrf制限解除　URL　/h2-console/任意...に誰でもアクセス可
        http.headers((head) -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));//同じドメインであればiframeを許可する。　メソッド参照を使った場合　
        //http.headers((head) -> head.frameOptions((frame)-> frame.sameOrigin())); メソッド参照を使わない場合
        return http.build();
        *
        * ※　注意　
        * DBにはROLE_ADMIN、とロールを登録しました。が、このコンフィグでhasRoleを使うときは、ROLE_の接頭辞は外す必要がありますので注意してください（内部ロジックが勝手にROLE_を付与するため）
        * */


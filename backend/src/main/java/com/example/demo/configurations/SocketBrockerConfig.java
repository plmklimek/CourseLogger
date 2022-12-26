package com.example.demo.configurations;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class SocketBrockerConfig implements WebSocketMessageBrokerConfigurer {
    private final UserRepository userRepository;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/specific");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message,
                                StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String email = Objects.requireNonNull(
                            accessor.getNativeHeader("email")).get(0);
                    String password = Objects.requireNonNull(
                            accessor.getNativeHeader("password")).get(0);
                    User user = userRepository.findByEmail(email);
                    if (email == null || password == null) {
                        throw new AuthenticationCredentialsNotFoundException(
                                "User not found");
                    }

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            email,
                            password,
                            user.getAuthorities().stream()
                                    .map(authority -> new SimpleGrantedAuthority(
                                            authority.getAuthority()))
                                    .collect(Collectors.toSet())
                    );

                    SecurityContextHolder.getContext().setAuthentication(token);
                    ; // access authentication header(s)
                    accessor.setUser(token);
                }
                return message;
            }
        });
    }

}

package com.mike.springboot2.webservice.config.auth.dto;

import com.mike.springboot2.webservice.domain.users.Role;
import com.mike.springboot2.webservice.domain.users.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String id;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email,
                           String id,
                           String picture) {

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.id = id;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        if("facebook".equals(registrationId)) {
            return ofFacebook(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .id((String) attributes.get("sub"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    private static OAuthAttributes ofFacebook(String userNameAttributeName,
                                              Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .id((String) attributes.get("id"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    public Users toEntity() {
        return Users.builder()
                .users_name(name)
                .users_email(email)
                .users_id(id)
                .users_picture(picture)
                .users_role(Role.USER)
                .build();
    }

}

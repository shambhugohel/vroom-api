package com.carzhub.identity.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    private final Keycloak keycloak;
    private final String realm;

    public IdentityService(
            @Value("${keycloak.auth-server-url}") String serverUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.client-id}") String clientId,
            @Value("${keycloak.client-secret}") String clientSecret) {
        this.realm = realm;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType("client_credentials")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    public void updateUserSubscription(String userId, String subscriptionType) {
        // Logic to find user by ID and update attributes or roles
        // UserResource userResource = keycloak.realm(realm).users().get(userId);
        // UserRepresentation user = userResource.toRepresentation();
        // user.singleAttribute("subscription", subscriptionType);
        // userResource.update(user);
        System.out.println("Updating subscription for user " + userId + " to " + subscriptionType);
    }
}

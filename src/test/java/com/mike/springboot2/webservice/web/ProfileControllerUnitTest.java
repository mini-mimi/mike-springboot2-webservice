package com.mike.springboot2.webservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.*;
public class ProfileControllerUnitTest {
    @Test
    public void real_profile이_조회된다() {
        String expectedProfile = "real";
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("oauth");
        mockEnvironment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnvironment);

        String profile = profileController.profile();

        assertEquals(profile, expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다() {
        String expectedProfile = "oauth";
        MockEnvironment mockEnvironment = new MockEnvironment();

        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(mockEnvironment);

        String profile = profileController.profile();

        assertEquals(profile, expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() {
        String expectedProfile = "default";
        MockEnvironment mockEnvironment = new MockEnvironment();

        mockEnvironment.addActiveProfile(expectedProfile);

        ProfileController profileController = new ProfileController(mockEnvironment);

        String profile = profileController.profile();

        assertEquals(profile, expectedProfile);
    }
}

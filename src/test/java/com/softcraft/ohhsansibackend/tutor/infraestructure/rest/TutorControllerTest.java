package com.softcraft.ohhsansibackend.tutor.infraestructure.rest;


import com.softcraft.ohhsansibackend.config.TestSecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@WebMvcTest(TutorController.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class TutorControllerTest {


}
package com.wellOfJava.micro.demo.userServices.controller;

import com.wellOfJava.micro.demo.commons.model.User;
import com.wellOfJava.micro.demo.userServices.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private ServletRequestAttributes attrs;

    @Mock
    private UserRepository mockUserRepository;

    private UserController userController;

    @Before
    public void setUp() {
        initMocks(this);
        mockUserRepository = Mockito.mock(UserRepository.class);
        userController = new UserController(mockUserRepository);
    }

    @Test
    public void testGetAll() {
        // Setup
        final List<User> expectedResult = Arrays.asList();
        when(mockUserRepository.findAll()).thenReturn(Arrays.asList());

        // Run the test
        final List<User> result = userController.getAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetStudent() {
        // Setup
        final long id = 0L;
        final User expectedResult = new User();
        User user = new User();
        user.setUserId(0L);
        Mockito.when(mockUserRepository.findById(eq(0L))).thenReturn(Optional.of(user));

        // Run the test
        final User result = userController.getStudent(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteUser() {
        // Setup
        final long id = 0L;

        // Run the test
        userController.deleteUser(id);

        // Verify the results
        verify(mockUserRepository).deleteById(eq(0L));
    }

    @Test
    public void testCreateUser() {
        // Setup
        MockHttpServletRequest request = new MockHttpServletRequest();
        attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
        final User user = new User();
        final ResponseEntity<Object> expectedResult = ResponseEntity.ok(user);
        when(mockUserRepository.save(any(User.class))).thenReturn(new User());

        // Run the test
        final ResponseEntity<Object> result = userController.createUser(user);

        // Verify the results
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getHeaders().getLocation().getPath()).isEqualTo("/0");
    }

    @Test
    public void testUpdateUser() {
        // Setup
        final User user = new User();
        final long id = 0L;
        User mockUser = new User();
        final ResponseEntity<Object> expectedResult = ResponseEntity.ok(user);

        when(mockUserRepository.findById(eq(0L))).thenReturn(Optional.of(mockUser));
        when(mockUserRepository.save(any(User.class))).thenReturn(mockUser);

        // Run the test
        final ResponseEntity<Object> result = userController.updateUser(user, id);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}

package umut.unay.task5_spring_boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import umut.unay.task5_spring_boot.entity.AuthRequest;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;
import umut.unay.task5_spring_boot.service.JwtService;
import umut.unay.task5_spring_boot.service.UserInfoService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome - This end point is not secured";
    }

    // Get
    @GetMapping("/all-users")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")  --DISABLED FOR TESTING
    public Iterable<UserInfo> getAllUsers()
    {
        return service.findAll();
    }

    // FOR TESTING CAN DELETE WHEN IMPELENTED AFTER TOKEN IMPLEMENTATION
    @GetMapping("/user-get/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")  --DISABLED FOR TESTING
    public Optional<UserInfo> getUser(@PathVariable int id)
    {
        return service.findById(id);
    }

    @GetMapping("/user/{id}-get-attending-events")
    //@PreAuthorize("hasRole('ROLE_USER')")  --DISABLED FOR TESTING
    public List<EventInfo> getAttendingEvents(@PathVariable int id)
    {
        return service.getAttendingEvents(id);
    }

    // Post
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public void addNewUser(@RequestBody UserInfo userInfo)
    {
        service.addUser(userInfo);
    }

    // Put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/user-update/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserInfo userInfo)
    {
        service.updateUser(id, userInfo);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/admin-update/{id}")
    public void updateAdmin(@PathVariable int id, @RequestBody UserInfo userInfo)
    {
        service.updateUser(id, userInfo);
    }

    // Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/user-delete/{id}")
    public void deleteUser(@PathVariable int id)
    {
        service.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/admin-delete/{id}")
    public void deleteAdmin(@PathVariable int id)
    {
        service.deleteUser(id);
    }

    // Profiles
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userProfile()
    {
        return "Welcome - User Profile";
    }

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminProfile()
    {
        return "Welcome - Admin Profile";
    }

    // Token generation
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {
        log.info("Authenticating user: " + authRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated())
        {
            log.info("User authenticated: " + authRequest.getUsername());
            return jwtService.generateToken(String.valueOf(authRequest.getUsername()));
        }
        else
        {
            throw new UsernameNotFoundException("Invalid username");
        }
    }


}

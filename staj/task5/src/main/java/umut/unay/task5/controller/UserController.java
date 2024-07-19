package umut.unay.task5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import umut.unay.task5.entity.AuthRequest;
import umut.unay.task5.entity.UserInfo;
import umut.unay.task5.service.JwtService;
import umut.unay.task5.service.UserInfoService;

@RestController
@RequestMapping("/auth")
public class UserController
{
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
    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserInfo userInfo)
    {
        service.updateUser(id, userInfo);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/admin/{id}")
    public void updateAdmin(@PathVariable int id, @RequestBody UserInfo userInfo)
    {
        service.updateUser(id, userInfo);
    }

    // Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id)
    {
        service.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/{id}")
    public void deleteAdmin(@PathVariable int id)
    {
        service.deleteUser(id);
    }


    // Profiles - Dashboards
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userProfile()
    {
        return "Welcome - User Profile";
    }

    @CrossOrigin(origins = "http://localhost:4200")
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
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getEmail());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid email");
        }
    }


}

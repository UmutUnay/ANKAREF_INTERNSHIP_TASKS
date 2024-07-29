package umut.unay.task5_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;
import umut.unay.task5_spring_boot.exceptions.UserAlreadyExistsException;
import umut.unay.task5_spring_boot.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService
{
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<UserInfo> user = userInfoRepository.findByName(username);
        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }

    public List<UserInfo> findAll()
    {
        return userInfoRepository.findAll();
    }

    public Optional<UserInfo> findById(int id)
    {
        return userInfoRepository.findById(id);
    }

    public List<EventInfo> getAttendingEvents(int id)
    {
        return userInfoRepository.getAttendingEvents(id);
    }

    public void addUser(UserInfo userInfo)
    {
        // Checks
        if (userInfoRepository.findByName(userInfo.getName()).isPresent())
        {
            throw new UserAlreadyExistsException("User already exists " + userInfo.getName());
        }
        if (userInfoRepository.findByEmail(userInfo.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException("Email already exists " + userInfo.getEmail());
        }

        // Registration
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
    }

    public void updateUser(int id, UserInfo userInfo)
    {
        // Checks
        if (userInfoRepository.findByName(userInfo.getName()).isPresent())
        {
            throw new UserAlreadyExistsException("User already exists " + userInfo.getName());
        }
        if (userInfoRepository.findByEmail(userInfo.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException("Email already exists " + userInfo.getEmail());
        }

        // Updating user
        UserInfo user = userInfoRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found " + id));
        user.setName(userInfo.getName());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(user);
    }

    public void deleteUser(int id)
    {
        userInfoRepository.deleteById(id);
    }

    // This method didn't work so I turned login by email to login by username
    public Optional<String> findNameByEmail(String email)
    {
        return userInfoRepository.findNameByEmail(email);
    }

}

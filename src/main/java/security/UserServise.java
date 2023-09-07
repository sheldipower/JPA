package security;

import mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final AppUserDetails appUserDetails;

    public UserService(UserRepository userRepository, UserMapper mapper, AppUserDetails appUserDetails) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.appUserDetails = appUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        appUserDetails.setUserDetails(mapper.toDto(appUser));
        return appUserDetails;
    }
}
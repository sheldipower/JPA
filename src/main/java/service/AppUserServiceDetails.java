package service;

import org.springframework.stereotype.Component;
import repository.AppUserRepository;
import security.AppUserDetails;

@Component
public class AppUserServiceDetails implements UserDetailsService {

    private final AppUserRepository repository;

    private final AppUserMapper mapper;

    private final AppUserDetails appUserDetails;

    public AppUserServiceDetails(AppUserRepository repository, AppUserMapper mapper, AppUserDetails appUserDetails) {
        this.repository = repository;
        this.mapper = mapper;
        this.appUserDetails = appUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = repository.findAppUserByLogin(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        appUserDetails.setUserDetails(mapper.toDto(appUser));
        return appUserDetails;
    }

}

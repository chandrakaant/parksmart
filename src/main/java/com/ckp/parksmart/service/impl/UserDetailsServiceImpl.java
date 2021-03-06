package com.ckp.parksmart.service.impl;

import com.ckp.parksmart.datastore.model.UserModel;
import com.ckp.parksmart.datastore.repository.UserRepository;
import com.ckp.parksmart.datastore.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

/**
 * 
 * @author Sushmitha.K
 *
 */
@Service( "UserDetailsServiceImpl" )
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    /* (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String) */
    @Override
    public UserDetails loadUserByUsername(String emailId )
    {

        Optional<UserModel> applicationUser = userRepository
                .findByEmailAndIsActiveTrue(emailId);

        if( !applicationUser.isPresent() )
        {
            throw new UsernameNotFoundException(emailId);
        }
        HashSet<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(userRoleRepository.getRoleByUserProfileId(applicationUser.get().getId())));
        return new User(applicationUser.get().getEmail(), applicationUser.get().getPassword(), authorities);
    }
}

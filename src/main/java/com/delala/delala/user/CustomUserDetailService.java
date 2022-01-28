package com.delala.delala.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        return new CustomUserDetail(user);
    }
     
    public ModelAndView registerEmployer(){
        ModelAndView modelAndView=new ModelAndView("");
        modelAndView.addObject("registerEmployer", new EmployerRegistraton);
        return modelAndView;
    }
}

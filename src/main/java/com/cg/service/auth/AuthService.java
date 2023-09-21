package com.cg.service.auth;

import com.cg.domain.Enum.ERole;
import com.cg.domain.User;
import com.cg.repository.UserRepository;
import com.cg.service.auth.request.RegisterRequest;
import com.cg.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        var user = AppUtils.mapper.map(request, User.class);
        user.setRole(ERole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean checkUsernameOrPhoneNumberOrEmail(RegisterRequest request, BindingResult result){
        boolean check = false;
        if(userRepository.existsByUsernameIgnoreCase(request.getUsername())){
            result.rejectValue("username", "username", "Tên người dùng đã tồn tại!");
            check = true;
        }
        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.rejectValue("email", "email", "Email đã tồn tại!");
            check = true;
        }
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            result.rejectValue("phoneNumber","phoneNumber","Số điện thoại đã tồn tại!");
            check = true;
        }
        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCaseOrPhoneNumber(username,username,username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Exist") );
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), role);
    }
    // để làm 1. kiểm tra xem user có tồn tại trong hệ thông hay không và tìm bằng 3 field Username Email PhoneNumber
    // 2. Nếu có thì sẽ trả về User của .security.core.userdetails.User để nó lưu vô kho spring sercurity context holder
    // 3. nếu không thì trả ra message lỗi User not Exist
}

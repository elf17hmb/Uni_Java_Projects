package whz.pti.eva.security.service.user;

import java.util.ArrayList; 
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import whz.pti.eva.security.domain.User;
import whz.pti.eva.security.domain.UserCreateForm;
import whz.pti.eva.security.domain.UserRepository;
import whz.pti.eva.security.service.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(long id) {
        log.debug("Getting user={}", id);
        User user = userRepository.findById(id).orElseThrow(() -> 
		new NoSuchElementException(String.format(">>> User=%s not found", id)));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    
    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userRepository.existsByLoginName(loginName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.debug("Getting all users");
        List<User> targetListOrigin = userRepository.findAllByOrderByLoginNameAsc(); 
		List<UserDTO> targetList= new ArrayList<>(); 
		for (User source: targetListOrigin ) {
		  	 UserDTO target= new UserDTO();
		     BeanUtils.copyProperties(source , target);
		     targetList.add(target);
		}
        return targetList;
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setLoginName(form.getLoginName());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }



}

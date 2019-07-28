package whz.pti.eva.security.service.user;


import java.util.Collection; 
import java.util.Optional;

import whz.pti.eva.security.domain.User;
import whz.pti.eva.security.domain.UserCreateForm;
import whz.pti.eva.security.service.dto.UserDTO;


public interface UserService {

	UserDTO getUserById(long id);
	Optional<User> getUserByEmail(String email);
    boolean existsByLoginName(String loginName);
    boolean existsByEmail(String email);
    Collection<UserDTO> getAllUsers();
    User create(UserCreateForm form);

}

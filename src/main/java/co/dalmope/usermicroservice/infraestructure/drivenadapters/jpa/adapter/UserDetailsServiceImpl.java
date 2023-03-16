package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.adapter;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.PersonEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.PrincipalUser;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.RoleEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.UserEntity;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IPersonRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IRoleRepository;
import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String documentID) throws UsernameNotFoundException {
        PersonEntity usuario = personRepository.findByDniNumber(documentID).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<UserEntity> userEntity = userRepository.findAllByPersonEntityId(usuario.getId());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found with documentID: " + documentID);
        }
        List<RoleEntity> roles = new ArrayList<>();

        for (UserEntity user : userEntity) {
            roles.add(user.getRoleEntity());
        }

        return PrincipalUser.build(usuario, roles);
    }
}

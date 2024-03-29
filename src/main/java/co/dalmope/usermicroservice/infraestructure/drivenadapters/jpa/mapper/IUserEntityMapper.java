package co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.mapper;

import co.dalmope.usermicroservice.infraestructure.drivenadapters.jpa.entity.UserEntity;
import co.dalmope.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(target = "personEntity.id", source = "person.id")
    @Mapping(target = "roleEntity.id", source = "role.id")
    UserEntity toEntity(User user);
    @Mapping(target = "person.id", source = "personEntity.id")
    @Mapping(target = "role.id", source = "roleEntity.id")
    User toUser(UserEntity userEntity);
    List<User> toUserList(List<UserEntity> userEntityList);
}

package br.com.nailDesigner.auth_service.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nailDesigner.auth_service.Models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByUsername(String username);
}

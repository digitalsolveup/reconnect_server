package reconnect.server.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reconnect.server.global.model.entity.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
}

package reconnect.server.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reconnect.server.global.model.entity.mysql.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
}

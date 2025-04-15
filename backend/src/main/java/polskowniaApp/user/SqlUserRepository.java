package polskowniaApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer>
{
    @Query(value = "SELECT id FROM users WHERE email = :email", nativeQuery = true)
    Optional<Integer> findIdByEmail(String email);
}

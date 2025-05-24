package polskowniaApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer>
{
    @Override
    @Query(value = "SELECT id FROM users WHERE email = :email", nativeQuery = true)
    Optional<Integer> findIdByEmail(String email);

    @Override
    @Query(value = "SELECT id FROM users WHERE email IN :emails", nativeQuery = true)
    List<Integer> findIdsByEmails(Set<String> emails);

    @Transactional
    @Modifying
    @Override
    @Query(value = "UPDATE users SET password = :password WHERE email = :email", nativeQuery = true)
    void updatePasswordByEmail(String email, String password);
}

package polskowniaApp.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface UserRepository
{
    User save(User toSave);

    Optional<Integer> findIdByEmail(String email);
    Optional<User> findByEmail(final String email);

    boolean existsByEmail(String email);

    List<Integer> findIdsByEmails(Set<String> emails);

    List<User> findByRole(UserRole role);

    void updatePasswordByEmail(String email, String password);

    Optional<User> findById(int userId);
}

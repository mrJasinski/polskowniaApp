package polskowniaApp.user;

import java.util.Optional;

interface UserRepository
{
    User save(User toSave);

    Optional<Integer> findIdByEmail(String email);
    Optional<User> findByEmail(final String email);

    boolean existsByEmail(String email);
}

package polskowniaApp.user;

import java.util.Optional;

interface UserRepository
{
    Optional<User> findByEmail(final String email);
}

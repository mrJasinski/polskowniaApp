package polskowniaApp.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UserServiceUnitTest
{

//    String passwordGenerator()
//    {
//        final String LOWER = "abcdefghijklmnopqrstuvwxyz";
//        final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        final String DIGITS = "0123456789";
//        final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
//
//        StringBuilder password = new StringBuilder();
//        var random = new SecureRandom();
//
//        var chars = LOWER + UPPER + DIGITS + PUNCTUATION;
//
//        for (int i = 0; i < 8; i++)
//        {
//            var index = random.nextInt(chars.length());
//            password.append(chars.charAt(index));
//        }
//
//        return password.toString();
//    }

    @Test
    void passwordGenerator_shouldCreateCharsStringOfGivenLength()
    {
//        given
        var mockEncoder = mock(PasswordEncoder.class);
        var passwordLength = 8;

//        system under test
        var toTest = new UserService(null, mockEncoder, null);

//        when
        var result = toTest.passwordGenerator();

//        then
        System.out.println(result);
        assertEquals(passwordLength, result.length());
    }
}
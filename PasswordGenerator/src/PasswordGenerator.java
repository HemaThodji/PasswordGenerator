import java.util.Random;

public class PasswordGenerator {

    public static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String SPECIAL_SYMBOLS = "!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/";

    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                   boolean includeNumbers, boolean includeSpecialSymbols) {

        StringBuilder passwordBuilder = new StringBuilder();
        StringBuilder validCharacters = new StringBuilder();

        if (includeUppercase) validCharacters.append(UPPERCASE_CHARACTERS);
        if (includeLowercase) validCharacters.append(LOWERCASE_CHARACTERS);
        if (includeNumbers) validCharacters.append(NUMBERS);
        if (includeSpecialSymbols) validCharacters.append(SPECIAL_SYMBOLS);

        if (validCharacters.length() == 0 || length <= 0) return "";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            char randomChar = validCharacters.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }
}

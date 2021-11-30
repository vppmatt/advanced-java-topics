package optionals;

import java.util.Optional;
import java.util.Random;

public class Main {

    static Random r = new Random();

    static Optional<String> getNameFromRemoteServer() {
        if (r.nextInt(5) == 0) {
            return Optional.empty();
        }
        return Optional.of("Matt");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Optional<String> name = getNameFromRemoteServer();
            if (name.isPresent()) {
                System.out.println("the name was " + name.get());
            } else {
                System.out.println("No name was present");
            }

            System.out.println("The result was " + name.orElse("empty"));

        }
    }
}

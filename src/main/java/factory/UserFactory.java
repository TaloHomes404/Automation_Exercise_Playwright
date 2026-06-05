package factory;

import models.User;
import net.datafaker.Faker;

public class UserFactory {

    public static User createRandomUser() {

        Faker faker = new Faker();

        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.credentials().password(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.phoneNumber().cellPhone()
        );
    }
}
package uk.co.trycatchfinallysoftware.data;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.PersonProperties;
import org.inferred.freebuilder.FreeBuilder;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@FreeBuilder
public interface Person {
    String getFirstName();

    Optional<String> getMiddleName();

    String getLastName();

    String getGender();

    Date getDateOfBirth();

    /**
     * Returns a new {@link Builder} with the same property values as this person.
     */
    Builder toBuilder();

    /**
     * Builder of {@link Person} instances.
     */
    class Builder extends Person_Builder {
        public Builder() {
            setFirstName("Test");
            setLastName("User");
        }
    }

    class FakeBuilder extends Builder {
        protected static Fairy fairy = Fairy.create(Locale.UK);
        protected static io.codearte.jfairy.producer.person.Person fakePerson = fairy.person(
                PersonProperties.ageBetween(16, 70),
                PersonProperties.withMiddleName("TEST-ACCOUNT")
        );

        public FakeBuilder() {
            super();

            setFirstName(fakePerson.getFirstName());
            setMiddleName(fakePerson.getMiddleName());
            setLastName(fakePerson.getLastName());
            setDateOfBirth(fakePerson.getDateOfBirth().toDate());
        }
    }
}

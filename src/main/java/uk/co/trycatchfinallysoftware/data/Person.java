package uk.co.trycatchfinallysoftware.data;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.PersonProperties;
import org.inferred.freebuilder.FreeBuilder;

import java.util.Date;
import java.util.Locale;

@FreeBuilder
public interface Person {
    String getFirstname();

    String getMiddleName();

    String getLastname();

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
            setFirstname("Another");
            setLastname("Test User");
        }
    }

    class FakeBuilder extends Builder {
        protected static Fairy fairy = Fairy.create(Locale.UK);
        protected static io.codearte.jfairy.producer.person.Person fakePerson = fairy.person(
                PersonProperties.ageBetween(16, 70),
                PersonProperties.withMiddleName("Ventura")
        );

        public FakeBuilder() {
            super();

            setFirstname(fakePerson.getFirstName());
            setMiddleName(fakePerson.getMiddleName());
            setLastname(fakePerson.getLastName());
            setDateOfBirth(fakePerson.getDateOfBirth().toDate());
        }
    }
}

package trycatchfinallysoftware.data;

import org.inferred.freebuilder.FreeBuilder;

import java.util.Date;

import trycatchfinallysoftware.data.Person_Builder;

@FreeBuilder
public interface Person {
    String getFirstname();

    String getLastname();

    int getAge();

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

        @Override
        public Builder setAge(int age) {
            // Check single-field (argument) constraints in the setter method.
//            checkArgument(age >= 0);
            return super.setAge(age);
        }
    }
}

package uk.co.trycatchfinallysoftware.data;

import org.inferred.freebuilder.FreeBuilder;

import java.util.Date;
import java.util.Optional;

@FreeBuilder
//public interface FakeVentura extends Person { // Can't seem to extend - find out why...
public interface FakeVentura {
    String getFirstName();
    Optional<String> getMiddleName();
    String getLastName();
    String getGender();
    Date getDateOfBirth();

    String getJob();

    /**
     * Returns a new {@link Builder} with the same property values as this person.
     */
    Builder toBuilder();

    /**
     * Builder of {@link FakeVentura} instances.
     */

    class Builder extends FakeVentura_Builder {
        public Builder () {
            super();

            // We'll pre-define our Ace Ventura Fake
            setFirstName("Ace");
            setLastName("Ventura");

            setJob("Pet Detective");

            clearMiddleName(); // One benefit of Optional<String>
        }
    }
}

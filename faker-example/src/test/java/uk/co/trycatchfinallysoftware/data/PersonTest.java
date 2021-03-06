package uk.co.trycatchfinallysoftware.data;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.trycatchfinallysoftware.LifeCycleLoggerListener;
import uk.co.trycatchfinallysoftware.TestApplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ContextConfiguration(
        classes = {
                TestApplication.class
        }
)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(
        listeners = {
                LifeCycleLoggerListener.class
        },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public class PersonTest {

    private final Logger Logger = LoggerFactory.getLogger(getClass());

    public org.slf4j.Logger getLogger() {
        return Logger;
    }

    @Test
    public void testPersonBuilder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1984, 11, 10); // 0 Indexed month

        Person person = new Person.Builder()
                .setDateOfBirth(calendar.getTime())
                .setGender("Male")
                .build();

        assertPerson(person);
    }

    /**
     * Arbitrary testing of our FreeBuilder built Object
     *
     * @param person Person
     */
    private void assertPerson(Person person) {
        getLogger().info("Asserting: {}", person); // Using Logger to help us debug test failures with object.toString()

        assertThat(person, instanceOf(Person.class));
        assertThat(person.getFirstName(), not(isEmptyString()));
        assertThat(person.getLastName().length(), lessThanOrEqualTo(100));
        assertThat(person.getDateOfBirth(), instanceOf(Date.class));
    }

    /**
     * Test of our fictional Person Builder which creates
     * imaginary People within prescribed criteria
     * through constructors and lately mapping.
     */
    @Test
    public void testFakerBuilder() {
        Person fakePerson = new Person.FakeBuilder()
                .buildPartial();
        // Partials are objects which would normally fail validation
        // they are useful during testing

        assertPerson(fakePerson); // Same assertion as Person
    }

    /**
     * Testing of our fictional Fake Ventura Builder which
     * populates an Ace Ventura person. Unfortunately I couldn't
     * extend Person to reduce reptition of the fields - yet.
     */
    @Test
    public void testFakeVentura() {
        FakeVentura fakeVentura = new FakeVentura.Builder()
                .buildPartial();

        assertFakeVentura(fakeVentura);
    }

    /**
     * Testing of mapping an item in this relatively simple example with
     * the added bonus of Bob resulting in a failing test which we'll
     * convert into a pass by expecting an AssertionError as our
     * object doesn't meet the assertions in fakeVentura - its a pass!
     */
    @Test(
            expected = AssertionError.class
    )
    public void testImposterVentura() {
        Calendar releaseDateCal = Calendar.getInstance();
        releaseDateCal.set(1994, 1, 4);

        FakeVentura aceVentura = new FakeVentura.Builder()
                .setGender("Male")
                .setDateOfBirth(releaseDateCal.getTime())
                .buildPartial();

        // Purpose of mapper is changing already existing values
        FakeVentura imposterVentura = aceVentura.toBuilder()
                .mapDateOfBirth(date -> {
                    // Example of mapping?
                    Calendar imposterCal = Calendar.getInstance();
                    imposterCal.setTime(date);
                    imposterCal.set(Calendar.YEAR, 2001);

                    return imposterCal.getTime();
                })
                .setFirstName("Bob")
                .setJob("Painter and Decorator")
                .buildPartial();

        assertFakeVentura(imposterVentura);
    }

    /**
     * Assert that our Faked object is, indeed, the true Ace Ventura - Pet Detective
     *
     * @param fakeVentura FakeVentura
     */
    public void assertFakeVentura(FakeVentura fakeVentura) {
        getLogger().info("Asserting: {}", fakeVentura);

        assertThat(fakeVentura.getFirstName(), is("Ace"));
        assertThat(fakeVentura.getLastName(), is("Ventura"));
        assertThat(fakeVentura.getJob(), is("Pet Detective"));
        assertThat(fakeVentura.getMiddleName(), is(Optional.empty()));
    }
}

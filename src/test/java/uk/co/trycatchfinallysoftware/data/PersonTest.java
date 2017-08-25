package uk.co.trycatchfinallysoftware.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.trycatchfinallysoftware.TestApplication;

import java.time.LocalDate;
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
public class PersonTest {
    @Test
    public void testPersonBuilder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1984, 12, 10);

        Person person = new Person.Builder()
                .setDateOfBirth(calendar.getTime())
                .build();

        assertPerson(person);
    }

    /**
     * Arbitrary testing of our FreeBuilder built Object
     *
     * @param person Person
     */
    private void assertPerson(Person person) {
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

        System.out.println(fakeVentura); // Simple output of properties
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
        FakeVentura imposterVentura = new FakeVentura.Builder()
                .mapFirstName(firstname -> "Bob")
                .buildPartial();

        assertFakeVentura(imposterVentura);
    }

    /**
     * Assert that our Faked object is, indeed, the true Ace Ventura - Pet Detective
     *
     * @param fakeVentura FakeVentura
     */
    public void assertFakeVentura(FakeVentura fakeVentura) {
        assertThat(fakeVentura.getFirstName(), is("Ace"));
        assertThat(fakeVentura.getLastName(), is("Ventura"));
        assertThat(fakeVentura.getJob(), is("Pet Detective"));
        assertThat(fakeVentura.getMiddleName(), is(Optional.empty()));

    }
}

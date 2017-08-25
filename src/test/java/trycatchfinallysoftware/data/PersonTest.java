package trycatchfinallysoftware.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ContextConfiguration(

)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonTest {
    @Test
    public void testBuilder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1984, 12, 10);

        Person person = new Person.Builder()
                .setAge(33)
                .setDateOfBirth(calendar.getTime())
                .build();

        /*
        Arbitrary testing of our FreeBuilder built Object
         */
        assertThat(person, instanceOf(Person.class));
        assertThat(person.getAge(), greaterThan(21));
        assertThat(person.getFirstname(), not(isEmptyString()));
        assertThat(person.getLastname().length(), lessThanOrEqualTo(100));
        assertThat(person.getDateOfBirth(), instanceOf(Date.class));
    }
}

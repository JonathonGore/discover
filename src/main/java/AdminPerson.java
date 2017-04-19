/**
 * Created by jack on 2017-03-17.
 */
public class AdminPerson extends Person {

    private final String test;

    protected AdminPerson(AdminBuilder ab) {
        super(ab);
        test = ab.test;
        // Set admin specific variables from admin builder here

    }

    public static class AdminBuilder extends PersonBuilder<PersonBuilder> {

        private String test;

        public AdminBuilder() {}

        public AdminBuilder setTest(String test) {
            this.test = test;
            return this;
        }

        public Person build() { return new Person(this); }

    }
}

/**
 * Created by jack on 2017-03-18.
 */
public class HostPerson extends Person {

    private final String test;

    protected HostPerson(HostBuilder ab) {
        super(ab);
        test = ab.test;
        // Set admin specific variables from admin builder here

    }

    public static class HostBuilder extends PersonBuilder<PersonBuilder> {

        private String test;

        public HostBuilder() {}

        public HostBuilder setTest(String test) {
            this.test = test;
            return this;
        }

        public Person build() { return new Person(this); }

    }
}

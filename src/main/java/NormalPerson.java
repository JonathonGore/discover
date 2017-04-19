/**
 * Created by jack on 2017-03-18.
 */
public class NormalPerson extends Person {

    private final String test;

    protected NormalPerson(NormalBuilder ab) {
        super(ab);
        test = ab.test;
        // Set admin specific variables from admin builder here

    }

    public static class NormalBuilder extends PersonBuilder<PersonBuilder> {

        private String test;

        public NormalBuilder() {}

        public NormalBuilder setTest(String test) {
            this.test = test;
            return this;
        }

        public Person build() { return new Person(this); }

    }
}
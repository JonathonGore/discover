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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HostPerson that = (HostPerson) o;

        return test != null ? test.equals(that.test) : that.test == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
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

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AdminPerson that = (AdminPerson) o;

        return test != null ? test.equals(that.test) : that.test == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (test != null ? test.hashCode() : 0);
        return result;
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

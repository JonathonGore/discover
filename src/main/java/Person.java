/**
 * Created by jack on 2017-03-17.
 * This is the base class NOT for Users of the app but rather attendees,
 * hosts and admins of an event.
 *
 * i.e could be multiple Person's with the same userId
 */

public class Person {

    private final String userUUID;
    private final PersonType personType;

    protected Person(PersonBuilder pb) {
        this.userUUID = pb.userUUID;
        this.personType = pb.personType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (userUUID != null ? !userUUID.equals(person.userUUID) : person.userUUID != null) return false;
        return personType == person.personType;

    }

    @Override
    public int hashCode() {
        int result = userUUID != null ? userUUID.hashCode() : 0;
        result = 31 * result + (personType != null ? personType.hashCode() : 0);
        return result;
    }

    public String getUserUUID() {
        return this.userUUID;
    }

    public PersonType getPersonType() {
        return this.personType;
    }

    // Generic builder for derived classes
    public static class PersonBuilder<T extends PersonBuilder> {
        private String userUUID;
        private PersonType personType;

        /**
         * Constructor for creating a NEW person (no previous data)
         */
        public PersonBuilder() {}

        /**
         * Constructor for updating a person (previous data)
         */
        public PersonBuilder(Person person) {
            // Use getters to update PersonBuilder with all fields from the event..
            this.userUUID = person.getUserUUID();
            this.personType = person.getPersonType();
        }

        // Setters

        public T setUserUUID(String userUUID) {
            this.userUUID = userUUID;
            return (T) this;
        }

        public T setPersonType(PersonType personType) {
            this.personType = personType;
            return (T) this;
        }

        /**
         * Create a person from the person builder
         * @return the new Person
         */
        public Person build() {
            return new Person(this);
        }
    }
}

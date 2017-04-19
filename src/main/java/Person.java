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

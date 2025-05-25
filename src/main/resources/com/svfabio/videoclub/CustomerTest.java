package com.svfabio.videoclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("Fabio");
    }

    @Test
    public void testStatementWithRegularMovie() {
        Movie movie = new Movie("The Matrix", Movie.REGULAR);
        Rental rental = new Rental(movie, 3); // 2 + (3 - 2) * 1.5 = 3.5
        customer.addRental(rental);

        String expected = ""
                + "Rental Record for Fabio\n"
                + "\tThe Matrix\t3.5\n"
                + "Amount owed is 3.5\n"
                + "You earned 1 frequent renter points\n";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementWithNewReleaseMovie() {
        Movie movie = new Movie("Dune", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie, 2); // 2 * 3 = 6
        customer.addRental(rental);

        String expected = ""
                + "Rental Record for Fabio\n"
                + "\tDune\t6.0\n"
                + "Amount owed is 6.0\n"
                + "You earned 2 frequent renter points\n";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementWithChildrensMovie() {
        Movie movie = new Movie("Toy Story", Movie.CHILDRENS);
        Rental rental = new Rental(movie, 4); // 1.5 + (4 - 3) * 1.5 = 3.0
        customer.addRental(rental);

        String expected = ""
                + "Rental Record for Fabio\n"
                + "\tToy Story\t3.0\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter points\n";

        assertEquals(expected, customer.statement());
    }

    @Test
    public void testStatementWithMultipleRentals() {
        customer.addRental(new Rental(new Movie("Interstellar", Movie.NEW_RELEASE), 1)); // 3.0
        customer.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 5));         // 1.5 + (5 - 3)*1.5 = 4.5
        customer.addRental(new Rental(new Movie("The Godfather", Movie.REGULAR), 1));    // 2.0

        String expected = ""
                + "Rental Record for Fabio\n"
                + "\tInterstellar\t3.0\n"
                + "\tFrozen\t4.5\n"
                + "\tThe Godfather\t2.0\n"
                + "Amount owed is 9.5\n"
                + "You earned 3 frequent renter points\n";

        assertEquals(expected, customer.statement());
    }
}

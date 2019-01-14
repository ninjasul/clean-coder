import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {
    private Statement statement;

    @Before
    public void setUp() {
        statement = new Statement("Customer");
    }

    @Test
    public void testSingleNewReleaseStatementTotals() {
        statement.addRental(new Rental(new NewReleaseMovie("The Cell"), 3));
        statement.generate();
        assertEquals(9.0d, statement.getTotal(), 0);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatementTotals() {
        statement.addRental(new Rental(new NewReleaseMovie("The Cell"), 3));
        statement.addRental(new Rental(new NewReleaseMovie("The Tigger Movie"), 3));
        statement.generate();
        assertEquals(18.0, statement.getTotal(), 0);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrenStatementTotals() {
        statement.addRental(new Rental(new ChildrensMovie("The Tigger Movie"), 3));
        statement.generate();
        assertEquals(1.5, statement.getTotal(), 0);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(new Rental(new RegularMovie("Plan 9 from Outer Space"), 1));
        statement.addRental(new Rental(new RegularMovie("8 1/2"), 2));
        statement.addRental(new Rental(new RegularMovie("Eraserhead"), 3));
        statement.generate();
        assertEquals(7.5, statement.getTotal(), 0);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(new Rental(new RegularMovie("Plan 9 from Outer Space"), 1));
        statement.addRental(new Rental(new RegularMovie("8 1/2"), 2));
        statement.addRental(new Rental(new RegularMovie("Eraserhead"), 3));
        assertEquals(
                "Rental Record for Customer\n" +
                        "\tPlan 9 from Outer Space\t2.0\n" +
                        "\t8 1/2\t2.0\n" +
                        "\tEraserhead\t3.5\n" +
                        "Amount owed is 7.5\n" +
                        "You earned 3 frequent renter points",
                statement.generate());
    }
}
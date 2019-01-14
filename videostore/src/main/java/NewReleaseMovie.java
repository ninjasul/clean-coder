public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    protected double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    protected int determineFrequentRentalPoint(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
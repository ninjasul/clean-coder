package movie;

public class Movie {
    private int sumOfRate = 0;
    private int countOfRate = 0;

    public Integer averageRating() {
        return countOfRate != 0 ? sumOfRate / countOfRate : 0;
    }

    public void rate(int rate) {
        sumOfRate += rate;
        countOfRate++;
    }
}
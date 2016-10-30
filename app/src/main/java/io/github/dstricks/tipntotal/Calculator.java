package io.github.dstricks.tipntotal;

public class Calculator {
    double amount = 0;
    double fifteen = 0;
    double fifteenAmount = 0;
    double eighteen = 0;
    double eighteenAmount = 0;
    double twenty = 0;
    double twentyAmount = 0;

    public void update(double amt) {
        amount = amt;
        fifteen = .15 * amount;
        fifteenAmount = fifteen + amount;

        eighteen = .18 * amount;
        eighteenAmount = eighteen + amount;

        twenty = .2 * amount;
        twentyAmount = twenty + amount;
    }
}

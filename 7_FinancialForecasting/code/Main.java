class FinancialForecast {
    static double predictValue(double current, double rate, int years) {
        if (years == 0) return current;
        return predictValue(current * (1 + rate/100), rate, years - 1);
    }

    static double predictValueMemo(double current, double rate, int years, double[] memo) {
        if (years == 0) return current;
        if (memo[years] != 0) return memo[years];
        memo[years] = predictValueMemo(current * (1 + rate/100), rate, years - 1, memo);
        return memo[years];
    }
}

public class Main {
    public static void main(String[] args) {
        double result = FinancialForecast.predictValue(1000, 5, 3);
        System.out.println(result);

        double[] memo = new double[4];
        double resultMemo = FinancialForecast.predictValueMemo(1000, 5, 3, memo);
        System.out.println(resultMemo);
    }
}
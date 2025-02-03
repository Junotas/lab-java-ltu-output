package com.lulea;

/**
 * The program calculates the charging time for an electric car based on
 * different voltage and current combinations and outputs the result in a tabular format.
 *
 * 1. Declare constants for battery capacity, voltage values, and current values.
 * 2. Compute the charging power using the provided formulas.
 * 3. Convert power from watts (W) to kilowatts (kW).
 * 4. Calculate the charging time using the formula: battery capacity ÷ charging power.
 * 5. Round all values to two decimal places using Math.round().
 * 6. Print the results in a formatted table.
 *
 * @author Ludvig Fendert (ludfen-4)
 */
public class Main {

    // Constants
    public static final double BATTERY_CAPACITY_KWH = 35.8;
    public static final int SINGLE_PHASE_VOLTAGE = 230;
    public static final int THREE_PHASE_VOLTAGE = 400;
    public static final double SQRT_3 = Math.sqrt(3);
    public static final int CURRENT_10 = 10;
    public static final int CURRENT_16 = 16;
    public static final int CURRENT_32 = 32;
    public static final double ROUND_FACTOR = 100.0;
    public static final double WATTS_TO_KILOWATTS = 1000.0;

    public static void main(final String[] args) {
        // Print battery capacity label
        System.out.printf("Battery: %.1f (kwh)%n", BATTERY_CAPACITY_KWH);

        // Print table header
        System.out.printf("%-12s%-12s%-22s%-22s%n",
                "Current(A)", "Voltage(V)", "Charging Power(kW)", "Charging Time(h)");

        // Single-phase calculations (explicit calls)
        calculateAndPrint(CURRENT_10, SINGLE_PHASE_VOLTAGE, false);
        calculateAndPrint(CURRENT_16, SINGLE_PHASE_VOLTAGE, false);
        calculateAndPrint(CURRENT_32, SINGLE_PHASE_VOLTAGE, false);

        // Three-phase calculations (explicit calls)
        calculateAndPrint(CURRENT_10, THREE_PHASE_VOLTAGE, true);
        calculateAndPrint(CURRENT_16, THREE_PHASE_VOLTAGE, true);
        calculateAndPrint(CURRENT_32, THREE_PHASE_VOLTAGE, true);
    }

    /**
     * Calculates and prints the charging power and time based on the input parameters.
     *
     * @param current      the current (in A)
     * @param voltage      the voltage (in V)
     * @param isThreePhase whether the calculation is for a three-phase system
     */
    private static void calculateAndPrint(final int current, final int voltage,
                                          final boolean isThreePhase) {
        double powerW = isThreePhase
                ? current * voltage * SQRT_3
                : current * voltage;

        // Convert power to kW
        double powerKW = powerW / WATTS_TO_KILOWATTS;

        // Calculate charging time
        double chargingTime = BATTERY_CAPACITY_KWH / powerKW;

        // Round values to 2 decimal places
        powerKW = Math.round(powerKW * ROUND_FACTOR) / ROUND_FACTOR;
        chargingTime = Math.round(chargingTime * ROUND_FACTOR) / ROUND_FACTOR;

        // Print formatted row
        System.out.printf("%-12.1f%-12.1f%-22.2f%-22.2f%n",
                (double) current, (double) voltage, powerKW, chargingTime);
    }
}
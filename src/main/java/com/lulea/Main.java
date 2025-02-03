package com.lulea;

/**
 * The program calculates the charging time for an electric car based on
 * different voltage and current combinations and outputs the result in a tabular format.
 *
 * Pseudocode:
 *   1. Print the battery capacity.
 *   2. Print the table header with the required formatting.
 *   3. For each of the five combinations:
 *       a. Calculate the charging power:
 *          - Single-phase: power = current * voltage.
 *          - Three-phase:  power = current * voltage * sqrt(3).
 *       b. Convert power to kilowatts (divide by 1000).
 *       c. Calculate the charging time (battery capacity / charging power).
 *       d. Round both values to two decimal places.
 *       e. Print the formatted row.
 *
 * @author Ludvig Fendert (ludfen-4)
 */
public class Main {

    // Constants
    public static final double BATTERY_CAPACITY_KWH = 35.8;
    public static final double WATTS_TO_KILOWATTS = 1000.0;
    public static final double SINGLE_PHASE_VOLTAGE = 230.0;
    public static final double THREE_PHASE_VOLTAGE = 400.0;
    public static final double CURRENT_10 = 10.0;
    public static final double CURRENT_16 = 16.0;
    public static final double CURRENT_32 = 32.0;
    public static final double SQRT_3 = Math.sqrt(3);
    public static final int DECIMAL_PLACES = 2;
    public static final double SCALE = Math.pow(10, DECIMAL_PLACES); // Scale factor for rounding (i.e., 100)

    public static void main(final String[] args) {
        // Print battery capacity with two decimal places
        System.out.printf("Battery: %.2f (kwh)%n", BATTERY_CAPACITY_KWH);

        // Print table header exactly as specified by the assignment
        System.out.printf("%-15s %-15s %-22s %-15s%n",
                "Current(A)", "Voltage(V)", "Charging Power(kW)", "Charging Time(h)");

        // Single-phase calculations (only for 10 A and 16 A)
        calculateAndPrint(CURRENT_10, SINGLE_PHASE_VOLTAGE, false);
        calculateAndPrint(CURRENT_16, SINGLE_PHASE_VOLTAGE, false);

        // Three-phase calculations (for 10 A, 16 A, and 32 A)
        calculateAndPrint(CURRENT_10, THREE_PHASE_VOLTAGE, true);
        calculateAndPrint(CURRENT_16, THREE_PHASE_VOLTAGE, true);
        calculateAndPrint(CURRENT_32, THREE_PHASE_VOLTAGE, true);
    }

    /**
     * Calculates and prints the charging power (in kW) and charging time (in hours)
     * for a given current and voltage. Uses the three-phase formula when needed.
     *
     * @param current      the current in amperes (A)
     * @param voltage      the voltage in volts (V)
     * @param isThreePhase true if three-phase, false if single-phase
     */
    private static void calculateAndPrint(final double current, final double voltage, final boolean isThreePhase) {
        // Calculate charging power in watts; three-phase uses an extra factor of sqrt(3)
        double powerW = isThreePhase ? current * voltage * SQRT_3 : current * voltage;

        // Convert power from watts to kilowatts
        double powerKW = powerW / WATTS_TO_KILOWATTS;

        // Calculate charging time (battery capacity in kWh divided by charging power in kW)
        double chargingTime = BATTERY_CAPACITY_KWH / powerKW;

        // Round the charging power and charging time to two decimal places
        powerKW = Math.round(powerKW * SCALE) / SCALE;
        chargingTime = Math.round(chargingTime * SCALE) / SCALE;

        // Print the row using the specified formatting
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f%n", current, voltage, powerKW, chargingTime);
    }
}

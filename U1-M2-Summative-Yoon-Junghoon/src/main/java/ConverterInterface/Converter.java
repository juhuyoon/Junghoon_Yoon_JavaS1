package ConverterInterface;

public interface Converter {

    /**
     * This method converts the given number into its corresponding month.
     * @param monthNumber the number you wish to convert to a month
     * @return the name of the corresponding month if the parameter value is between 1 and 12; error message otherwise
     */

    default String convertMonth(int monthNumber){
        return "";
    };

    /**
     * This method converts the given number into its corresponding day of the week.
     * @param dayNumber number you wish to convert to a day of the week.
     * @return the name of the corresponding day if the parameter value is between  1 and 7, error message otherwise.
     * The first day of the week is Sunday
     */

    String convertDay(int dayNumber);


}

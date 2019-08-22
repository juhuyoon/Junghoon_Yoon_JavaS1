package ConverterInterface;

//Interface implementation
public class ConverterSwitch implements Converter {

    //abstract method pulled from interface
    @Override
    public String convertMonth(int monthNumber) {
        //Main switch case logic for convertMonth
        String month = null;
        String errorMessage = "Please enter a number between 1 and 12";
        switch(monthNumber) {
            case (1) :
                month = "January";
                break;
            case (2) :
                month = "February";
                break;
            case (3) :
                month = "March";
                break;
            case (4) :
                month = "April";
                break;
            case (5) :
                month = "May";
                break;
            case (6) :
                month = "June";
                break;
            case (7) :
                month = "July";
                break;
            case (8) :
                month = "August";
                break;
            case (9) :
                month = "September";
                break;
            case (10) :
                month = "October";
                break;
            case (11) :
                month = "November";
                break;
            case (12) :
                month = "December";
                break;
            default:
                return errorMessage;
        }
        return month;
    }

    //abstract method pulled from convertDay
    @Override
    public String convertDay(int dayNumber) {
        String day = null;
        String errorMessage = "Please enter a number between 1 and 12";
        //Main switch case logic for convertDay

        switch (dayNumber) {

            case 1: {
                day = "Sunday";
                break;
            }
            case 2: {
                day = "Monday";
                break;
            }
            case 3: {
                day = "Tuesday";
                break;
            }
            case 4: {
                day = "Wednesday";
                break;
            }
            case 5: {
                day = "Thursday";
                break;
            }
            case 6: {
                day = "Friday";
                break;
            }
            case 7: {
                day = "Saturday";
                break;
            }
            default: {
                return errorMessage;
            }
        }
        return day;
    }
}

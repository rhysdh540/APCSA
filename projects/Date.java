import org.jetbrains.annotations.NotNull;

/**
 * ok so i didn't realize that there was already a Date class but i don't want to delete all this so yep here we go
 * also very javadocced (is that a word?)
 * i recommend viewing this using an ide/code editor that renders javadoc inline (like intellij)
 */
public class Date {
    /**
     * The day of the month in the date.
     */
    private int day;

    /**
     * The month of the year in the date, represented by 1-12 meaning January to December.
     */
    private int month;

    /**
     * What year the Date is in.
     */
    private int year;

    /**
     * Used when converting the Date to a String, tells whether to use DD/MM/YYYY (false) or MM/DD/YYYY (true).
     */
    private boolean system;

    /**
     * Creates a new Date object with the default date 1/1/0000.
     */
    public Date() {
        day = 1;
        month = 1;
        year = 0;
        system = true;
    }
    /**
     * Creates a new Date object with the specified values:
     * @param newDay the day for the new Date.
     * @param newMonth the month for the new Date.
     * @param newYear the year for the new Date.
     */
    public Date(int newDay, int newMonth, int newYear, boolean newSystem) {
        day = newDay;
        month = newMonth;
        year = newYear;
        system = newSystem;
        fixOverflow();
    }
    /**
     * Creates a new Date object with the specified values:
     * @param newDay the day for the new Date.
     * @param newMonth the month for the new Date.
     * @param newYear the year for the new Date.
     */
    public Date(int newDay, int newMonth, int newYear) {
        this(newDay, newMonth, newYear, true);
    }

    /**
     * Creates a new Date object using the specified String.
     * @param date a String in the format DD/MM/YYYY or MM/DD/YYYY.
     * @param useAmericanDate whether to use DD/MM/YYYY (false) or MM/DD/YYYY (true).
     */
    public Date(@NotNull String date, boolean useAmericanDate){
        if(date.indexOf('/') == date.lastIndexOf('/'))
            throw new IllegalArgumentException("The date must be in the format DD/MM/YYYY or MM/DD/YYYY.");
        system = useAmericanDate;
        int a = Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")));
        int b = Integer.parseInt(date.substring(0,date.indexOf("/")));
        day = system ? a : b;
        month = system ? b : a;
        year = Integer.parseInt(date.substring(date.lastIndexOf("/")+1, date.length()));
        fixOverflow();
    }

    /**
     * Creates a new Date object using the specified String.
     * @param date a String in the format MM/DD/YYYY.
     */
    public Date(@NotNull String date){
        this(date, true);
    }

    /**
     * Creates a new Date object using the specified Date.
     * @param date the Date to copy.
     */
    public Date(@NotNull Date date){
        day = date.getDay();
        month = date.getMonth();
        year = date.getYear();
        system = date.getSystem();
    }

    /* GETTERS AND SETTERS */
    public int getDay() {return day;}
    public int getMonth() {return month;}
    public int getYear() {return year;}
    public boolean getSystem() {return system;}
    public void setDay(int newDay) {day = newDay;}
    public void setMonth(int newMonth) {month = newMonth;}
    public void setYear(int newYear) {year = newYear;}
    public void setSystem(boolean newSystem) {system = newSystem;}
    public void toggleSystem() {system = !system;}
    public String getMonthAsString(){
        return switch (month){
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "ERROR";
        };
    }
    /**
     * Fixes the day and month if they are out of bounds.
     */
    public void fixOverflow(){
        for(int i : new int[2]){
            while(day > 31 &&(month==1||month==3||month==5||month==7||month==8||month==10||month==12)){
                month += day/31;
                day %=31;
            }while(day > 30 && (month==4||month==6||month==9||month==11)){
                month += day/30;
                day %=30;
            }while(day>28&&month==2){
                month += day/28;
                day %=28;
            }
            while(month>12){
                year += month/12;
                month %= 12;
            }
            while(day < 1){
                if(month==2||month==4||month==6||month==8||month==9||month==11||month==1)
                    day += 31;
                if(month==5||month==7||month==10||month==12)
                    day += 30;
                if(month==3)
                    day+=28;
                month--;
            }
            while(month<1){
                month+=12;
                year--;
            }
        }
    }
    /**
     * Adds to the Date.
     * @param days the number of days to advance.
     * @param months the number of months to advance.
     * @param years the number of years to advance.
     * @see <a href="https://www.timeanddate.com/date/dateadd.html">Date Calculator</a>
     */
    public void advance(int days, int months, int years) {
        day += days;
        month += months;
        year += years;
        fixOverflow();
    }

    /**
     * Static version of {@link #advance}
     * @param d the date to advance.
     * @param days the number of days to advance.
     * @param months the number of months to advance.
     * @param years the number of ears to advance.
     * @return the advanced date.
     * @see <a href="https://www.timeanddate.com/date/dateadd.html">Date Calculator</a>
     */
    public static @NotNull Date advancedDate(Date d, int days, int months, int years) {
        Date c = new Date(d);
        c.advance(days, months, years);
        return c;
    }

    /**
     * Changes the Date to a String.
     * @return the String version of the date
     */
    public String toString() {
        return system ? month + "/" + day + "/" + year/* + " (mm/dd/yyyy)"*/ : day + "/" + month + "/" + year/* + " (dd/mm/yyyy)"*/;
    }

    /**
     * Checks if the Date is equal to another Date. (systems not checked)
     * @param d the Date to compare to.
     * @return whether the Dates are equal.
     */
    public boolean equals(@NotNull Date d) {
        return day == d.getDay() && month == d.getMonth() && year == d.getYear();
    }
    /**
     * Checks if the Date is equal to another String-based date.
     * @param d the Date to compare to.
     * @param useAmericanDate whether to use DD/MM/YYYY (false) or MM/DD/YYYY (true).
     * @return whether the Dates are equal.
     */
    public boolean equals(@NotNull String d, boolean useAmericanDate) {
        return equals(new Date(d, useAmericanDate));
    }

    /**
     * Returns if the Date is after another Date.
     * @param d the Date to compare to.
     * @return whether the Date is after the other Date.
     */
    public boolean isAfter(@NotNull Date d) {
        return year > d.getYear() || (year == d.getYear() && month > d.getMonth()) || (year == d.getYear() && month == d.getMonth() && day > d.getDay());
    }
    /**
     * Finds the day of the week that the Date is on using complex logical witchery.
     * @return the day of the week that the Date is on, as a String. (Sunday-Saturday)
     * @see <a href="https://artofmemory.com/blog/how-to-calculate-the-day-of-the-week/">How to Calculate the Day of the Week from Any Date</a>
     */
    public String dayOfWeek() {
        int y = this.getYear(), m = this.getMonth();
        int monthCode = new int[]{0,3,3,6,1,4,6,2,5,0,3,5}[m-1];
        int yearCode = (y % 100 + (y % 100 / 4)) % 7;
        int leapCode = ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0) && m < 3) ? -1 : 0;
        int centuryCode = new int[]{6,4,2,0}[(y - (y % 100)) / 100 % 4];
        return switch ((yearCode + monthCode + centuryCode + this.getDay() + leapCode) % 7) {
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "ERROR";
        };
    }

    /**
     * basically {@link #dayOfWeek()} but unintelligible
     */
    public String dayOfWeekButItDoesntMakeAnySense() {
        return switch ((((this.getYear()%100+(this.getYear()%100/4))%7)+(new int[]{0,3,3,6,1,4,6,2,5,0,3,5}[this.getMonth()-1])+(new int[]{6,4,2,0}[(this.getYear()-(this.getYear()%100))/100%4])+this.getDay()+(((this.getYear()%4==0&&this.getYear()%100!=0)||(this.getYear()%400==0)&&this.getMonth()<3)?-1:0))%7) {
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "ERROR";
        };
    }
}
/**
 *
 */
public class Date { // ok so i didn't realize that there was already a Date class but i don't want to delete all this so yep here we go
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
    public Date(int newDay, int newMonth, int newYear) {
        day = newDay;
        month = newMonth;
        year = newYear;
        system = true;
        fixOverflow();
    }

    /**
     * Creates a new Date object using the specified String.
     * @param date a String in the format DD/MM/YYYY or MM/DD/YYYY.
     * @param useAmericanDate whether to use DD/MM/YYYY (false) or MM/DD/YYYY (true).
     */
    public Date(String date, boolean useAmericanDate){
        system = useAmericanDate;
        int a = Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")));
        int b = Integer.parseInt(date.substring(0,date.indexOf("/")));
        day = system ? a : b;
        month = system ? b : a;
        year = Integer.parseInt(date.substring(date.lastIndexOf("/")+1, date.length()));
        fixOverflow();
    }
    /**
     * Creates a new Date object with the same fields as the input Date,
     * essentially cloning it.
     * @param d the Date to clone.
     */
    public Date(Date d) {
        day = d.getDay();
        month = d.getMonth();
        year = d.getYear();
        system = d.getSystem();
        fixOverflow();
    }

    /* GETTERS AND SETTERS */
    public int getDay() {return day;}
    public int getMonth() {return month;}
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
    public int getYear() {return year;}
    public boolean getSystem() {return system;}
    public void setDay(int newDay) {day = newDay;}
    public void setMonth(int newMonth) {month = newMonth;}
    public void setYear(int newYear) {year = newYear;}
    /**
     * Switches between MM/DD/YYYY and DD/MM/YYYY systems.
     */
    public void toggleSystem() {system = !system;}

    /**
     * Calls {@link #thing} twice.
     */
    public void fixOverflow(){
        thing();
        thing();
    }
    /**
     * Checks if the Date is valid, and if not, fixes it.
     */
    private void thing() { // very creative and descriptive method name

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
    public static Date advancedDate(Date d, int days, int months, int years) {
        Date c = new Date(d);
        c.advance(days, months, years);
        return c;
    }

    /**
     * Changes the Date to a String, for printing or something idk.
     * @return the String version of the date
     */
    public String toString() {
        return (system) ? (month + "/" + day + "/" + year/* + " (mm/dd/yyyy)"*/) : (day + "/" + month + "/" + year/* + " (dd/mm/yyyy)"*/);
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
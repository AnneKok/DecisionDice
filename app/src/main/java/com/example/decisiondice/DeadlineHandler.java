package com.example.decisiondice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class contains all methods related to the
 * stand-alone functionality of the Tweetbot module,
 * namely notifying users of the next deadline.
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */
public class DeadlineHandler extends Tweeter {

    /**
     * Adds deadlines to the {@code List} of deadlines by passing
     * them to the adder method.
     */
    public void setDeadlines() {
        addDeadline("the mini-PDP", 2017, 1, 13, 8, 45);
        addDeadline("Challenge 1", 2017, 2, 10, 17, 0);
        addDeadline("Challenge 2", 2017, 3, 10, 17, 0);
    }

    /**
     * Adds parameterized deadlines to the {@code List} of
     * deadlines.
     *
     * @param  item The name of the deliverable.
     * @param  year The year of the due date.
     * @param  month The month of the due date. January is 0, etc.
     * @param  day The day of the due date.
     * @param  hour The hour of the deadline. In 24-hour format.
     * @param  minute The minutes of the deadline.
     * @throws  Exception if the date is invalid.
     */
    private void addDeadline(String item, int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(year, month, day, hour, minute);
        try {
            cal.getTime();
            Deadline dl = new Deadline(item, cal);
            deadlines.add(dl);
        } catch (Exception e) {
            System.out.println("Invalid date");
        }
    }

    /**
     * Gets the nearest deadline from the {@code List} of
     * deadlines.
     *
     * @return The nearest deadline if there is one; otherwise,
     *  a {@code Deadline} with description "there are no more
     *  deadlines" and the current date.
     */
    public Deadline getNextDeadline() {
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        if(deadlines != null) {
            for(Deadline dl : deadlines) {
                if (!(dl.getCal().getTimeInMillis() < currentTime)) {
                    return dl;
                }
            }
        }
        return new Deadline("-- there are no more deadlines! --", Calendar.getInstance());
    }

}

/**
 * This class is a data type containing a {@code String} and a
 * {@code Calendar}, which together represent a deadline.
 *
 * @author  Marcin van de Ven
 * @author  Dianne Vasseur
 * @author  Manon Blankendaal
 * @author  Anne Kok
 */

class Deadline {

    private final String item;
    private final Calendar time;

    /**
     * Constructor for objects of type Deadline.
     *
     * @param  item The name of the deliverable.
     * @param  time The date and time of the deadline, in
     *  {@code Calendar} format.
     */
    public Deadline(String item, Calendar time) {
        this.item = item;
        this.time = time;
    }

    /**
     * Gets the deliverable of a deadline.
     *
     * return the {@code item}, the deliverable name, of a
     * {@code Deadline}.
     */
    public String getItem() { return item; }

    /**
     * Gets the due date and time of a deadline.
     *
     * return the {@code time}, the date and time, of a
     * {@code Deadline}.
     */
    public Calendar getCal() { return time; }

}
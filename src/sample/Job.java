package sample;

/*
    Creates a Job Object.
    Overrides Equals method and toString method.
 */

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class Job {
    private String roleName;
    private String companyName;
    private LocalDate dateAdded;
    private LocalDate dateApplied;
    private LocalDate lastDate;
    private JobStatus jobStatus;
    private long daysLeftToApply;

    public Job(String roleName, String companyName, String lastDate){
       this(roleName, companyName, lastDate , null);
    }

    public Job(String roleName, String companyName, String lastDate , String datApplied) throws IllegalArgumentException, DateTimeException {
        this.roleName = roleName;
        this.companyName = companyName;
        List<Integer> intListLastDate= dateStringToInt(lastDate);
        List<Integer> intListDateApplued = dateStringToInt(datApplied);
        this.lastDate =  LocalDate.of(intListLastDate.get(0), intListLastDate.get(1), intListLastDate.get(2));

        // case when job not applied
        if(intListDateApplued==null){
            dateApplied = null;
        }
        else{
            dateApplied = LocalDate.of(intListDateApplued.get(0), intListDateApplued.get(1), intListDateApplued.get(2));
        }


        this.dateAdded = LocalDate.now();
        this.daysLeftToApply = DAYS.between(LocalDate.now(), this.lastDate);

        // Urgent Categorization
        if(daysLeftToApply<5){
            jobStatus = JobStatus.urgent;
        }
        else{
            jobStatus =  JobStatus.inProgress;
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public String getRoleName() {
        return roleName;
    }

    public long getDaysLeftToApply(){
        return daysLeftToApply;
    }
    /**
     * Custom Job reorientation
     *
     * @return - String
     */
    @Override
    public String toString(){
        if(jobStatus != JobStatus.applied){
            return String.format("%s\t%-20s\t%-20s\t%-20s\n", jobStatus, roleName, companyName, lastDate);
        }
        else{
            return String.format("%s\t%-20s\t%-20s\t%-20s\t%-20s\n", jobStatus, roleName, companyName, lastDate, dateApplied);
        }
    }

    /**
     * Toggles the job status between Inprogress and applied.
     *
     * @return - void
     */
    public void toggleApplicationStatus(){
        if(jobStatus==JobStatus.inProgress || jobStatus ==JobStatus.urgent){
            jobStatus=JobStatus.applied;
        }
        else{
            jobStatus = JobStatus.inProgress;
        }
    }

    /**
     * Returns the date on which job is applied
     *
     * @return - LocalDate
     */
    public LocalDate getDateApplied() {
        return dateApplied;
    }

    /**
     * Sets today's date as applying date.
     *
     * @return - void
     */
    public void appliedToday(){
        dateApplied = LocalDate.now();
    }

    /**
     * Helper method
     *
     * @return - integers numbers of date
     */
    public static List<Integer> dateStringToInt(String date) throws IllegalArgumentException{

        if(date==null){
            return null;
        }

        String[] dateList;
        boolean usedSlash = false;
        if (date.contains("/")){
            dateList = date.strip().split("/");
            usedSlash = true;
        }
        else{
            dateList = date.strip().split("-");
        }

        List<Integer> intArrayList = Arrays.stream(dateList).map(i->Integer.parseInt(i)).collect(Collectors.toList());
        if(usedSlash) {
            Collections.reverse(intArrayList);
        }

        if(intArrayList.size()!=3){
            throw new IllegalArgumentException("Invalid Date");
        }
        return  intArrayList;
    }
}

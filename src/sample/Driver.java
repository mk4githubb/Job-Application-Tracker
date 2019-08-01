package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
    Driver Class for the application.
    Responsible for data structures and files.
 */
public class Driver {

    private static File inProgressJobs = new File("inProgressJobs.txt");
    private static File appliedJobs = new File("appliedJobs.txt");

    private static Tree inProgressJobsTree = new Tree();
    private static Tree appliedJobsTree = new Tree();

    /**
     * Sets up the necessary files.
     *
     * @return - void
     */

    public static void setup(){
        try {
            inProgressJobs.createNewFile();
            appliedJobs.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Driver.load();
    }


    /**
     * Returns the tree storing in Progress Jobs.
     *
     * @return - Tree
     */
    public static Tree getInProgressTree(){
        return inProgressJobsTree;
    }


    /**
     * Returns the tree storing in applied Jobs.
     *
     * @return - Tree
     */
    public static Tree getAppliedTree(){
        return appliedJobsTree;
    }

    /**
     * Returns In Order Transversal of InProgress Tree.
     *
     * @return - List of Jobs
     */
    public static List<Job> getInProgressTransversal(){
       return inProgressJobsTree.inOrderTransverse();
    }


    /**
     * Returns In Order Transversal of Applied Tree.
     *
     * @return - List of Jobs
     */
    public static List<Job> getAppliedTransversal(){
        return appliedJobsTree.inOrderTransverse();
    }


    /**
     * Creates and Returns a Job
     *
     * @param - vararg string
     *
     * @return - List of Jobs
     */
    public static Job addJob(String...args) throws IllegalArgumentException{
        if(args[0].equals("") && args[1].equals("")|| args[2].equals("")){
            return null;
        }

        Job newJob = new Job(args[0], args[1], args[2]);

        // Add job to the Tree of in-progress jobs.
        addJobToTreeOfJobs(newJob, inProgressJobsTree);
        return newJob;
    }


    /**
     * Adds Job to specified Tree.
     *
     * @return - void
     */
    public static void addJobToTreeOfJobs(Job job, Tree treeName){
        treeName.add(job);
    }


    /**
     * searches for a job using a keyword
     *
     * @return - List of Jobs
     */
    public static List<Job> searchJob(String keyword) {
        List<Job> foundJobs = new ArrayList<>();

        //      searches in in-progress jobs tree and adds the jobs in the list to display later.
        for (Job job : inProgressJobsTree.inOrderTransverse()) {
            try {
                if (job.getCompanyName().toLowerCase().contains(keyword.toLowerCase()) || job.getRoleName().toLowerCase().contains(keyword.toLowerCase()) && !foundJobs.contains(job)) {
                    foundJobs.add(job);
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

        //      searches in applied jobs tree and adds the jobs in the list to display later.
        for (Job job : appliedJobsTree.inOrderTransverse()) {
            try {
                if (job.getCompanyName().toLowerCase().contains(keyword.toLowerCase()) || job.getRoleName().toLowerCase().contains(keyword.toLowerCase()) && !foundJobs.contains(job)) {
                    foundJobs.add(job);
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        return foundJobs;
    }


    /**
     * loads the saved local job files and creates Job objects
     * populates Trees.
     *
     * @return - void
     */
    public static void load(){
        try {
            Scanner input = new Scanner(inProgressJobs);
            while (input.hasNext()){
                String[] line = input.nextLine().split("\t");
                Job newJob = new Job(line[1].trim(),line[2].trim(),line[3].trim());
                addJobToTreeOfJobs(newJob, inProgressJobsTree);
            }
            input.close();

            input = new Scanner(appliedJobs);
            while (input.hasNext()){
                String[] line = input.nextLine().split("\t");
                Job newJob = new Job(line[1].trim(),line[2].trim(),line[3].trim(),line[4].trim());
                newJob.toggleApplicationStatus();
                addJobToTreeOfJobs(newJob, appliedJobsTree);
            }
            input.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * saves the job data to files.
     *
     * @return - void
     */
    public static void updateAndSave(){
        try {
            PrintWriter printWriter = new PrintWriter(inProgressJobs);
            printWriter.flush();
            for(Job job: inProgressJobsTree.inOrderTransverse()){
                printWriter.print(job);
            }
            printWriter.close();

            printWriter = new PrintWriter(appliedJobs);
            for(Job job: appliedJobsTree.inOrderTransverse()){
                printWriter.print(job);
            }
            printWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Marks a Job applied
     *
     * @return - void
     */
    public static void markJobApplied(Job job){

        job.appliedToday();
        job.toggleApplicationStatus();

        inProgressJobsTree.remove(job);
        addJobToTreeOfJobs(job,appliedJobsTree);
    }

}
package org.esempio.courseinfo.cli;

import org.esempio.courseinfo.cli.service.CourseRetrievalService;
import org.esempio.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String... args) {
        LOG.info("CourseRetriever is starting...");
        if(args.length==0){
            LOG.warn("Please provide an author name as first argument");
            return;
        }
        try {
            retrieverCourses(args[0]);
            //utilizzo del record
//            PluralsightCourse course =
//                    new PluralsightCourse("id","title","00:20:14","https://url",false);
//            LOG.info("course: {}", course);
        }catch(Exception e){
            LOG.error("Unexpected error occured", e);
        }
    }

    private static void retrieverCourses(String authorId) {
        LOG.info("Retrieving courses for author '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(course -> !course.isRetired())
                .toList();
        LOG.info("Retrieved the following {} courses: {}",coursesToStore.size() ,coursesToStore);
    }
}

package com.in28minutes.soapcoursemanagement.soap.service;

import com.in28minutes.soapcoursemanagement.soap.bean.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseDetailsService {

    public enum Status {
        SUCCESS, FAILURE;
    }

    private static List<Course> courseList = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Spring", "10 Steps");
        Course course2 = new Course(2, "Spring MVC", "10 Examples");
        Course course3 = new Course(3, "Spring Boot", "6K Students");
        Course course4 = new Course(4, "Maven", "Popular maven course");
        courseList.addAll(List.of(course1, course2, course3, course4));
    }

    //get 1 course
    public Course findById(int id) {
        for(Course course : courseList) {
            if(course.getId()==id) {
                return course;
            }
        }
        return null;
    }

    //get all courses
    public List<Course> findAll() {
        return courseList;
    }

    //delete a course by id
    public Status deleteCourse(int id) {
        Iterator<Course> iterator = courseList.iterator();
        while(iterator.hasNext()) {
            Course course = iterator.next();
            if(course.getId()==id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

}

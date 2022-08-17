package com.in28minutes.soapcoursemanagement.soap;

import com.in28minutes.courses.*;
import com.in28minutes.soapcoursemanagement.soap.bean.Course;
import com.in28minutes.soapcoursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService courseDetailsService;

    //method
    //input - GetCourseDetailsRequest
    //output - GetCourseDetailsResponse

    // This will process anything from the namespace http://in28minutes.com/courses

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = courseDetailsService.findById(request.getId());

        if(course == null) {
            throw new CourseNotFoundException("Invalid Course Id " + request.getId());
        }

        return mapResponse(course);
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courseList = courseDetailsService.findAll();
        return mapResponseAll(courseList);
    }

    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
        DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
        CourseDetailsService.Status status = courseDetailsService.deleteCourse(request.getId());
        deleteCourseDetailsResponse.setStatus(mapStatus(status));
        return deleteCourseDetailsResponse;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if (status.toString().equals(Status.SUCCESS.toString())) {
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }

    private GetCourseDetailsResponse mapResponse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = mapCourse(course);
        response.setCourseDetails(courseDetails);
        return response;
    }

    private GetAllCourseDetailsResponse mapResponseAll(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for(Course course : courses) {
            CourseDetails courseDetails = mapCourse(course);
            response.getCourseDetails().add(courseDetails);
        }
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }


}

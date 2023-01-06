package com.manu.GradeR.Student;

import com.manu.GradeR.SchoolClass.SchoolClass;
import com.manu.GradeR.SchoolClass.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @GetMapping("schoolclasses/{schoolclassid}/students")
    public String showStudentList(Model model, @PathVariable Long schoolclassid) {

        SchoolClass currentClass = schoolClassRepository.findById(schoolclassid).get();

        model.addAttribute("currentClass", currentClass);

        return "student_list";
    }

    @PostMapping("schoolclasses/{schoolclassid}/newstudent")
    public ResponseEntity newStudent(@PathVariable Long schoolclassid, Student studentFormData) {

        SchoolClass schoolClass = schoolClassRepository.getReferenceById(schoolclassid);
        List<Student> students = schoolClass.getStudents();
        studentFormData.setSchoolClass(schoolClass);
        students.add(studentFormData);
        schoolClass.setStudents(students);
        schoolClassRepository.save(schoolClass);

        return new ResponseEntity(HttpStatus.OK);
    }
}
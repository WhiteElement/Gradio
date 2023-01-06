package com.manu.GradeR.SchoolClass;

import com.manu.GradeR.GradeTest.GradeTest;
import com.manu.GradeR.GradeTest.GradeTestRepository;
import com.manu.GradeR.GradeTest.GradeTestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SchoolClassController {

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    GradeTestRepository gradeTestRepository;

    @GetMapping("/")
    public String showSchoolClassPage(Model model) {

        List<SchoolClass> allSchoolClasses = schoolClassRepository.findAll();

        model.addAttribute("allSchoolClasses", allSchoolClasses);
        model.addAttribute("newSchoolClass", new SchoolClass());

        return "index";
    }

    @PostMapping("/")
    public String newSchoolClass(RedirectAttributes redirectAttributes, SchoolClass schoolClassFormData) {
        schoolClassRepository.save(schoolClassFormData);

        return "redirect:/";
    }

    @PostMapping("/updateSchoolClass")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateSchoolClass(SchoolClass schoolClassFormData) {
        schoolClassRepository.save(schoolClassFormData);
    }

    @GetMapping("/schoolclasses/{id}")
    public String showSingleClass(@PathVariable Long id, Model model) {

        SchoolClass currentSchoolClass = schoolClassRepository.findById(id).get();

        model.addAttribute("newGradeTest", new GradeTest());
        model.addAttribute("currentSchoolClass", currentSchoolClass);
        model.addAttribute("writtenGradeTests", gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.WRITTEN, currentSchoolClass));
        model.addAttribute("oralGradeTests", gradeTestRepository.findByGradeTypeAndSchoolClass(GradeTestType.ORAL, currentSchoolClass));


        return "single_class";
    }


}

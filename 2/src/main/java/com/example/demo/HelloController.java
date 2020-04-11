package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;



@RestController
public class HelloController {
    private ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        if (students.contains(student)){
            return "姓名重复";
        } else {
            students.add(student);
            return "添加成功";
        }
    }

    @GetMapping("/getAllStudent")
    public ArrayList<Student> getAllStudent() {
        return students;
    }

    @PostMapping("/getStudentByName")
    public Student getStudentByName(@RequestBody String name) {
        return students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/deleteStudentByName")
    public String deleteStudentByName(@RequestBody String name) {
        if (students.removeIf(student -> student.getName().equals(name))) {
            return "删除成功";
        } else {
            return "该学生不存在";
        }
    }


}

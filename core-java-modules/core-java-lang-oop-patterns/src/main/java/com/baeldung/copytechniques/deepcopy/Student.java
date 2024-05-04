package com.baeldung.copytechniques.deepcopy;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Student implements Cloneable {
    private String name;
    private Department department;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.department = new Department(this.department.getName());
        return cloned;
    }
}

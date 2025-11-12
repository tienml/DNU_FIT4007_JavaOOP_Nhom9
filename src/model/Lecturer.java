package model;


public class Lecturer extends Person {
    private String department;
    private String email;

    public Lecturer() {}

    public Lecturer(String id, String fullName, String department, String email) {
        super.id = id;
        super.fullName = fullName;
        this.department = department;
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Lecturer: " + fullName + " (" + id + "), Department: " + department + ", Email: " + email;
    }
}

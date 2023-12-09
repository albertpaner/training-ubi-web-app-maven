package model.dto;

public class UtenteDto {

    public UtenteDto() {
    }

    private int userId;
    private String userPassword;
    private String userEmail;
    private int roleId;
    private String firstName;
    private String lastName;
    private int userEvaluatorId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserEvaluatorId() {
        return userEvaluatorId;
    }

    public void setUserEvaluatorId(int userEvaluatorId) {
        this.userEvaluatorId = userEvaluatorId;
    }

    @Override
    public String toString() {
        return "UtenteDto{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", roleId=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEvaluatorId=" + userEvaluatorId +
                '}';
    }
}

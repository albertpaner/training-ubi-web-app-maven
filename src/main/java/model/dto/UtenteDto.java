package model.dto;

public class UtenteDto {

    public UtenteDto() {
    }

    private int userId;
    private String userEmail;
    private String firstName;
    private String lastName;
    private int userEvaluatorId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
                ", userEmail='" + userEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEvaluatorId=" + userEvaluatorId +
                '}';
    }
}

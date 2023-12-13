package model.Dto;

public class UtenteDto {
    private int userId;
    private String userPassword;
    private String userEmail;
    private int roleId;
    private String firstName;
    private String lastName;
    private int userManagerId;
    private String companyOp;
    private String userJob;
    private String userScope;
    private String userJobFamily;
    private String userSubFamily;
    private String userStandardJob;
    private String userJobLevel;
    private Boolean outstanding;
    
    public Boolean getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Boolean outstanding) {
		this.outstanding = outstanding;
	}
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
    public int getUserManagerId() {
        return userManagerId;
    }
    public void setUserManagerId(int userManagerId) {
        this.userManagerId = userManagerId;
    }
    public String getCompanyOp() {
        return companyOp;
    }
    public void setCompanyOp(String companyOp) {
        this.companyOp = companyOp;
    }
    public String getUserJob() {
        return userJob;
    }
    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }
    public String getUserScope() {
        return userScope;
    }
    public void setUserScope(String userScope) {
        this.userScope = userScope;
    }
    public String getUserJobFamily() {
        return userJobFamily;
    }
    public void setUserJobFamily(String userJobFamily) {
        this.userJobFamily = userJobFamily;
    }
    public String getUserSubFamily() {
        return userSubFamily;
    }
    public void setUserSubFamily(String userSubFamily) {
        this.userSubFamily = userSubFamily;
    }
    public String getUserStandardJob() {
        return userStandardJob;
    }
    public void setUserStandardJob(String userStandardJob) {
        this.userStandardJob = userStandardJob;
    }
    public String getUserJobLevel() {
        return userJobLevel;
    }
    public void setUserJobLevel(String userJobLevel) {
        this.userJobLevel = userJobLevel;
    }
 
}

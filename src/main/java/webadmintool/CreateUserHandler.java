package webadmintool;

import model.License;
import model.LicenseDAO;
import model.User;
import model.UserDAO;

import javax.servlet.http.HttpSession;

public class CreateUserHandler {

    private User user;
    private String[] licenses;
    private HttpSession session;

    public CreateUserHandler(User user, String[] licenses, HttpSession session) {
        this.user = user;
        this.licenses = licenses;
        this.session = session;
    }

    public boolean create() {
        if(isUsernameTaken()) {
            setErrorAttribute("Username is already taken.");
            return false;
        }
        setLicenses();
        saveUserToDatabase();
        return true;
    }

    private void setLicenses() {
        if(licenses == null || licenses.length == 0) return;
            for(String licenseId: licenses) {
            License license = LicenseDAO.retrieve(licenseId);
            user.getLicenses().add(license);
        }
    }

    private void saveUserToDatabase() {
        UserDAO.createUser(user);
    }

    private boolean isUsernameTaken()  {
        return UserDAO.getUser(user.getUsername()) != null;
    }

    private void setErrorAttribute(String errorMessage) {
        session.setAttribute("userCreationError", errorMessage);
    }
}
package backstage;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class SubmissionUploadListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equalsIgnoreCase("newSubmission")) {
            handleNewSubmission(event);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equalsIgnoreCase("newSubmission")) {
            handleNewSubmission(event);
        }
    }

    private void handleNewSubmission(HttpSessionBindingEvent event) {
        String newSubmission = (String) event.getValue();
        SubmissionProcessor submissionProcessor = new SubmissionProcessor(newSubmission);
        event.getSession().removeAttribute(event.getName());
        boolean success = submissionProcessor.process();
        if(success) {
            event.getSession().setAttribute("uploadSuccessful", "true");
        } else {
            event.getSession().setAttribute("uploadSuccessful", "false");
        }
    }
}
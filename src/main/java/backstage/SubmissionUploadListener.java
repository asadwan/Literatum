package backstage;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class SubmissionUploadListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        handleNewSubmission(event);

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        handleNewSubmission(event);
    }

    private void handleNewSubmission(HttpSessionBindingEvent event) {
        String newSubmission = (String) event.getSession().getAttribute("newSubmission");;
        SubmissionProcessor submissionProcessor = new SubmissionProcessor(newSubmission);
        submissionProcessor.process();

    }
}
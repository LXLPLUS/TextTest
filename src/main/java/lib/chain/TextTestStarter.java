package lib.chain;

import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;

public class TextTestStarter {

    TextDispatch testRunSolution = null;
    AnnotationDispatch annotationDispatch = null;

    public TextTestStarter(Class<?> c) throws Exception {
        if (TextPreCheck.checkTextAnnotation(c, TextTest.class, TestCollect.class)) {
            testRunSolution = new TextDispatch(c, TextTest.class, TestCollect.class);
        }
        if (AnnotationPreCheck.checkAnnotation(c, SourceParam.class, SourceParams.class)) {
            annotationDispatch = new AnnotationDispatch(c, SourceParam.class, SourceParams.class);
        }
    }
    public void starkAllTask() throws Exception {
        if (testRunSolution != null) {
            testRunSolution.startAllTask();
        }
        if (annotationDispatch != null) {
            annotationDispatch.startAllTask();
        }
    }
}

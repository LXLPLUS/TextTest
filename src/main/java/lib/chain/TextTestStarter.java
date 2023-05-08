package lib.chain;
/*
  @author 青碧凝霜
 * 2023 2023/5/8 3:01 created
 */
import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;

public class TextTestStarter {

    TextDispatch testRunSolution = null;
    AnnotationDispatch annotationDispatch = null;

    public TextTestStarter(Class<?> c) {
        if (TextPreCheck.checkTextAnnotation(c, TextTest.class, TestCollect.class)) {
            try {
                testRunSolution = new TextDispatch(c, TextTest.class, TestCollect.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (AnnotationPreCheck.checkAnnotation(c, SourceParam.class, SourceParams.class)) {
            try {
                annotationDispatch = new AnnotationDispatch(c, SourceParam.class, SourceParams.class);
            } catch (AnnotationException e) {
                e.printStackTrace();
            }
        }
    }
    public void starkAllTask() {
        if (testRunSolution != null) {
            testRunSolution.startAllTask();
        }
        if (annotationDispatch != null) {
            annotationDispatch.startAllTask();
        }
    }
}

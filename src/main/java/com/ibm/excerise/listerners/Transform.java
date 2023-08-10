package com.ibm.excerise.listerners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer {


    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          java.lang.reflect.Constructor testConstructor,
                          java.lang.reflect.Method testMethod) {

        annotation.setRetryAnalyzer(Retry.class);


    }


}

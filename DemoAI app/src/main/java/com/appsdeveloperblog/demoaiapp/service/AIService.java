package com.appsdeveloperblog.demoaiapp.service;

import org.springframework.stereotype.Service;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class AIService {

    public String performAnalysis(String dataPath) throws Exception {
        // Load dataset
        DataSource source = new DataSource(dataPath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        // Build classifier
        Classifier classifier = new J48();
        classifier.buildClassifier(data);

        // For simplicity, return the classifier model string representation
        return classifier.toString();
    }
}


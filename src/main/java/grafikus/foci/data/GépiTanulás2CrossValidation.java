package grafikus.foci.data;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.*;
import java.util.Random;
public class GépiTanulás2CrossValidation {
    String fájlnév;
    int classIndex;
    public Classifier classifier;

    public int TP;
    public int  FN;
    public int  FP;
    public int  TN;

    public double CCI;

    public double ICI;

    public String summary;
    public int size;

    public GépiTanulás2CrossValidation(String fájlNév, int classIndex, Classifier classifier){	// Classifier classifier: interface
        // https://weka.sourceforge.io/doc.dev/weka/classifiers/Classifier.html
        this.fájlnév = fájlNév;
        this.classIndex = classIndex;
        this.classifier = classifier;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fájlnév));
            Instances instances = new Instances(bufferedReader);
            instances.setClassIndex(this.classIndex);

            //aaaaaaaaa
            Instances tanító, kiértékelő;
            Evaluation evaluation;


            if(false) instances.randomize(new Random());
            evaluation = new Evaluation(instances);
            evaluation.crossValidateModel( classifier, instances, 10, new Random(1));

            CCI = evaluation.correct();
            ICI = instances.size()-(int)evaluation.correct();
            summary = evaluation.toSummaryString("\nResults", false);
            size = instances.size();


            //aaaaaaaaa
            tanító = new Instances(instances,0,9*instances.size()/10);
            kiértékelő = new Instances(instances,9*instances.size()/10,instances.size()/10);
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);

            double[] eredmeny = evaluation.evaluateModel(classifier, kiértékelő);

            int TP=0, TN=0, FP=0, FN=0;
            //  TP:TtruePositive, TN:TrueNegative, FP:FalsePositive, FN:FalseNegative
            for(int i=0;i<kiértékelő.size();i++){
                System.out.println(i+"\t"+(((Instances)kiértékelő).get(i)).classValue()+"\t"+eredmeny[i]);
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==1)
                    TP++;
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==0)
                    FN++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==1)
                    FP++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==0)
                    TN++;
            }

            this.TP = TP;
            this.TN = TN;
            this.FP = FP;
            this.FN = FN;

            tanító = new Instances(instances,0,instances.size());
            kiértékelő = new Instances(instances,0,instances.size());
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            evaluation.evaluateModel(classifier, kiértékelő);

        }
        catch (Exception e) {
            System.out.println("Error Occurred!!!! \n" + e.getMessage());
        }
    }

    public void WriteToFile(PrintWriter kiir) throws FileNotFoundException {
        kiir.println("\nclassifier.getClass():"+classifier.getClass());
        kiir.println(summary);
        kiir.println("Correctly Classified Instances:"+CCI+"\t"+100*CCI/size+"%");
        kiir.println("Incorrectly Classified Instances:"+ICI);
        kiir.println(classifier.toString());
        kiir.println();
        kiir.println("\nGépiTanulás1: Vizsgálat részletesen, egyesével:");
        kiir.println("TP="+TP+", "+"TN="+TN+", "+"FP="+FP+", "+"FN="+FN);
    }
}

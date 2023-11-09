package grafikus.foci.data;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

public class GépiTanulás1 {
    public GépiTanulás1(String fájlNév, int classIndex){
        try {
            PrintWriter kiir = new PrintWriter("Döntési fa.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fájlNév));
            Instances instances = new Instances(bufferedReader);
            kiir.println("instances.size():" + instances.size());
            instances.setClassIndex(classIndex);
            Instances tanító, kiértékelő;
            J48 classifier;
            Evaluation evaluation;
            classifier = new J48();
            kiir.println("\nGépiTanulás1: Randomize után vagy anélkül: tanító: első 90%, kiértékelő: utolsó 10%");
            if(false) instances.randomize(new Random());
            tanító = new Instances(instances,0,9*instances.size()/10);
            kiir.println("tanító.size():"+tanító.size());
            kiértékelő = new Instances(instances,9*instances.size()/10,instances.size()/10);
            kiir.println("kiértékelő.size():"+kiértékelő.size());
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            double[] eredmeny = evaluation.evaluateModel(classifier, kiértékelő);
            kiir.println(evaluation.toSummaryString("\nResults", false));
            kiir.println("Correctly Classified Instances:"+(int)evaluation.correct());
            kiir.println("Incorrectly Classified Instances:"+(kiértékelő.size()-(int)evaluation.correct()));
            kiir.println(classifier.toString());
            kiir.println(classifier.toString());	kiir.close();


            kiir.println("\nGépiTanulás1: Vizsgálat részletesen, egyesével:");
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

            kiir.println("TP="+TP+", "+"TN="+TN+", "+"FP="+FP+", "+"FN="+FN);
            kiir.println("TP+TN="+(TP+TN));
            kiir.println("FP+FN="+(FP+FN));
            kiir.println("\nGépiTanulás1: ha a tanító és kiértékelő megegyezik:");
            tanító = new Instances(instances,0,instances.size());
            kiértékelő = new Instances(instances,0,instances.size());
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            evaluation.evaluateModel(classifier, kiértékelő);
            kiir.println(evaluation.toSummaryString("\nResults", false));
        }
        catch (Exception e) {
            System.out.println("Error Occurred!!!! \n" + e.getMessage());
        }
    }
}



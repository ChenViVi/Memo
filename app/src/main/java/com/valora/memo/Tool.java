package com.valora.memo;

import com.valora.memo.model.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Tool {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || Pattern.matches("^\\s*$", str);
    }

    public static double EF = 2.5;
    public static int I1 = 1;
    public static int I2 = 6;
    public static int I = I1;
    public static int q = 0;

    public static int getRandomQ(){
        return q = (int)(Math.random() * 6);
    }

    /*public static int nextI(int q, double n){
        int I;
        if (n == 1) I = I1;
        else if (n == 2) I = I2;
        else if (q < 3) {
            I = I1;
            EF = 2.5;
        }
        else {
            if (q != 4)
                EF = EF-0.8+0.28*q-0.02*q*q;
            if (EF < 1.3) EF = 1.3;
            else I = (int) EF * I;
        }
        return I;
    }*/

    public static Question calculateI(Question question, int q){
        question.setN(question.getN() + 1);
        if (question.getN() == 1) question.setI(I1);
        else if (question.getN() == 2) question.setI(I2);
        else if (q < 3) {
            question.setI(I1);
            question.setN(1);
            question.setEF(2.5);
        }
        else {
            if (q != 4)
                question.setEF(question.getEF() - 0.8 + 0.28*q - 0.02*q*q);
            if (question.getEF() < 1.3) question.setEF(1.3);
            else if (question.getEF() > 2.5) question.setEF(2.5);
            question.setI(((int) question.getEF() * question.getI()));
        }
        question.setTime(getTaskZeroPointTimestamps(question.getI()));
        return question;
    }

    public static long getTodayZeroPointTimestamps(){
        long currentTimestamps = System.currentTimeMillis();
        long oneDayTimestamps = (long) (60 * 60 * 24 * 1000);
        return currentTimestamps-(currentTimestamps + 60*60*8*1000) % oneDayTimestamps;
    }

    public static long getTaskZeroPointTimestamps(int day){
        return getTodayZeroPointTimestamps() + day*24*60*60*1000;
    }


    /*public static int getDays(long time, long now){
        if (time > now) return 0;
        return (int)(now - time /  (1000 * 60 * 60 * 24));
    }*/

    /*public static List<Question> sortQuestionByI(List<Question> questions){
        long now = new Date().getTime();
        for(int i=0;i<questions.size()-1;i++){
            for(int j=0;j<questions.size()-1-i;j++){
                Question question1 = questions.get(j);
                Question question2 = questions.get(j+1);
                if(question1.getNum()getDays() < question2.getNum()){
                    questions.set(j, question2);
                    questions.set(j+1, question1);
                }
            }
        }
        return questions;
    }*/

    public static void main(String[] args){
        //System.out.println( getTodayZeroPointTimestamps() + " " + getTaskZeroPointTimestamps(1) + " " + new Date().getTime() + " " + System.currentTimeMillis());
        //getTaskZeroPointTimestamps(1);

        /*for (int i = 1; i < 50; i++) {
            nextI(getRandomQ(), i);
            System.out.println(" i=" + i + " q=" + q + " EF=" + EF + " N=" + I);
        }*/
        /*List<Q> questions = new ArrayList<>();
        questions.add(new Q(0,50));
        questions.add(new Q(1,40));
        questions.add(new Q(2,63));
        questions.add(new Q(3,20));
        questions.add(new Q(4,34));
        questions.add(new Q(5,98));
        questions.add(new Q(6,41));
        *//*50,40,63,20,98,41,65,78,21,34*//*
        for(int i=0;i<questions.size()-1;i++){
            for(int j=0;j<questions.size()-1-i;j++){
                Q question1 = questions.get(j);
                Q question2 = questions.get(j+1);
                if(question1.getNum() < question2.getNum()){
                    questions.set(j, question2);
                    questions.set(j+1, question1);
                }
            }
        }
        for (Q q:questions){
            System.out.println(q.getId() + " " + q.getNum());
        }*/
        Question question = new Question();
        for (int i = 0; i < 20; i++) {
            int q = getRandomQ();
            System.out.println("n=" + question.getN() + " q=" + q + " EF=" + question.getEF() + " I=" + question.getI());
            question = calculateI(question, q);
        }
    }
}

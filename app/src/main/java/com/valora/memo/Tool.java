package com.valora.memo;

import com.valora.memo.model.Question;

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

    /**
     * 算法核心
     */
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
        question.setNextTime(getTaskZeroPointTimestamps(question.getI()));
        question.setReviewTime(getTodayZeroPointTimestamps());
        return question;
    }

    /**
     * 得到今天零点的时间戳
     */
    public static long getTodayZeroPointTimestamps(){
        long currentTimestamps = System.currentTimeMillis();
        long oneDayTimestamps = (long) (60 * 60 * 24 * 1000);
        return currentTimestamps-(currentTimestamps + 60*60*8*1000) % oneDayTimestamps;
    }

    /**
     * 得到N天后零点的时间戳
     */
    public static long getTaskZeroPointTimestamps(int day){
        return getTodayZeroPointTimestamps() + day*24*60*60*1000;
    }

    public static void main(String[] args){
        Question question = new Question();
        for (int i = 0; i < 20; i++) {
            int q = getRandomQ();
            System.out.println("n=" + question.getN() + " q=" + q + " EF=" + question.getEF() + " I=" + question.getI());
            question = calculateI(question, q);
        }
    }
}

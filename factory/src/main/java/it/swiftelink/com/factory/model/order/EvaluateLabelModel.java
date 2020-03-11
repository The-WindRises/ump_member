package it.swiftelink.com.factory.model.order;

public class EvaluateLabelModel {


    /**
     * type : Doctor
     * language : zh_CN
     * score : 1
     */

    private String type;
    private String language;
    private int score;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

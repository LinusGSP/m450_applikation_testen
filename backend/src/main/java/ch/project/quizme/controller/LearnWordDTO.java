package ch.project.quizme.controller;

public class LearnWordDTO {
    private Integer learnSetId;
    private String word;
    private String word2;

    public Integer getLearnSetId() {
        return learnSetId;
    }

    public void setLearnSetId(Integer learnSetId) {
        this.learnSetId = learnSetId;
    }

    public String getWord1() {
        return word;
    }


    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}

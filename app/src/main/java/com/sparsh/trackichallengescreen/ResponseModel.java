package com.sparsh.trackichallengescreen;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel {
    ArrayList<Model> Participant = new ArrayList<>();

    public ArrayList<Model> getParticipant() {
        return Participant;
    }

    public void setParticipant(ArrayList<Model> participant) {
        Participant = participant;
    }
}

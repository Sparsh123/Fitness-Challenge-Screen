package com.sparsh.trackichallengescreen;




public class Model {

    int  man_icon;
    String image, position, name, steps;
    String creator, endTime, stepsToComplete;

    public void setImage(String url)
    {
        this.image = url;
    }


    public void setPosition(String position)
    {
        this.position = position;
    }


    public void setSteps(String steps)
    {
        this.steps = steps;
    }


    public void setName(String name)
    {
        this.name = name;
    }



    public void setMan_icon(int man_icon)
    {
        this.man_icon = man_icon;
    }

    public void setCreator(String man_icon)
    {
        this.creator = creator;
    }




    public String getImage()
    {
        return  image;
    }


    public String getPosition()
    {
        return position;
    }


    public String getSteps()
    {
        return steps;
    }


    public String getName()
    {
        return name;
    }



    public int getMan_icon()
    {
        return man_icon;
    }


    public String getCreator()
    {
        return creator;
    }



}

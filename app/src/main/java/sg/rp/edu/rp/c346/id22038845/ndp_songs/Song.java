package sg.rp.edu.rp.c346.id22038845.ndp_songs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    private int imgResource;
    private int bgColors;

    public Song(int id, String title, String singers, int year, int stars){
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getSingers() {return singers;}
    public int getYear() {return year;}
    public int getStars() {return stars;}

    public void setSong(String title, String singers, int year, int stars){

        this.title =title;
        this.singers =singers;
        this.year = year;
        this.stars = stars;
    }

    @NonNull
    @Override
    public String toString(){
        String starStr = "";
        for(int i=0; i<stars; i++){
            starStr+="★";
        }

        return title + " by " + singers + " \n [ YR: "+year+ " | Stars: " + starStr +"] ";
    }
}
